package org.example.midterm.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.midterm.Service.UserService;
import org.example.midterm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
        // Kiểm tra xem đã tồn tại tài khoản với địa chỉ email này chưa
        User existing = userService.getByEmail(user.getEmail());
        if (existing != null) {
            // Nếu đã tồn tại tài khoản với địa chỉ email này, thêm thông báo lỗi vào RedirectAttributes
            redirectAttributes.addFlashAttribute("error", "emailExists");
            return "redirect:/register";
        }

        // Lưu người dùng mới vào cơ sở dữ liệu
        if (userService.save(user)) {
            // Nếu đăng ký thành công, chuyển hướng đến trang đăng nhập với thông báo đăng ký thành công
            return "redirect:/login?registered";
        } else {
            // Nếu có lỗi xảy ra trong quá trình đăng ký, chuyển hướng đến trang đăng ký với thông báo lỗi
            return "redirect:/register?error";
        }
    }



}
