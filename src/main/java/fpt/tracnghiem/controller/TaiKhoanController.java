package fpt.tracnghiem.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fpt.tracnghiem.entity.TaiKhoan;
import fpt.tracnghiem.service.TaiKhoanService;

// TODO: Auto-generated Javadoc
/**
 * The Class TaiKhoanController.
 */
@Controller
@RequestMapping("/user")
public class TaiKhoanController {
	/**
	 * User info.
	 *
	 * @return the string
	 */
	@Autowired
	TaiKhoanService taiKhoanService;
	
	/**
	 * User info.
	 *
	 * @param req the req
	 * @return the string
	 */
	@RequestMapping(value = {"","/manageInfo"},method = RequestMethod.GET)
	public String userInfo(HttpServletRequest req) {
		return "user/info";
	}
	
	/**
	 * Edits the info form.
	 *
	 * @return the string
	 */
	@GetMapping(value = "/edit")
	public String editInfoForm() {
		return "user/editinfo";
	}
	
	/**
	 * Edits the info.
	 *
	 * @param hoVaTen the ho va ten
	 * @param email the email
	 * @param sdt the sdt
	 * @param ngaySinh the ngay sinh
	 * @param req the req
	 * @return the string
	 */
	@PostMapping(value = "/edit")
	public String editInfo(
			@RequestParam(name = "hoVaTen") String hoVaTen,
			@RequestParam(name = "email") String email,
			@RequestParam(name = "sdt") String sdt,
			@RequestParam(name = "ngaySinh") String ngaySinh,
			HttpServletRequest req) {
		HttpSession session = req.getSession();
		TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("user");
		taiKhoan.setHoVaTen(hoVaTen);
		taiKhoan.setEmail(email);
		taiKhoan.setSdt(sdt);
		try {
			Date dNgaySinh = new SimpleDateFormat("yyyy-MM-dd").parse(ngaySinh);
			taiKhoan.setNgaySinh(dNgaySinh);
			taiKhoanService.update(taiKhoan);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return "redirect:/user";
	}
	
	/**
	 * Change password.
	 *
	 * @param password the password
	 * @param newPassword the new password
	 * @return the string
	 */
	@PostMapping(value = "/changepassword")
	public String changePassword(
			@RequestParam(name="password") String password,
			@RequestParam(name="newPassword") String newPassword,
			HttpServletRequest req
			) {
		HttpSession session = req.getSession();
		if(session.getAttribute("user")!=null) {
			TaiKhoan taiKhoan = (TaiKhoan)session.getAttribute("user");
			if(password.equals(taiKhoan.getPassword())) {
				taiKhoan.setPassword(newPassword);
				taiKhoanService.update(taiKhoan);
				System.out.println(password + " " + newPassword);
				
			}
			return "redirect:/user";
		}
		else {
			return "redirect:/login";
		}
		
	}
}
