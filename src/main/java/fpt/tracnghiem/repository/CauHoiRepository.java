package fpt.tracnghiem.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fpt.tracnghiem.entity.CauHoi;
import fpt.tracnghiem.entity.DeThi;

@Repository
public interface CauHoiRepository extends JpaRepository<CauHoi, Integer> {
	Page<CauHoi> findAllByDeThi(DeThi deThi, Pageable pageable);
}
