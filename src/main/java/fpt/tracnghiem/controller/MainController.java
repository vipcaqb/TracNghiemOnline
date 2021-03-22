package fpt.tracnghiem.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fpt.tracnghiem.entity.Role;
import fpt.tracnghiem.entity.TaiKhoan;
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
	public String login(@RequestParam(name="username") String username, 
			@RequestParam("password") String password,
			HttpServletRequest req,HttpServletResponse res) {
		List<TaiKhoan> listTaiKhoan = taiKhoanService.findByUsernameAndPassword(username, password);
		if(listTaiKhoan.size() > 0) {
			System.out.println(listTaiKhoan.get(0));
			//luu vao session
			HttpSession session = req.getSession();
			session.setAttribute("user", listTaiKhoan.get(0));
			String nameAccount=listTaiKhoan.get(0).getRole().getRoleName();
		
			if(nameAccount.equals("ROLE_USER")) {
				return "redirect:/user/thi/page/1";
			}
			else if(nameAccount.equals("ROLE_ADMIN")){
				return "redirect:/admin";
			}
			else if(nameAccount.equals("ROLE_CREATER"))
			{
				return "redirect:/manageExam";
			}
		}
		return "redirect:/login";
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
	public String  register(@ModelAttribute("taiKhoan") TaiKhoan taiKhoan) {
		System.out.println("POST /register");
		taiKhoan.setEnable(true);
		Optional<Role> o = roleService.findByRoleName("ROLE_USER");
		if(o.isEmpty()) {
			return "redirect:/register";
		}
		Role role = o.get();
		taiKhoan.setRole(role);
		System.out.println(taiKhoan.getUsername());
		taiKhoan.setUrlAvatar("/img/defaultAvatar.jpg");
		try {
			taiKhoanService.save(taiKhoan);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/register";
		}
		return "redirect:/login";
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
