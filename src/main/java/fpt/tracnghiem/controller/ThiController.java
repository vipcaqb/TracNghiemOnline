package fpt.tracnghiem.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import fpt.tracnghiem.service.CauHoiService;
import fpt.tracnghiem.service.DeThiService;
import fpt.tracnghiem.service.LopService;
import fpt.tracnghiem.service.MonHocService;

@Controller
@RequestMapping(value = "/user")
public class ThiController {
	@Autowired
	private CauHoiService cauHoiService;
	@Autowired
	private DeThiService dethiService;
	

	@Autowired
	private LopService lopService;
	@Autowired
	private MonHocService monHocService;
	
	
	@RequestMapping(value = "/thi/page/{page}",method = RequestMethod.GET)
	public String userInfo(HttpServletRequest req, Model model,@PathVariable(name = "page",required = false) Integer page) {
		if(page == null || page < 1) {
			page=1;
		}
		Page<DeThi> pageDethi = dethiService.findPaginated(page, MyConstances.HOMEPAGE_SIZE);
		List<DeThi> dsDethi = pageDethi.getContent();
		List<MonHoc> listMonhoc = (List<MonHoc>) monHocService.getAllMonHoc();
		List<Lop> listLop = (List<Lop>) lopService.getAllLop();
		
		if(dsDethi.size()==0 && page >1) {
			pageDethi = dethiService.findPaginated(page-1, MyConstances.HOMEPAGE_SIZE);
			dsDethi = pageDethi.getContent();
		}
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", pageDethi.getTotalPages());
		model.addAttribute("totalItems", pageDethi.getTotalElements());
		model.addAttribute("dsDeThi",dsDethi);
		model.addAttribute("listLop", listLop);
		model.addAttribute("listMonHoc", listMonhoc);
		return "user/thi/listExam";
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
	
		mav.addObject("dsDeThi",dsDethi);
		mav.setViewName("user/thi/listExam");
		return mav;
	}
	
	
	@RequestMapping(value="/thi/{idDe}")
	ModelAndView StartExam(@PathVariable int idDe) {
		ModelAndView mav = new ModelAndView();
		List<CauHoi> listCauHoi = cauHoiService.findAllByIdDeThi(idDe);
		Optional<DeThi> deThi = dethiService.findById(idDe);
		if(deThi.isPresent()) {
			mav.addObject("deThi", deThi.get());
			mav.addObject("thoiGian", deThi.get().getThoiGianThi());
		}
		
		
		mav.addObject("listCauHoi", listCauHoi);
		mav.setViewName("/user/thi/startExam");
		return mav;
	}
	

	

	
}
