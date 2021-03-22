package fpt.tracnghiem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fpt.tracnghiem.entity.CauHoi;
import fpt.tracnghiem.entity.DeThi;
import fpt.tracnghiem.model.ExamInformation;
import fpt.tracnghiem.repository.CauHoiRepository;
import fpt.tracnghiem.repository.DeThiRepository;

@Service
public class DeThiService {
	@Autowired
	private DeThiRepository deThiRepository;
	@Autowired 
	private CauHoiRepository cauHoiRepository;
	
	@Autowired
	private CauHoiService cauHoiService;
	// Phân trang service
	public Page<DeThi> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		return this.deThiRepository.findAll(pageable);
	}
	
	public Iterable<DeThi> findAllDeThi() {
		return deThiRepository.findAll();
	}
	
	public Iterable<ExamInformation> getExamInformation() {
		return deThiRepository.getExamInformation();
	}
	
	public void ThemDeThi(DeThi deThi) {
		 deThiRepository.save(deThi);
	}
	public void DeleteById(int id) {
		deThiRepository.deleteById(id);
	}
	public void deleteByDeThi(DeThi deThi) {
		List<CauHoi> listCauHoi = cauHoiRepository.findByDeThi(deThi);
		if(listCauHoi!=null) {
			if(listCauHoi.size()>0) {
				listCauHoi.forEach(x->{
					cauHoiService.deleteCauHoiByIdCauHoi(x.getIdCauHoi());
				});
			}
		}
		deThiRepository.delete(deThi);
	}
	
	public Optional<DeThi> findById(int id) {
		return deThiRepository.findById(id);
	}
	
	public void editDeThi(DeThi deThi) {
		deThiRepository.save(deThi);
	}
	public List<DeThi> findByTenDeContaining(String tenDe) {
	return	deThiRepository.findByTenDeContaining(tenDe);
	}
	
}
