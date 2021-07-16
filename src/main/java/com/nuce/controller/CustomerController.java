package com.nuce.controller;

import com.nuce.dao_impl.AddressDaoImpl;
import com.nuce.model.Bill;
import com.nuce.model.Customer;
import com.nuce.model.OrderItem;
import com.nuce.model.Province;
import com.nuce.service.AddressService;
import com.nuce.service.BillService;
import com.nuce.service.CustomerService;
import com.nuce.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private BillService billService;
    @Autowired
    private OrderItemService orderItemService;
    @GetMapping("/taikhoan")
    public String profile(ModelMap modelMap, HttpServletRequest request){
        HttpSession session=request.getSession();
        Customer customer= (Customer) session.getAttribute("customer");
        modelMap.addAttribute("customer",customer);
        List<Province> provinces=addressService.getAllProvince();
        modelMap.addAttribute("provinces",provinces);
        return "user_view/profile";
    }
    @PostMapping("/update-profile")
    public String updateProfile(@ModelAttribute("customer") Customer customer, @RequestParam("file") MultipartFile file,
                                HttpServletRequest request, RedirectAttributes redirectAttributes){
        String path=request.getServletContext().getRealPath("/resources/images/user/");
        if(!file.getOriginalFilename().isEmpty()){
            File newFile=new File(path+file.getOriginalFilename());
            try {
                FileOutputStream fileOutputStream=new FileOutputStream(newFile);
                fileOutputStream.write(file.getBytes());
                fileOutputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            customer.setAvatar(file.getOriginalFilename());
        }
        boolean check=customerService.update(customer);
        if(check){
            redirectAttributes.addFlashAttribute("msg","Cập nhật thành công");
        }else {
            redirectAttributes.addFlashAttribute("msg","Cập nhật thất bại");
        }
        HttpSession session=request.getSession();
        session.setAttribute("customer",customer);
        return "redirect:/taikhoan";
    }
    @GetMapping("/donmua")
    public String order(ModelMap modelMap,HttpServletRequest request){
        HttpSession session=request.getSession();
        Customer customer= (Customer) session.getAttribute("customer");
        List<Bill> bills=billService.getByCustomer(customer.getId());
        for (Bill bill:bills) {
            bill.setOrderItems(orderItemService.getByBill(bill.getId()));
        }
        modelMap.addAttribute("bills",bills);
        return "user_view/order";
    }
}
