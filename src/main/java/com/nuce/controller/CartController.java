package com.nuce.controller;

import com.nuce.model.*;
import com.nuce.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    private ItemService itemService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private BillService billService;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private DetailProductService detailProductService;
    @Autowired
    private EmailAPI emailAPI;
    @GetMapping("/giohang")
    public String cart(ModelMap modelMap, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        List<Item> items = itemService.getByCustomerId(customer.getId());
        session.setAttribute("num_item", items.size());
        modelMap.addAttribute("items", items);
        return "user_view/cart";
    }

    @GetMapping("/delete-item/{id}")
    public String deleteItem(@PathVariable("id") int id, HttpServletRequest request) {
        itemService.delete(id);
        return "redirect:/giohang";
    }

    @GetMapping("/thanh-toan")
    public String checkout(HttpServletRequest request, ModelMap modelMap) {
        HttpSession session = request.getSession();
        List<Item> items = (List<Item>) session.getAttribute("items");
        Customer customer = (Customer) session.getAttribute("customer");
        modelMap.addAttribute("items", items);
        modelMap.addAttribute("customer", customer);
        modelMap.addAttribute("provinces", addressService.getAllProvince());
        return "user_view/checkout";
    }

    @PostMapping("/checkout")
    public String checkout(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        int customerId = Integer.parseInt(request.getParameter("customer_id"));
        Customer customer = customerService.getById(customerId);
        String address = request.getParameter("address");
        Bill bill = new Bill();
        bill.setCustomer(customer);
        bill.setDeliveryAddress(address);
        Bill newBill = billService.insert(bill);
        HttpSession session = request.getSession();
        List<Item> items = (List<Item>) session.getAttribute("items");
        for (Item item : items) {
            OrderItem orderItem = new OrderItem();
            orderItem.setDetailProduct(item.getDetailProduct());
            orderItem.setNumber(item.getNumber());
            orderItem.setBill(newBill);
            orderItemService.insert(orderItem);
            itemService.delete(item.getId());
            DetailProduct detailProduct = orderItem.getDetailProduct();
            detailProduct.setNumber(detailProduct.getNumber() - orderItem.getNumber());
            detailProductService.update(detailProduct);
        }
        if (newBill.getId() != 0) {
            session.setAttribute("num_item", itemService.getByCustomerId(customerId).size());
            session.removeAttribute("items");
            redirectAttributes.addFlashAttribute("msg", "Đặt hàng thành công");
            String subject = "Xác nhận đơn hàng";
            String content = "Chào " + customer.getName() + ".Bạn vừa đặt 1 đơn hàng trên Fashi Shop." +
                    "Đơn hàng sẽ được giao trong vòng 1-5 ngày tới.";
            emailAPI.sendEmail(customer.getEmail(), subject, content);
        } else {
            redirectAttributes.addFlashAttribute("msg", "Đặt hàng thất bại");
        }
        return "redirect:/thanh-toan";
    }


}
