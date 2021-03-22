package fpt.tracnghiem.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class TaiKhoanController {
	/**
	 * User info.
	 *
	 * @return the string
	 */
	@RequestMapping(value = {"","/manageInfo"},method = RequestMethod.GET)
	public String userInfo(HttpServletRequest req) {
		return "user/info";
	}
	
	
}