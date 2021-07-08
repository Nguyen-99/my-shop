package com.nuce.controller;

import com.nuce.model.Category;
import com.nuce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public String view(ModelMap modelMap){
        modelMap.addAttribute("list",categoryService.getByPage(1,7));
        int numPage= (int) Math.ceil((double) categoryService.getAll().size()/7);
        modelMap.addAttribute("num_page",numPage);
        modelMap.addAttribute("page_id",1);
        return "admin_view/manage_category";
    }
    @GetMapping("/{pageId}")
    public String viewByPage(ModelMap modelMap, @PathVariable("pageId") int pageId){
        int total=7;
        int num=pageId;
        if(pageId==1){}
        else{
            pageId=(pageId-1)*total+1;
        }
        int numPage= (int) Math.ceil((double) categoryService.getAll().size()/total);
        modelMap.addAttribute("num",num);
        modelMap.addAttribute("page_id",pageId);
        modelMap.addAttribute("num_page",numPage);
        modelMap.addAttribute("list",categoryService.getByPage(pageId,total));
        return "admin_view/manage_category";
    }
    @PostMapping("/add-category")
    public String addCategory(RedirectAttributes redirectAttributes,@RequestParam("name") String name,
                            @RequestParam(value = "gender") boolean gender,@RequestParam(value = "active",defaultValue = "false") boolean active,
                            @RequestParam(value = "priority",defaultValue ="0") int priority){
        Category category=new Category();
        category.setName(name);
        category.setGender(gender);
        category.setActive(active);
        category.setPriority(priority);
        boolean check= categoryService.insert(category);
        if(check){
            redirectAttributes.addFlashAttribute("msg","Thêm mới thành công");
        }else {
            redirectAttributes.addFlashAttribute("msg","Thêm mới thất bại");
        }
        return "redirect:/category";
    }
    @GetMapping("/delete-category/{id}")
    public String deleteCategory(@PathVariable("id") int id,RedirectAttributes redirectAttributes){
        boolean check= categoryService.delete(id);
        if(check){
            redirectAttributes.addFlashAttribute("msg","Xóa thành công");
        }else {
            redirectAttributes.addFlashAttribute("msg","Xóa thất bại");
        }
        return "redirect:/category";
    }
    @PostMapping("/edit-category")
    public String editCategory(RedirectAttributes redirectAttributes,HttpServletRequest request,@RequestParam(value = "active",defaultValue = "false") boolean active){
        Category category=new Category();
        int id= Integer.parseInt(request.getParameter("id"));
        String name=request.getParameter("name");
        boolean gender= Boolean.parseBoolean(request.getParameter("gender"));
        int priority= Integer.parseInt(request.getParameter("priority"));
        category.setId(id);
        category.setName(name);
        category.setGender(gender);
        category.setActive(active);
        category.setPriority(priority);
        System.out.println(active+","+priority);
        boolean check= categoryService.update(category);
        if(check){
            redirectAttributes.addFlashAttribute("msg","Sửa thành công");
        }else {
            redirectAttributes.addFlashAttribute("msg","Sửa thất bại");
        }
        return "redirect:/category";
    }
}
