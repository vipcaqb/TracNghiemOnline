package fpt.tracnghiem.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fpt.tracnghiem.entity.CauHoi;
import fpt.tracnghiem.entity.Lop;
import fpt.tracnghiem.entity.MonHoc;
import fpt.tracnghiem.entity.TaiKhoan;
import fpt.tracnghiem.entity.ThamGiaThi;
import fpt.tracnghiem.service.CauHoiService;
import fpt.tracnghiem.service.LopService;
import fpt.tracnghiem.service.MonHocService;
import fpt.tracnghiem.service.ThamGiaThiService;
import jdk.internal.jshell.tool.resources.l10n;



@Controller
public class UserController {
	@Autowired 
	private ThamGiaThiService thamGiaThiService;
	@RequestMapping(value = "/HistoryExam" )
	ModelAndView ShowHistoryStudy(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		if(session.getAttribute("user")!=null) {
			TaiKhoan taiKhoan =(TaiKhoan) session.getAttribute("user");
			Iterable<ThamGiaThi> listThamGiaThi = thamGiaThiService.GetAllThamGiaThi(taiKhoan); 
			mav.addObject("listThamGiaThi",listThamGiaThi);
		}
		
		mav.setViewName("/user/history/historyStudy");
		return mav;
		
	}
}
