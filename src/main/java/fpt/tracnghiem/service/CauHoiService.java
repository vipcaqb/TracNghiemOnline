package fpt.tracnghiem.service;


import java.util.Iterator;
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

// TODO: Auto-generated Javadoc
/**
 * The Class CauHoiService.
 */
@Service
public class CauHoiService {
	
	/** The anh repository. */
	@Autowired
	private AnhRepository anhRepository;
	
	/** The phuong an repository. */
	@Autowired
	private PhuongAnRepository phuongAnRepository;
	
	/** The cau hoi repository. */
	@Autowired
	private CauHoiRepository cauHoiRepository;
	
	/** The de thi repository. */
	@Autowired
	private DeThiRepository deThiRepository;
	
	/**
	 * Lưu cauHoi, listPhuongAn, listAnh.
	 *
	 * @param cauHoi the cau hoi
	 * @param listPhuongAn the list phuong an
	 * @param listAnh the list anh
	 * @param idDe the id de
	 */
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
	 * tìm tất cả câu hỏi có idDe và nằm ở trang thứ pageNumber.
	 *
	 * @param idDe the id de
	 * @return the list
	 */
	
	public List<CauHoi> findAllByIdDeThi(Integer idDe){
		Optional<DeThi> oDeThi = deThiRepository.findById(idDe);
		if(oDeThi.isEmpty()) {
			throw new NotFoundException("Không tìm thấy Đề thi có id = "+ idDe);
		}
		DeThi deThi = oDeThi.get();
		
		return cauHoiRepository.findAllByDeThi(deThi);
	}
	
	/**
	 * Find all by id de thi.
	 *
	 * @param idDe the id de
	 * @param pageNumber the page number
	 * @return the page
	 */
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
	 * Xóa câu hỏi và dữ liệu của tất cả các bảng có tham chiếu đến câu hỏi đó.
	 *
	 * @param idCauHoi the id cau hoi
	 */
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
	
	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the optional
	 */
	public Optional<CauHoi> findById(Integer id) {
		return cauHoiRepository.findById(id);
	}
	
	/**
	 * Cập nhật thông tin của câu hỏi và các thông tin tham chiếu đến nó.
	 *
	 * @param cauHoi the cau hoi
	 * @param listPhuongAn the list phuong an
	 */
	@Transactional
	public void update(CauHoi cauHoi,List<PhuongAn> listPhuongAn) {
		cauHoiRepository.save(cauHoi);
		listPhuongAn.forEach(x->{
			phuongAnRepository.save(x);
		});
	}
	
	/**
	 * Count by id de.
	 *
	 * @param id the id
	 * @return the int
	 */
	public int CountByIdDe(int id) {
		return cauHoiRepository.countByIdDeThi(id);
	}
	
	/**
	 * Checks if is only one correct answer.
	 *
	 * @param cauHoi the cau hoi
	 * @return the boolean
	 */
	public static Boolean isOnlyOneCorrect(CauHoi cauHoi) {
		List<PhuongAn> listPhuongAn = cauHoi.getPhuongAns();
		int count = 0;
		for (PhuongAn phuongAn : listPhuongAn) {
			if(phuongAn.getIsCorrect()) {
				count++;
			}
		}
		if(count==1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public List<CauHoi> findAll(){
		return cauHoiRepository.findAll();
	}
	
	public boolean isCorrectAnswer(CauHoi cauHoiYeuCau) {
		boolean correct= true;
		List<PhuongAn> listPhuongAn = cauHoiYeuCau.getPhuongAns();
		for (PhuongAn phuongAn : listPhuongAn) {
			PhuongAn phuongAnTuongUng = phuongAnRepository.findById(phuongAn.getIdPhuongAn()).get();
			if(phuongAn.getIsCorrect()!= phuongAnTuongUng.getIsCorrect()) {
				correct = false;
			}
		}
		
		return correct;
		
	}
}
