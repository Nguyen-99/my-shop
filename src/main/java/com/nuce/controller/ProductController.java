package com.nuce.controller;

import com.nuce.model.Category;
import com.nuce.model.DetailProduct;
import com.nuce.model.Image;
import com.nuce.model.Product;
import com.nuce.service.CategoryService;
import com.nuce.service.DetailProductService;
import com.nuce.service.ImageService;
import com.nuce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private DetailProductService detailProductService;
    @Autowired
    private ImageService imageService;
    @GetMapping("/male")
    public String viewMale(ModelMap modelMap) {
        modelMap.addAttribute("list", productService.getProductByGender(true));
        int numPage= (int) Math.ceil((double) productService.getProductByGender(true).size()/7);
        modelMap.addAttribute("num_page",numPage);
        return "admin_view/manage_product";
    }
    @GetMapping("/female")
    public String viewFemale(ModelMap modelMap) {
        modelMap.addAttribute("list", productService.getProductByGender(false));
        int numPage= (int) Math.ceil((double) productService.getProductByGender(false).size()/7);
        modelMap.addAttribute("num_page",numPage);
        return "admin_view/manage_product";
    }
    @GetMapping("/add-product")
    public String addProduct(ModelMap modelMap) {
        Product product = new Product();
        modelMap.addAttribute("product", product);
        modelMap.addAttribute("categories", categoryService.getAll());
        return "admin_view/add_product";
    }

    @PostMapping("/add-product")
    public String addProduct(RedirectAttributes redirectAttributes, @ModelAttribute("product") Product product,
                             @RequestParam("category_id") int categoryId, @RequestParam("file") MultipartFile file,
                             HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("/resources/images/product/");
        System.out.println(path);
        File newFile = new File(path + file.getOriginalFilename());
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(newFile);
            fileOutputStream.write(file.getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        product.setImage(file.getOriginalFilename());
        Category category= categoryService.getById(categoryId);
        product.setCategory(category);
        boolean check = productService.insert(product);
        if (check) {
            redirectAttributes.addFlashAttribute("msg", "Thêm mới thành công");
        } else {
            redirectAttributes.addFlashAttribute("msg", "Thêm mới thất bại");
        }
        if(category.isGender()){
            return "redirect:/product/male";
        }else {
            return "redirect:/product/female";
        }
    }

    @GetMapping("/delete-product/{id}")
    public String deleteProduct(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        Category category=productService.getById(id).getCategory();
        boolean check = productService.delete(id);
        if (check) {
            redirectAttributes.addFlashAttribute("msg", "Xóa thành công");
        } else {
            redirectAttributes.addFlashAttribute("msg", "Xóa thất bại");
        }
        if(category.isGender()){
            return "redirect:/product/male";
        }else {
            return "redirect:/product/female";
        }
    }

    @GetMapping("/edit-product/{id}")
    public String editProduct(ModelMap modelMap, @PathVariable("id") int id) {
        Product product = productService.getById(id);
        modelMap.addAttribute("product", product);
        modelMap.addAttribute("categories", categoryService.getAll());
        return "admin_view/edit_product";
    }

    @PostMapping("/edit-product")
    public String editProduct(RedirectAttributes redirectAttributes, @ModelAttribute("product") Product product,
                              @RequestParam("file") MultipartFile file, @RequestParam("category_id") int categoryId,
                              HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("/resources/images/product/");
        if (!file.getOriginalFilename().isEmpty()) {
            File newFile = new File(path + file.getOriginalFilename());
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(newFile);
                fileOutputStream.write(file.getBytes());
                fileOutputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(file.getOriginalFilename());
            product.setImage(file.getOriginalFilename());
        }
        Category category=categoryService.getById(categoryId);
        product.setCategory(category);
        boolean check = productService.update(product);
        if (check) {
            redirectAttributes.addFlashAttribute("msg", "Sửa thành công");
        } else {
            redirectAttributes.addFlashAttribute("msg", "Sửa thất bại");
        }
        if(category.isGender()){
            return "redirect:/product/male";
        }else {
            return "redirect:/product/female";
        }
    }
    @GetMapping("/detail-product/{id}")
    public String detailProduct(@PathVariable("id") int id, ModelMap modelMap) {
        modelMap.addAttribute("product", productService.getById(id));
        modelMap.addAttribute("detail_products", detailProductService.getDetailByProductId(id));
        modelMap.addAttribute("new_detail", new DetailProduct());
        modelMap.addAttribute("images",imageService.getImageByProductId(id));
        modelMap.addAttribute("sizes",detailProductService.getListSize(id));
        modelMap.addAttribute("colors",detailProductService.getListColor(id));
        return "admin_view/detail_product";
    }

    @PostMapping("/add-detail")
    public String addDetail(@ModelAttribute("new_detail") DetailProduct detailProduct, HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("product_id"));
        detailProduct.setProduct(productService.getById(id));
        detailProductService.insertOrUpdate(detailProduct);
        return "redirect:/product/detail-product/" + id;
    }

    @GetMapping("/delete-detail/{id}")
    public String deleteDetail(@PathVariable("id") int id) {
        int productId = detailProductService.getById(id).getProduct().getId();
        detailProductService.delete(id);
        return "redirect:/product/detail-product/" + productId;
    }

    @PostMapping("/edit-detail")
    public String editDetail(HttpServletRequest request) {
        DetailProduct detailProduct = new DetailProduct();
        int id = Integer.parseInt(request.getParameter("id"));
        int productId = Integer.parseInt(request.getParameter("product_id"));
        String size = request.getParameter("size");
        String color = request.getParameter("color");
        int number = Integer.parseInt(request.getParameter("number"));
        detailProduct.setId(id);
        detailProduct.setProduct(productService.getById(productId));
        detailProduct.setSize(size);
        detailProduct.setColor(color);
        detailProduct.setNumber(number);
        detailProductService.update(detailProduct);
        return "redirect:/product/detail-product/" + productId;
    }

    @GetMapping("/image/{id}")
    public String image(ModelMap modelMap, @PathVariable("id") int id) {
        modelMap.addAttribute("product_id", id);
        modelMap.addAttribute("images",imageService.getImageByProductId(id));
        return "admin_view/image";
    }
    @PostMapping("/image/add")
    public String addImage(@RequestParam("file") MultipartFile file,@RequestParam("product_id") int productId,
                           HttpServletRequest request){
        if (!file.getOriginalFilename().isEmpty()) {
            String path=request.getSession().getServletContext().getRealPath("/resources/images/product/");
            File newFile=new File(path+file.getOriginalFilename());
            try {
                FileOutputStream fileOutputStream=new FileOutputStream(newFile);
                fileOutputStream.write(file.getBytes());
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Image image=new Image();
            image.setImage(file.getOriginalFilename());
            image.setProduct(productService.getById(productId));
            imageService.insert(image);
        }
        return "redirect:/product/image/"+productId;
    }
    @PostMapping("/image/edit")
    public String editImage(@RequestParam("file") MultipartFile file,@RequestParam("id") int id,
                            @RequestParam("product_id") int productId,HttpServletRequest request){
        if (!file.getOriginalFilename().isEmpty()) {
            String path=request.getSession().getServletContext().getRealPath("/resources/images/product/");
            File newFile=new File(path+file.getOriginalFilename());
            try {
                FileOutputStream fileOutputStream=new FileOutputStream(newFile);
                fileOutputStream.write(file.getBytes());
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Image image=new Image();
            image.setId(id);
            image.setImage(file.getOriginalFilename());
            image.setProduct(productService.getById(productId));
            imageService.update(image);
        }

        return "redirect:/product/image/"+productId;
    }
    @GetMapping("/image/delete/{id}")
    public String deleteImage(@PathVariable("id") int id){
        int productId=imageService.getById(id).getProduct().getId();
        imageService.delete(id);
        return "redirect:/product/image/"+productId;
    }
}
