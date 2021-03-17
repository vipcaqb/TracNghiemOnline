package fpt.tracnghiem.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import fpt.tracnghiem.entity.Anh;
import fpt.tracnghiem.entity.CauHoi;
import fpt.tracnghiem.entity.PhuongAn;
import fpt.tracnghiem.service.CauHoiService;

@RestController
public class TestController {
	@Autowired
	CauHoiService cauHoiService;
	@PostMapping("/testCauHoi")
	public String test() {
		CauHoi cauHoi = new CauHoi();
		cauHoi.setGiaiThich("Đơn giản vkl");
		cauHoi.setNoiDung("Ai là người đầu tiên lên mặt trăng?");
		List<PhuongAn> listPhuongAn = new ArrayList<PhuongAn>();
		listPhuongAn.add(new PhuongAn("ABC",false,cauHoi));
		listPhuongAn.add(new PhuongAn("DEF",false,cauHoi));
		listPhuongAn.add(new PhuongAn("AAA",false,cauHoi));
		listPhuongAn.add(new PhuongAn("XXX",true,cauHoi));
		List<Anh> listAnh = new ArrayList<Anh>();
		Anh a1 = new Anh();
		a1.setIdAnh(1);
		a1.setUrl("aaa");
		a1.setCauHoi(cauHoi);
		
		Anh a2 = new Anh();
		a2.setIdAnh(1);
		a2.setUrl("bbb");
		a2.setCauHoi(cauHoi);
		cauHoiService.save(cauHoi, listPhuongAn, listAnh);
		return "OK";
	}
}
