package fpt.tracnghiem.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fpt.tracnghiem.config.MyConstances;
import fpt.tracnghiem.entity.DeThi;
import fpt.tracnghiem.entity.Lop;
import fpt.tracnghiem.entity.MonHoc;
import fpt.tracnghiem.entity.TaiKhoan;
import fpt.tracnghiem.model.ExamInformation;
import fpt.tracnghiem.service.CauHoiService;
import fpt.tracnghiem.service.DeThiService;
import fpt.tracnghiem.service.LopService;
import fpt.tracnghiem.service.MonHocService;

@Controller
public class DeThiController {
	@Autowired
	private DeThiService deThiService;
	@Autowired
	private LopService lopService;
	@Autowired
	private MonHocService monHocService;
	@Autowired
	private CauHoiService cauHoiService;
	@RequestMapping(value = "/manageExam")
	public String ShowAllContest(Model model) {
//		ModelAndView mav = new ModelAndView();
//		List<DeThi> listExam =(ArrayList<DeThi>) deThiService.findAllDeThi();
//		mav.addObject("listExam", listExam);
//		List<ExamInformation> examInfomations = (ArrayList<ExamInformation>) deThiService.getExamInformation();
//		mav.addObject("listExam", examInfomations);

//		mav.setViewName("/creator/manageExam");
		
		return findPaginated(1, model);
	}

	@GetMapping(value = "/addExam/page/{page}")
	public ModelAndView showFormAdd(@PathVariable int page) {
		ModelAndView mav = new ModelAndView();
		List<Lop> lops = (ArrayList<Lop>) lopService.getAllLop();
		DeThi deThi = new DeThi();
		List<MonHoc> dsMonHoc = (ArrayList<MonHoc>) monHocService.getAllMonHoc();
		mav.addObject("dsMonHoc", dsMonHoc);
		mav.addObject("exam", deThi);
		mav.addObject("Lop", lops);
		mav.addObject("page", page);
		mav.setViewName("/creator/exam/addExam");
		return mav;
	}

	@GetMapping(value = "/editExam/{id}/page/{page}")
	public ModelAndView editShowForm(@PathVariable int id,@PathVariable int page) {
		ModelAndView mav = new ModelAndView();
		Optional<DeThi> deThi = deThiService.findById(id);
		if (deThi.isPresent()) {
			mav.addObject("exam", deThi.get());
			List<Lop> lops = (ArrayList<Lop>) lopService.getAllLop();
			List<MonHoc> dsMonHoc = (ArrayList<MonHoc>) monHocService.getAllMonHoc();
			mav.addObject("dsMonHoc", dsMonHoc);
			mav.addObject("Lop", lops);

		} else {
			mav.setViewName("redirect:/manageExam");
		}
		mav.setViewName("/creator/exam/editExam");
		return mav;
	}
	@PostMapping(value = "/editExam/{id}/page/{page}")
	public ModelAndView editExamSubmit(HttpServletRequest request, HttpServletResponse response, @ModelAttribute DeThi deThi,@PathVariable int id,@PathVariable int page){
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("user");
		if (taiKhoan != null) {
			Optional<DeThi> de = deThiService.findById(id);
			deThi.setNgayTao(de.get().getNgayTao());
			deThi.setIdDe(id);
			deThi.setTaiKhoan(taiKhoan);
			deThiService.editDeThi(deThi);
		
		}
		mav.setViewName("redirect:/manageExam/page/"+page);
		return mav;
	}

	@PostMapping(value = "/addExam/page/{page}")
	public ModelAndView addExam(HttpServletRequest request, HttpServletResponse response, @ModelAttribute DeThi deThi,@PathVariable int page) {
		HttpSession session = request.getSession();
		TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("user");
		if (taiKhoan != null) {
			deThi.setTaiKhoan(taiKhoan);
			deThi.setNgayTao(new Date());
			deThiService.ThemDeThi(deThi);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/manageExam/page/"+page);
		return mav;
	}

	@RequestMapping(value = "deleteExam/{id}/page/{page}")
	public ModelAndView deleteById(@PathVariable int id,@PathVariable int page) {
		ModelAndView mav = new ModelAndView();
		Optional<DeThi> deThi = deThiService.findById(id);
		
		deThiService.deleteByDeThi(deThi.get());
		mav.setViewName("redirect:/manageExam/page/"+page);
		
		return mav;
	}
	// pagination 
	@GetMapping("/manageExam/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
	    int pageSize = MyConstances.PAGE_SIZE;
	    Page <DeThi> page = deThiService.findPaginated(pageNo, pageSize);
	    List<DeThi> listDeThis = page.getContent();
	    if(listDeThis.size()==0 && pageNo>1) {
	    	page =deThiService.findPaginated(pageNo - 1, pageSize);
	    	listDeThis = page.getContent();
	    }
	   // Page<DeThi> page = deThiService.findPaginated(pageNo, pageSize);
	    //List<DeThi> examInformations = page.getContent();
	    //List<DeThi> examInformations = page.getContent();
	   // List<DeThi> examInfomations = (ArrayList<ExamInformation>) deThiService.getExamInformation();
	    //examInfomations = page.getContent();
	    model.addAttribute("listExam", listDeThis);
	    model.addAttribute("currentPage", pageNo);
	    model.addAttribute("totalPages", page.getTotalPages());
	    model.addAttribute("totalItems", page.getTotalElements());
		return "creator/exam/manageExam";
	}
}
