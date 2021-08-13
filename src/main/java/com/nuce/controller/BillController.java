package com.nuce.controller;

import com.nuce.model.Bill;
import com.nuce.service.BillService;
import com.nuce.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/bill")
public class BillController {
    @Autowired
    private BillService billService;
    @Autowired
    private OrderItemService orderItemService;
    @GetMapping
    public String getBill(ModelMap modelMap){
        List<Bill> bills=billService.getAll();
        for (Bill bill:bills) {
            bill.setOrderItems(orderItemService.getByBill(bill.getId()));
        }
        modelMap.addAttribute("bills",bills);
        return "admin_view/manage_bill";
    }
    @GetMapping(path = "search")
    public String search(ModelMap modelMap, @RequestParam("date1") String date1,@RequestParam("date2") String date2){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
        Date d1=null;
        Date d2=null;
        try {
            d1=formatter.parse(date1);
            d2=formatter.parse(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Bill> bills=billService.searchByDate(d1,d2);
        for (Bill bill:bills) {
            bill.setOrderItems(orderItemService.getByBill(bill.getId()));
        }
        System.out.println(bills.size());
        modelMap.addAttribute("bills",bills);
        return "admin_view/manage_bill";
    }
}
