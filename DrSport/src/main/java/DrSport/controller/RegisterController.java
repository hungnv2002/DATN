
package DrSport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import DrSport.entities.Role;
import DrSport.entities.User;
import DrSport.repository.UserRepository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class RegisterController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping("/register")
	public ModelAndView registerForm(ModelMap model) {
		model.addAttribute("user", new User());
		return new ModelAndView("web/register", model);
	}

	@PostMapping("/register")
	public String register(ModelMap model, @Validated @ModelAttribute("user") User dto, BindingResult result,
						   @RequestParam("password") String password) {
		// Kiểm tra lỗi form
		if (result.hasErrors()) {
			return "web/register";
		}

		// Kiểm tra email đã tồn tại hay chưa
		if (!checkEmail(dto.getEmail())) {
			model.addAttribute("error", "Email này đã được sử dụng!");
			return "web/register";
		}

		// Thiết lập thông tin người dùng và lưu vào cơ sở dữ liệu
		dto.setPassword(bCryptPasswordEncoder.encode(password)); // Mã hóa mật khẩu
		dto.setRegisterDate(new Date());
		dto.setStatus(true); // Kích hoạt tài khoản ngay lập tức
		dto.setAvatar("user.png");
		dto.setRoles(Arrays.asList(new Role("ROLE_USER")));

		// Lưu thông tin người dùng vào cơ sở dữ liệu
		userRepository.save(dto);

		model.addAttribute("message", "Đăng ký thành công! Bạn có thể đăng nhập ngay bây giờ.");
		return "web/login"; // Chuyển đến trang đăng nhập sau khi đăng ký thành công
	}

	// Hàm kiểm tra email đã tồn tại
	public boolean checkEmail(String email) {
		List<User> list = userRepository.findAll();
		for (User c : list) {
			if (c.getEmail().equalsIgnoreCase(email)) {
				return false;
			}
		}
		return true;
	}
}

