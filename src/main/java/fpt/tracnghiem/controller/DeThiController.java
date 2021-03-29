package fpt.tracnghiem.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
import fpt.tracnghiem.service.CauHoiService;
import fpt.tracnghiem.service.DeThiService;
import fpt.tracnghiem.service.LopService;
import fpt.tracnghiem.service.MonHocService;

// TODO: Auto-generated Javadoc
/**
 * The Class DeThiController.
 */
@Controller
public class DeThiController {
	
	/** The de thi service. */
	@Autowired
	private DeThiService deThiService;
	
	/** The lop service. */
	@Autowired
	private LopService lopService;
	
	/** The mon hoc service. */
	@Autowired
	private MonHocService monHocService;

	/**
	 * Hiển thị danh sách các đề thi.
	 *
	 * @param model the model
	 * @return the string
	 */
	
	@Autowired
	private CauHoiService cauHoiService;
//	@RequestMapping(value = "/manageExam")
//	public String ShowAllContest(Model model) {
//		return findPaginated(1, model);
//	}
	
	/**
	 * Hiển thị giao diện thêm đề thi.
	 *
	 * @return the model and view
	 */

	@GetMapping(value = {"/addExam/page/{page}","/addExam"})
	public ModelAndView showFormAdd(@PathVariable(required =  false) Integer page) {
		ModelAndView mav = new ModelAndView();
		List<Lop> lops = (ArrayList<Lop>) lopService.getAllLop();
		DeThi deThi = new DeThi();
		List<MonHoc> dsMonHoc = (ArrayList<MonHoc>) monHocService.getAllMonHoc();
		mav.addObject("dsMonHoc", dsMonHoc);
		mav.addObject("deThi", deThi);
		mav.addObject("Lop", lops);
		if(page!=null) {
			mav.addObject("page", page);
		}
		else {
			mav.addObject("page", 1);
		}
		mav.setViewName("/creator/exam/addExam");
		return mav;
	}
	
	/**
	 * Hiển thị giao diện sửa câu hỏi.
	 *
	 * @param id the id
	 * @return the model and view
	 */

	@GetMapping(value = {"/editExam/{id}/page/{page}","/editExam/{id}"})
	public ModelAndView editShowForm(@PathVariable int id,@PathVariable(required = false) Integer page) {
		ModelAndView mav = new ModelAndView();
		Optional<DeThi> deThi = deThiService.findById(id);
		if (deThi.isPresent()) {
			mav.addObject("exam", deThi.get());
			List<Lop> lops = (ArrayList<Lop>) lopService.getAllLop();
			List<MonHoc> dsMonHoc = (ArrayList<MonHoc>) monHocService.getAllMonHoc();
			mav.addObject("dsMonHoc", dsMonHoc);
			mav.addObject("Lop", lops);

		} else {
			if(page!=null) {
				mav.setViewName("redirect:/manageExam/page/"+page);
			}
			else {
				mav.setViewName("redirect:/manageExam");
			}
			
		}
		mav.setViewName("/creator/exam/editExam");
		return mav;
	}

  /**
	 * Thực hiện sửa câu hỏi .
	 *
	 * @param request the request
	 * @param response the response
	 * @param deThi the de thi
	 * @param id the id
	 * @return the model and view
	 */
	@PostMapping(value = {"/editExam/{id}/page/{page}","/editExam/{id}"})
	public ModelAndView editExamSubmit(HttpServletRequest request, HttpServletResponse response
			, @ModelAttribute DeThi deThi,@PathVariable int id,@PathVariable(required = false) Integer page){

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
		if(page!=null) {
			mav.setViewName("redirect:/manageExam/page/"+page);
		}
		else {
			mav.setViewName("redirect:/manageExam/page/"+1);
		}
		return mav;
	}
	/**
	 * Adds the exam.
	 *
	 * @param request the request
	 * @param response the response
	 * @param deThi the de thi
	 * @return the model and view
	 */
	// bindingResulf phải nằm ngay sau Valid
		@PostMapping(value = { "/addExam/page/{page}", "/addExam" })
		public ModelAndView addExam(@ModelAttribute("deThi") @Valid DeThi deThi,
				BindingResult bindingResult,
				HttpServletRequest request,
				HttpServletResponse response, 
				@PathVariable(required = false) Integer page) {
			//DeThi deThi = new DeThi();
			System.out.println(deThi);
			HttpSession session = request.getSession();
			ModelAndView mav = new ModelAndView();
			TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("user");
			if (taiKhoan != null) {
				//deThi.getTenDe()
				deThi.setTaiKhoan(taiKhoan);
				deThi.setNgayTao(new Date());
				
			}
			if (bindingResult.hasErrors()) {
				System.out.println("truong hop mac loi");
				// ModelAndView mav = new ModelAndView();
				List<Lop> lops = (ArrayList<Lop>) lopService.getAllLop();		 
				List<MonHoc> dsMonHoc = (ArrayList<MonHoc>) monHocService.getAllMonHoc();
				mav.addObject("dsMonHoc", dsMonHoc);
				//mav.addObject("deThi", deThi);
				mav.addObject("Lop", lops);
				if (page != null) {
					mav.addObject("page", page);
				} else {
					mav.addObject("page", 1);
				}
				mav.setViewName("/creator/exam/addExam");
				return mav;
			} else {
				if (page != null) {
					mav.setViewName("redirect:/manageExam/page/" + page);
				} else {
					mav.setViewName("redirect:/manageExam");
				}
				deThiService.ThemDeThi(deThi);
				return mav;
			}
		}

  /**
	 * Delete by id.
	 *
	 * @param id the id
	 * @return the model and view
	 */
	@RequestMapping(value = {"deleteExam/{id}/page/{page}","deleteExam/{id}"})
	public ModelAndView deleteById(@PathVariable int id,@PathVariable(required = false) Integer page) {

		ModelAndView mav = new ModelAndView();
		Optional<DeThi> deThi = deThiService.findById(id);
		
		deThiService.deleteByDeThi(deThi.get());
		if(page!=null) {
			mav.setViewName("redirect:/manageExam/page/"+page);
		}
		else {
			mav.setViewName("redirect:/manageExam");
		}
		return mav;
	}
	
	/**
	 * Find paginated.
	 *
	 * @param pageNo the page no
	 * @param model the model
	 * @return the string
	 */
	@GetMapping(value = {"/manageExam/page/{pageNo}","/manageExam"})
	public String findPaginated(@PathVariable(value = "pageNo",required = false) Integer pageNo, Model model,HttpServletRequest req) {
		HttpSession session = req.getSession();
		if(session.getAttribute("user") == null) {
			return "redirect:/login";
		}
		
		TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("user");
		String username = taiKhoan.getUsername();
		
	    int pageSize = MyConstances.PAGE_SIZE;
	    Page <DeThi> page;
	    if(pageNo!=null) {
	    	if(taiKhoan.getRole().getRoleName().equals("ROLE_ADMIN")) {
	    		page = deThiService.findPaginated(pageNo, pageSize);
	    	}
	    	else {
	    		page= deThiService.findPaginatedByUsername(pageNo, pageSize,username);
	    	}
	    }
	    else {
	    	if(taiKhoan.getRole().getRoleName().equals("ROLE_ADMIN")) {
	    		page = deThiService.findPaginated(1, pageSize);
	    	}
	    	else {
	    		page= deThiService.findPaginatedByUsername(1, pageSize,username);
	    	}
	    }
	    List<DeThi> listDeThis = page.getContent();
	    
	    model.addAttribute("listExam", listDeThis);
	    if(pageNo!=null) {
	    	model.addAttribute("currentPage", pageNo);
	    }
	    else {
	    	model.addAttribute("currentPage", 1);
	    }
	    model.addAttribute("totalPages", page.getTotalPages());
	    model.addAttribute("totalItems", page.getTotalElements());

		return "creator/exam/manageExam";
	}
}
