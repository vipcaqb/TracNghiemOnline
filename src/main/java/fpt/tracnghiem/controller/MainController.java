package fpt.tracnghiem.controller;

import java.io.Console;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fpt.tracnghiem.entity.Role;
import fpt.tracnghiem.entity.TaiKhoan;
import fpt.tracnghiem.model.AjaxResponseTaiKhoan;
import fpt.tracnghiem.service.RoleService;
import fpt.tracnghiem.service.TaiKhoanService;

// TODO: Auto-generated Javadoc
/**
 * The Class MainController.
 */
@Controller
public class MainController {
	
	/** The tai khoan service. */
	@Autowired
	TaiKhoanService taiKhoanService;
	
	/** The role service. */
	@Autowired 
	RoleService roleService;
	
	/**
	 * Home.
	 *
	 * @return the string
	 */
	@GetMapping("/")
	public String home() {
		return "index.html";
	}
	
	/**
	 * Login form.
	 *
	 * @return the string
	 */
	@GetMapping("/login")
	public String loginForm() {
		
		return "/login";
	}
	
	/**
	 * Login.
	 *
	 * @param username the username
	 * @param password the password
	 * @param req the req
	 * @param res the res
	 * @return the string
	 */
	@PostMapping("/login")
	public String login(@ModelAttribute("taiKhoan") TaiKhoan taiKhoan,
			HttpServletRequest req,
			HttpServletResponse res,
			Model model) {
		List<TaiKhoan> listTaiKhoan = taiKhoanService
				.findByUsernameAndPassword(taiKhoan.getUsername(), taiKhoan.getPassword());
		if(listTaiKhoan.size() > 0) {
			//luu vao session
			HttpSession session = req.getSession();
			session.setAttribute("user", listTaiKhoan.get(0));
			String nameAccount=listTaiKhoan.get(0).getRole().getRoleName();
			if(nameAccount.equals("ROLE_USER")) {
				return "redirect:/user";
			}
			else if(nameAccount.equals("ROLE_ADMIN")){
				return "redirect:/admin";
			}
			else if(nameAccount.equals("ROLE_CREATER"))
			{
				return "redirect:/manageExam";
			}
		}
		model.addAttribute("hasError", "Sai thông tin đăng nhập.");
		return "login";
	}
	
	/**
	 * Register form.
	 *
	 * @param taiKhoan the tai khoan
	 * @return the string
	 */
	@GetMapping("/register")
	public String registerForm(TaiKhoan taiKhoan) {
		return "register";
	}
	
	/**
	 * Register.
	 *
	 * @param taiKhoan the tai khoan
	 * @return the string
	 */
	@PostMapping("/register")
	@ResponseBody
	public ResponseEntity<?>  register(@Valid @RequestBody TaiKhoan taiKhoan, BindingResult bindingResult) {
		//validation data
		AjaxResponseTaiKhoan result = new AjaxResponseTaiKhoan();
		if(bindingResult.hasErrors()) {
			System.out.println("Lỗi validation");
			result.setMsg(bindingResult.getAllErrors().stream().map(x->x.getDefaultMessage())
					.collect(Collectors.joining(",")));
			System.out.println(result.getMsg());
			return ResponseEntity.badRequest().body(result);
		}
		
		
		//when success data
		taiKhoan.setEnable(true);
		Optional<Role> o = roleService.findByRoleName("ROLE_USER");
		Role role = o.get();
		taiKhoan.setRole(role);
		taiKhoan.setUrlAvatar("/image/defaultAvatar.png");
		try {
			taiKhoanService.save(taiKhoan);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Username đã tồn tại");
			result.setMsg("Username đã tồn tại");
			return ResponseEntity.badRequest().body(result);
		}
		return ResponseEntity.ok("");
	}
	
	/**
	 * Commingsoon.
	 *
	 * @return the string
	 */
	@GetMapping("/commingsoon")
	public String commingsoon() {
		return "commingsoon";
	}
		
	/**
	 * Logout account.
	 *
	 * @param req the req
	 * @return the response entity
	 */
	@PostMapping("/logout")
	@ResponseBody
	public ResponseEntity<?> logoutAccount(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.removeAttribute("user");
		return ResponseEntity.ok(null);
	}
	
}
