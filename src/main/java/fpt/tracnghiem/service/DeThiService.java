package fpt.tracnghiem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fpt.tracnghiem.entity.CauHoi;
import fpt.tracnghiem.entity.DeThi;
import fpt.tracnghiem.entity.Lop;
import fpt.tracnghiem.entity.MonHoc;
import fpt.tracnghiem.model.ExamInformation;
import fpt.tracnghiem.repository.CauHoiRepository;
import fpt.tracnghiem.repository.DeThiRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class DeThiService.
 */
@Service
public class DeThiService {
	
	/** The de thi repository. */
	@Autowired
	private DeThiRepository deThiRepository;
	
	/** The cau hoi repository. */
	@Autowired 
	private CauHoiRepository cauHoiRepository;
	
	/** The cau hoi service. */
	@Autowired
	private CauHoiService cauHoiService;
	
	/**
	 * Find paginated.
	 *
	 * @param pageNo the page no
	 * @param pageSize the page size
	 * @return the page
	 */
	public Page<DeThi> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		return this.deThiRepository.findAll(pageable);
	}
	
	/**
	 * Find all de thi.
	 *
	 * @return the iterable
	 */
	public Iterable<DeThi> findAllDeThi() {
		return deThiRepository.findAll();
	}
	
	/**
	 * Gets the exam information.
	 *
	 * @return the exam information
	 */
	public Iterable<ExamInformation> getExamInformation() {
		return deThiRepository.getExamInformation();
	}
	
	/**
	 * Them de thi.
	 *
	 * @param deThi the de thi
	 */
	public void ThemDeThi(DeThi deThi) {
		 deThiRepository.save(deThi);
	}
	
	/**
	 * Delete by id.
	 *
	 * @param id the id
	 */
	public void DeleteById(int id) {
		deThiRepository.deleteById(id);
	}
	
	/**
	 * Delete by de thi.
	 *
	 * @param deThi the de thi
	 */
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
	
	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the optional
	 */
	public Optional<DeThi> findById(int id) {
		return deThiRepository.findById(id);
	}
	
	/**
	 * Edits the de thi.
	 *
	 * @param deThi the de thi
	 */
	public void editDeThi(DeThi deThi) {
		deThiRepository.save(deThi);
	}
	
	/**
	 * Find by ten de containing.
	 *
	 * @param tenDe the ten de
	 * @return the list
	 */
	public List<DeThi> findByTenDeContaining(String tenDe) {
	return	deThiRepository.findByTenDeContaining(tenDe);
	}
	
	/**
	 * Find by mon hoc.
	 *
	 * @param monHoc the mon hoc
	 * @return the list
	 */
	public List<DeThi> findByMonHoc(MonHoc monHoc) {
		return	deThiRepository.findByMonHoc(monHoc);
	}
	
	/**
	 * Find by lop.
	 *
	 * @param lop the lop
	 * @return the list
	 */
	public List<DeThi> findByLop(Lop lop) {
		return	deThiRepository.findByLop(lop);
	}
	
	/**
	 * Filter by keyword.
	 *
	 * @param keyWord the key word
	 * @param ListDeThi the list de thi
	 * @return the list
	 */
	public List<DeThi> filterByKeyword(String keyWord, List<DeThi> ListDeThi){
		List<DeThi> temp = new ArrayList<DeThi>();
		ListDeThi.forEach(x->{
			if(x.getTenDe().contains(keyWord))
				temp.add(x);
		});
		return temp;
	}
	
}
