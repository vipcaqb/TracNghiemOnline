package fpt.tracnghiem.controller;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fpt.tracnghiem.config.MyConstances;
import fpt.tracnghiem.entity.CauHoi;
import fpt.tracnghiem.entity.DeThi;
import fpt.tracnghiem.entity.Lop;
import fpt.tracnghiem.entity.MonHoc;
import fpt.tracnghiem.entity.Role;
import fpt.tracnghiem.entity.TaiKhoan;
import fpt.tracnghiem.entity.ThamGiaThi;
import fpt.tracnghiem.service.CauHoiService;
import fpt.tracnghiem.service.DeThiService;
import fpt.tracnghiem.service.LopService;
import fpt.tracnghiem.service.MonHocService;
import fpt.tracnghiem.service.RoleService;
import fpt.tracnghiem.service.TaiKhoanService;

@Controller
@RequestMapping(value = "/user")
public class ThiController {
	@Autowired
	private CauHoiService cauHoiService;
	@Autowired
	private DeThiService dethiService;
	@Autowired
	private TaiKhoanService taiKhoanService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private LopService lopService;
	@Autowired
	private MonHocService monHocService;
	
	
	@RequestMapping(value = "/thi/page/{page}",method = RequestMethod.GET)
	public ModelAndView userInfo(HttpServletRequest req,@PathVariable(name = "page",required = false) Integer page) {
		ModelAndView mav = new ModelAndView();
		if(page == null || page < 1) {
			page=1;
		}
		Page<DeThi> pageDethi = dethiService.findPaginated(page, MyConstances.HOMEPAGE_SIZE);
		List<DeThi> dsDethi = pageDethi.getContent();
		if(dsDethi.size()==0 && page >1) {
			pageDethi = dethiService.findPaginated(page-1, MyConstances.HOMEPAGE_SIZE);
			dsDethi = pageDethi.getContent();
		}
	
		mav.addObject("currentPage", page);
		mav.addObject("totalPages", pageDethi.getTotalPages());
		mav.addObject("totalItems", pageDethi.getTotalElements());
		mav.addObject("dsDeThi",dsDethi);
		ShowFormFormListExam(mav);
		return mav;
	}
	
	@PostMapping(value = "/thi/page/{page}")
	public ModelAndView searchDeThi(@Param("keyword") String keyword,HttpServletRequest req, Model model,@PathVariable(name = "page",required = false) Integer page) {
		ModelAndView mav = new ModelAndView();
		List<DeThi> dsDethi;
		if(keyword.equals("")) {
			 dsDethi =(List<DeThi>) dethiService.findAllDeThi();
		}else {
			 dsDethi =(List<DeThi>) dethiService.findByTenDeContaining(keyword);
		}
		ShowFormFormListExam(mav);
		mav.addObject("dsDeThi",dsDethi);
		return mav;
	}
	
	private void ShowFormFormListExam(ModelAndView mav) {
		Optional<Role> role = roleService.findByRoleName(MyConstances.ROLE_USER);
		List<TaiKhoan> Top6TaiKhoan = taiKhoanService.findTop6UserMaxPoint(role.get());
		mav.addObject("TopUser",Top6TaiKhoan);
		List<MonHoc> listMonhoc = (List<MonHoc>) monHocService.getAllMonHoc();
		List<Lop> listLop = (List<Lop>) lopService.getAllLop();
		mav.addObject("listLop", listLop);
		mav.addObject("listMonHoc", listMonhoc);
		mav.setViewName("/user/thi/listExam");
	}
	
	@RequestMapping(value="/thi/{idDe}")
	ModelAndView GetDataToThamGiaThi(HttpServletRequest req,@PathVariable int idDe) {
		ModelAndView mav = new ModelAndView();
		List<CauHoi> listCauHoi = cauHoiService.findAllByIdDeThi(idDe);
		Optional<DeThi> deThi = dethiService.findById(idDe);
		int ThoiGianThi = deThi.get().getThoiGianThi();
		Date date = new Date();
		Timestamp NgayGioKetThuc = new  Timestamp(date.getTime()+ ThoiGianThi *60000);
		HttpSession session = req.getSession();
		session.setAttribute("NgayGioKetThuc", NgayGioKetThuc);
		mav.addObject("listCauHoi", listCauHoi);
		mav.setViewName("redirect:/user/ThamGiaThi/"+idDe);
		return mav;
	}
	
	@RequestMapping(value="/ThamGiaThi/{idDe}")
	ModelAndView StartExam(HttpServletRequest req,@PathVariable int idDe) {
		ModelAndView mav = new ModelAndView();
		List<CauHoi> listCauHoi = cauHoiService.findAllByIdDeThi(idDe);
		Optional<DeThi> deThi = dethiService.findById(idDe);
		if(deThi.isPresent()) {
			mav.addObject("deThi", deThi.get());
			mav.addObject("thoiGian", deThi.get().getThoiGianThi());
		}
		
		HttpSession session = req.getSession();
		Timestamp TimeEnd =(Timestamp) session.getAttribute("NgayGioKetThuc");
		mav.addObject("TimeEnd", TimeEnd);
		mav.addObject("listCauHoi", listCauHoi);
		mav.setViewName("/user/thi/startExam");
		return mav;
	}
	
	@RequestMapping(value = "/findByMonHoc/{idMonHoc}")
	ModelAndView findByMonHoc(HttpServletRequest req,@PathVariable int idMonHoc,@Param("keyword") String keyword) {
		ModelAndView mav = new ModelAndView();
		List<DeThi> dsDethi=null;
		Optional<MonHoc> monHoc = monHocService.FindById(idMonHoc);
		if(monHoc.isPresent()) {
			dsDethi =(List<DeThi>) dethiService.findByMonHoc(monHoc.get());
		}
		if(keyword!=null) 
			dsDethi =dethiService.filterByKeyword(keyword, dsDethi);
		mav.addObject("dsDeThi",dsDethi);
		ShowFormFormListExam(mav);
		return mav;
		
	}
	@RequestMapping(value = "/findByLop/{idLopHoc}")
	ModelAndView findByLopHoc(HttpServletRequest req,@PathVariable int idLopHoc,@Param("keyword") String keyword) {
		ModelAndView mav = new ModelAndView();
		List<DeThi> dsDethi=null;
		Optional<Lop> lop = lopService.findByID(idLopHoc);
		if(lop.isPresent()) {
			dsDethi =(List<DeThi>) dethiService.findByLop(lop.get());
		}
		if(keyword!=null) 
			dsDethi =dethiService.filterByKeyword(keyword, dsDethi);
		mav.addObject("dsDeThi",dsDethi);
		ShowFormFormListExam(mav);
		return mav;
		
	}
	@RequestMapping(value = "/examDetail/{idDe}")
	ModelAndView ShowExamDetail(HttpServletRequest req,@PathVariable int idDe) {
		ModelAndView mav = new ModelAndView();
		Optional<Role> role = roleService.findByRoleName(MyConstances.ROLE_USER);
		Optional<DeThi> deThi = dethiService.findById(idDe);
		List<TaiKhoan> Top6TaiKhoan = taiKhoanService.findTop6UserMaxPoint(role.get());
		mav.addObject("TopUser",Top6TaiKhoan);
		mav.addObject("deThi", deThi.get());
		mav.setViewName("/user/thi/examDetail");
		return mav;
		
	}
	

	
}
