package org.example.midterm.api;
import jakarta.persistence.*;
import org.example.midterm.Service.UserService;
import org.example.midterm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthAPI {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User newUser) {
        // Kiểm tra xem đã tồn tại tài khoản với địa chỉ email này chưa
        User existingUser = userService.getByEmail(newUser.getEmail());
        if (existingUser != null) {
            // Nếu đã tồn tại tài khoản với địa chỉ email này, trả về thông báo lỗi
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists");
        }

        // Nếu không có tài khoản nào trùng email, thực hiện lưu người dùng mới
        if (userService.save(newUser)) {
            return ResponseEntity.ok().body("User registered successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register user");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginUser) {
        // Tìm kiếm người dùng trong cơ sở dữ liệu dựa trên email
        User existingUser = userService.getByEmail(loginUser.getEmail());

        if (existingUser == null) {
            return ResponseEntity.badRequest().body("Invalid email or password");
        }

        // So sánh mật khẩu đã mã hóa
        if (!passwordEncoder.matches(loginUser.getPassword(), existingUser.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid email or password");
        }


        return ResponseEntity.ok(loginUser);
    }
}

