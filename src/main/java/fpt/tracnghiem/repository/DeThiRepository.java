package fpt.tracnghiem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fpt.tracnghiem.entity.DeThi;

@Repository
public interface DeThiRepository extends CrudRepository<DeThi, Integer> {
	
}
