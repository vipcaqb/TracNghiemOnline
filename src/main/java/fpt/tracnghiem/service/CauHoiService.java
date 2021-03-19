package fpt.tracnghiem.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import fpt.tracnghiem.config.MyConstances;
import fpt.tracnghiem.entity.Anh;
import fpt.tracnghiem.entity.CauHoi;
import fpt.tracnghiem.entity.DeThi;
import fpt.tracnghiem.entity.PhuongAn;
import fpt.tracnghiem.repository.AnhRepository;
import fpt.tracnghiem.repository.CauHoiRepository;
import fpt.tracnghiem.repository.DeThiRepository;
import fpt.tracnghiem.repository.PhuongAnRepository;

@Service
public class CauHoiService {
	@Autowired
	private AnhRepository anhRepository;
	@Autowired
	private PhuongAnRepository phuongAnRepository;
	@Autowired
	private CauHoiRepository cauHoiRepository;
	@Autowired
	private DeThiRepository deThiRepository;
	
	/**
	 * Lưu cauHoi, listPhuongAn, listAnh
	 * */
	@Transactional
	public void save(CauHoi cauHoi,List<PhuongAn> listPhuongAn, List<Anh> listAnh,Integer idDe){
		Optional<DeThi> oDeThi = deThiRepository.findById(idDe);
		if(oDeThi.isEmpty()) {
			throw new NotFoundException("Không tìm thấy Đề thi có id = "+ idDe);
		}
		DeThi deThi = oDeThi.get();
		
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
		cauHoi.setDeThi(deThi);
		if(cauHoi!=null) {
			cauHoiRepository.save(cauHoi);
		}
		System.out.println("upload thanh cong");
	}
	/**
	 * tìm tất cả câu hỏi có idDe và nằm ở trang thứ pageNumber
	 * */
	public Page<CauHoi> findAllByIdDeThi(Integer idDe,int pageNumber ){
		Optional<DeThi> oDeThi = deThiRepository.findById(idDe);
		if(oDeThi.isEmpty()) {
			throw new NotFoundException("Không tìm thấy Đề thi có id = "+ idDe);
		}
		DeThi deThi = oDeThi.get();
		Pageable pageable = PageRequest.of(pageNumber, MyConstances.PAGE_SIZE);
		return cauHoiRepository.findAllByDeThi(deThi, pageable);
	}
	/**
	 * Xóa câu hỏi và dữ liệu của tất cả các bảng có tham chiếu đến câu hỏi đó
	 * */
	@Transactional
	public void deleteCauHoiByIdCauHoi(Integer idCauHoi) {
		Optional<CauHoi> oCauHoi = cauHoiRepository.findById(idCauHoi);
		CauHoi cauHoi;
		if(oCauHoi.isPresent()) {
			cauHoi = oCauHoi.get();
		}
		else {
			throw new NotFoundException("Câu hỏi có id = "+idCauHoi+" không tồn tại.");
		}
		//Xóa các ảnh liên quan
		List<Anh> listAnh = anhRepository.findByCauHoi(cauHoi);
		if(listAnh.size()>0) {
			listAnh.forEach(x->{
				anhRepository.delete(x);
			});
		}
		//Xóa các phương án liên quan
		List<PhuongAn> listPhuongAn = phuongAnRepository.findByCauHoi(cauHoi);
		if(listPhuongAn.size()>0) {
			listPhuongAn.forEach(x->{
				phuongAnRepository.delete(x);
			});
		}
		//Xóa câu hỏi
		cauHoiRepository.delete(cauHoi);
		System.out.println("Xóa câu hỏi và các thông tin liên quan thành công!");
	}

}
