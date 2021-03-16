package fpt.tracnghiem.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fpt.tracnghiem.entity.Anh;
import fpt.tracnghiem.entity.CauHoi;
import fpt.tracnghiem.entity.PhuongAn;
import fpt.tracnghiem.repository.AnhRepository;
import fpt.tracnghiem.repository.CauHoiRepository;
import fpt.tracnghiem.repository.PhuongAnRepository;

@Service
public class CauHoiService {
	@Autowired
	private AnhRepository anhRepository;
	@Autowired
	private PhuongAnRepository phuongAnRepository;
	@Autowired
	private CauHoiRepository cauHoiRepository;
	
	/**
	 * Lưu cauHoi, listPhuongAn, listAnh
	 * */
	@Transactional
	public void save(CauHoi cauHoi,List<PhuongAn> listPhuongAn, List<Anh> listAnh){
		//Lưu danh sách ảnh nếu có
		if(listAnh!=null) {
			if(listAnh.size()>0) {
				listAnh.forEach(x->{
					if(!x.getUrl().isBlank()) {
						anhRepository.save(x);
					}
				});
			}
		}
		//Lưu danh sách đáp án
		if(listPhuongAn!=null) {
			if(listPhuongAn.size()>0) {
				listPhuongAn.forEach(x->{
					phuongAnRepository.save(x);
				});
			}
		}
		
		if(cauHoi!=null) {
			cauHoiRepository.save(cauHoi);
		}
		System.out.println("upload thanh cong");
	}
	
}
