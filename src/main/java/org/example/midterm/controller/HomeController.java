package org.example.midterm.controller;


import org.example.midterm.Service.BrandService;
import org.example.midterm.Service.CartService;
import org.example.midterm.Service.ProductService;
import org.example.midterm.Service.UserService;
import org.example.midterm.model.Brand;
import org.example.midterm.model.CartItem;
import org.example.midterm.model.Product;
import org.example.midterm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private ProductService productService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;
    @GetMapping("/")
    public String showIndex(Model model, @AuthenticationPrincipal UserDetails userDetails,@RequestParam(required = false) String keyword,@RequestParam(required = false, defaultValue = "4") Long brandId) {
        List<Product> products;
        List<Brand> brands = new ArrayList<>();
        if (keyword != null && !keyword.isEmpty()) {
            products = productService.searchByName(keyword);
            model.addAttribute("search_word", keyword);
        }
        else {
            products = productService.getAll();
        }

        if(brandId != 4){
            brands.add(brandService.findByID(brandId));
        }else {
            brands = brandService.getAll();
        }
        model.addAttribute("products", products);
        model.addAttribute("brands", brands);

        if(userDetails!=null){
            User user = userService.getByEmail(userDetails.getUsername());
            model.addAttribute("user", user);
            model.addAttribute("quantity_cart", cartService.getQuantity(user.getId()));
        }
        else {
            model.addAttribute("quantity_cart", 1);
            model.addAttribute("user", null);
        }
        return "index";
    }

    @GetMapping("/cart/add/{id}")
    public String addCart(@PathVariable("id") Long id,@AuthenticationPrincipal UserDetails userDetails) {
        Product foundProduct = productService.findById(id);
        if(userDetails!=null){
            User user = userService.getByEmail(userDetails.getUsername());
            cartService.addProduct(user.getId(),foundProduct);
        }
        return "redirect:/cart";
    }
    @GetMapping("/cart/minus/{id}")
    public String minusCart(@PathVariable("id") Long id,@AuthenticationPrincipal UserDetails userDetails) {
        if(userDetails!=null){
            User user = userService.getByEmail(userDetails.getUsername());
            CartItem cartItem = cartService.findCartItemByUserIdAndProductId(user.getId(),id);

            cartService.minusCartItem(cartItem.getId());
        }

        return "redirect:/cart";
    }

    @GetMapping("/cart/remove/{id}")
    public String removeCartItem(@PathVariable("id") Long id,@AuthenticationPrincipal UserDetails userDetails) {

        if(userDetails!=null){
            User user = userService.getByEmail(userDetails.getUsername());
            CartItem cartItem = cartService.findCartItemByUserIdAndProductId(user.getId(),id);
            if (cartItem == null){
                return "redirect:/";
            }


            cartService.removeCartItem(cartItem);
            return "redirect:/cart";
        }


        return "redirect:/cart";
    }
    @GetMapping("/cart")
    public  String showCart(Model model, @AuthenticationPrincipal UserDetails userDetails){
        if(userDetails != null){
            User user = userService.getByEmail(userDetails.getUsername());
            model.addAttribute("cart_products", cartService.getAllProduct(user.getId()));
            model.addAttribute("quantity_cart", cartService.getQuantity(user.getId()));
            model.addAttribute("total", cartService.getTotal(user.getId()));
            model.addAttribute("user", user);
        }
        return "cart";
    }

    @GetMapping("checkout")
    public String showFormCheckOut(Model model,@AuthenticationPrincipal UserDetails userDetails){
        if(userDetails != null){
            User user = userService.getByEmail(userDetails.getUsername());
            model.addAttribute("user",user);
            model.addAttribute("cart_products", cartService.getAllProduct(user.getId()));
            model.addAttribute("quantity_cart", cartService.getQuantity(user.getId()));
            model.addAttribute("total", cartService.getTotal(user.getId()));
            cartService.removeAllCartItem(user.getCart());
        }

        return "checkout";
    }
}
