package fpt.tracnghiem.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fpt.tracnghiem.entity.CauHoi;
import fpt.tracnghiem.entity.DeThi;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Interface CauHoiRepository.
 */
@Repository
public interface CauHoiRepository extends JpaRepository<CauHoi, Integer> {
	
	/**
	 * Find all by de thi.
	 *
	 * @param deThi the de thi
	 * @param pageable the pageable
	 * @return the page
	 */
	Page<CauHoi> findAllByDeThi(DeThi deThi, Pageable pageable);
	
	/**
	 * Find by de thi.
	 *
	 * @param dethi the dethi
	 * @return the list
	 */
	List<CauHoi> findByDeThi(DeThi dethi);
	
	/**
	 * Find all by de thi.
	 *
	 * @param deThi the de thi
	 * @return the list
	 */
	List<CauHoi> findAllByDeThi(DeThi deThi);

}
