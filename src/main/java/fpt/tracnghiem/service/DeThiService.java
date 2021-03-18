package fpt.tracnghiem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fpt.tracnghiem.entity.DeThi;
import fpt.tracnghiem.model.ExamInformation;
import fpt.tracnghiem.repository.DeThiRepository;

@Service
public class DeThiService {
	@Autowired
	private DeThiRepository deThiRepository;
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
	
	public Optional<DeThi> findById(int id) {
		return deThiRepository.findById(id);
	}
	
	public void editDeThi(DeThi deThi) {
		deThiRepository.save(deThi);
	}
	
	
}
