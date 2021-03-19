package fpt.tracnghiem.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.xml.bind.v2.TODO;

import fpt.tracnghiem.entity.CauHoi;
import fpt.tracnghiem.entity.PhuongAn;
import fpt.tracnghiem.model.MyCounter;
import fpt.tracnghiem.service.CauHoiService;

@Controller
public class CauHoiController {
	@Autowired
	CauHoiService cauHoiService;
	/**
	 * Tải giao diện hiển thị tất cả các câu hoi của đề thi
	 * */
	@RequestMapping(value = {"/manageExam/{idExam}/manageQuestion/{pageNumber}",
			"/manageExam/{idExam}/manageQuestion"}, method = RequestMethod.GET)
	public String manageQuestionUI(@PathVariable(name = "idExam") Integer idDe,
			@PathVariable(name = "pageNumber",required = false) Integer pageNumber, ModelMap model) {
		if (pageNumber == null) {
			pageNumber = 1;
		}
		if (pageNumber < 1) {
			pageNumber = 1;
		}
		Page<CauHoi> pageCauHoi = cauHoiService.findAllByIdDeThi(idDe, pageNumber - 1);
		model.addAttribute("pageCauHoi",pageCauHoi);
		model.addAttribute("idDe",idDe);
		model.addAttribute("pageNumber",pageNumber);
		model.addAttribute("hasPrevios",pageCauHoi.hasPrevious());
		model.addAttribute("hasNext",pageCauHoi.hasNext());
		return "creator/question/manageQuestion";
	}
	/**
	 * tải giao diện thêm câu hỏi
	 * */
	@RequestMapping(value="/manageExam/{idExam}/addQuestion",method = RequestMethod.GET)
	public String addQuestionUI(@PathVariable(name = "idExam") Integer idDe,CauHoi cauHoi,Model model) {
		List<CauHoi> listCauHoi = cauHoiService.findAllByIdDeThi(idDe);
		
		MyCounter myCounter = new MyCounter();
		
		model.addAttribute("myCounter",myCounter);
		model.addAttribute("idDe", idDe);
		model.addAttribute("listCauHoi",listCauHoi);
		return "creator/question/addQuestion";
	}
	/**
	 * Tải giao diện sửa câu hỏi
	 * */
	@RequestMapping(value = "/manageExam/{idExam}/editQuestion/{idQuestion}",method= RequestMethod.GET)
	public String editQuestionUI(@PathVariable(name = "idExam") Integer idDe,
			@PathVariable(name = "idQuestion") Integer idCauHoi,
			Model model)
	{
		//kiểm tra xem câu hỏi hiện tại đã có trong db chưa, nếu chưa thì trả về lỗi 404
		Optional<CauHoi> oCauHoi = cauHoiService.findById(idCauHoi);
		if(oCauHoi.isEmpty()) {
			return "404";
		}
		List<CauHoi> listCauHoi = cauHoiService.findAllByIdDeThi(idDe);
		model.addAttribute("listCauHoi",listCauHoi);
		
		CauHoi cauHoi = oCauHoi.get();
		
		MyCounter myCounter = new MyCounter();
		MyCounter correctCounter = new MyCounter(0);
		model.addAttribute("myCounter",myCounter);
		model.addAttribute("cauHoi", cauHoi);
		model.addAttribute("idDe", idDe);
		model.addAttribute("correctCounter",correctCounter);
		return "creator/question/editQuestion";
	}
	/**
	 * Thực hiện sửa câu hỏi
	 * 
	 * */
	
	@RequestMapping(value = "editQuestion",method= RequestMethod.POST)
	@ResponseBody
	public String editQuestion(@ModelAttribute(name = "cauHoi") CauHoi cauHoi,
			@RequestParam(name = "phuongAn") List<String> listNoiDungPhuongAn,
			@RequestParam(name = "isCorrect",required = false) List<Integer> listCorrect) {
		System.out.println(cauHoi.toString());
		
		Optional<CauHoi> oCauHoi = cauHoiService.findById(cauHoi.getIdCauHoi());
		if(oCauHoi.isEmpty()) {
			return "404";
		}
		CauHoi cauHoiHienTai = oCauHoi.get();
		
		cauHoiHienTai.setNoiDung(cauHoi.getNoiDung());
		cauHoiHienTai.setGiaiThich(cauHoi.getGiaiThich());
		
		//Đặt các giá trị correct
		List<PhuongAn> listPhuongAnHienTai = cauHoiHienTai.getPhuongAns();
		for(int i = 0 ; i<listPhuongAnHienTai.size();i++) {
			listPhuongAnHienTai.get(i).setNoiDung(listNoiDungPhuongAn.get(i));
			listPhuongAnHienTai.get(i).setIsCorrect(false);
			for(int j =0;j<listCorrect.size();j++) {
				if(listCorrect.get(j)==i) {
					listPhuongAnHienTai.get(i).setIsCorrect(true);
					break;
				}
			}
		}
		listPhuongAnHienTai.forEach(x->{
			System.out.println(x.getIsCorrect());
		});
		//Thêm vào db
		cauHoiService.save(cauHoiHienTai, listPhuongAnHienTai, null, cauHoiHienTai.getDeThi().getIdDe());
		
		return "OK";
	}
	
	
	/**
	 * Tạo câu hỏi mới
	 * */
	
	@RequestMapping(value="/manageExam/{idExam}/addQuestion",method = RequestMethod.POST)
	public String addQuestion(@PathVariable(name = "idExam") Integer idDe,
			@ModelAttribute("cauHoi") CauHoi cauHoi,
			@RequestParam(name = "phuongAn") List<String> listNoiDungPhuongAn,
			@RequestParam(name = "isCorrect",required = false) List<Integer> listCorrect
			) {
		int size;
		//Thêm phương án
		List<PhuongAn> listPhuongAn = new ArrayList<PhuongAn>();
		if(listNoiDungPhuongAn.size()>0) {
			size = listNoiDungPhuongAn.size();
			for(Integer i= 0 ; i<size ; i++) {
				String noiDung = listNoiDungPhuongAn.get(i);
				Boolean isCorrect = false;
				if(listCorrect!=null) {
					for (Integer item : listCorrect) {
						if(i==item) {
							isCorrect = true;
						}
						else {
							isCorrect=false;
						}
					}
				}
				listPhuongAn.add(new PhuongAn(noiDung,isCorrect,cauHoi));
			}
		}
		//Thêm ảnh
		
		//Thêm câu hỏi
		cauHoiService.save(cauHoi, listPhuongAn, null, idDe);
		return "commingsoon";
	}
	
	@PostMapping("/deleteQuestion/{idQuestion}")
	public String deleteQuestion(@PathVariable(name = "idQuestion") Integer idCauHoi) {
		Optional<CauHoi> oCauHoi = cauHoiService.findById(idCauHoi);
		CauHoi cauHoi = null;
		if(oCauHoi.isPresent()) {
			cauHoi = oCauHoi.get();
		}
		cauHoiService.deleteCauHoiByIdCauHoi(idCauHoi);
		return "redirect:/manageExam/"+cauHoi.getDeThi().getIdDe()+"/manageQuestion";
	}
}
