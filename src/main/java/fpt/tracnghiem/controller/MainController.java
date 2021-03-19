package fpt.tracnghiem.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import fpt.tracnghiem.entity.Role;
import fpt.tracnghiem.entity.TaiKhoan;
import fpt.tracnghiem.service.RoleService;
import fpt.tracnghiem.service.TaiKhoanService;

@Controller
public class MainController {
	
	@Autowired
	TaiKhoanService taiKhoanService;
	
	@Autowired 
	RoleService roleService;
	@GetMapping("/")
	public String home() {
		return "index.html";
	}
	
	@GetMapping("/login")
	public String loginForm() {
		
		return "/login";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam(name="username") String username, @RequestParam("password") String password,
			HttpServletRequest req,HttpServletResponse res) {
		System.out.println("Do POST /login");
		List<TaiKhoan> listTaiKhoan = taiKhoanService.findByUsernameAndPassword(username, password);
		if(listTaiKhoan.size() > 0) {
			//luu vao session
			HttpSession session = req.getSession();
			session.setAttribute("user", listTaiKhoan.get(0));
			String nameAccount=listTaiKhoan.get(0).getRole().getRoleName();
			System.out.println(nameAccount);
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
		return "redirect:/login";
	}
	
	@GetMapping("/register")
	public String registerForm(TaiKhoan taiKhoan) {
		return "register";
	}
	
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
	
	@GetMapping("/commingsoon")
	public String commingsoon() {
		return "commingsoon";
	}
		
}
