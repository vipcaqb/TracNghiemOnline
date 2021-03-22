package fpt.tracnghiem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fpt.tracnghiem.entity.ThamGiaThi;
@Repository
public interface ThamGiaThiRepository extends CrudRepository<ThamGiaThi, Integer> {
	
}
