package fpt.tracnghiem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fpt.tracnghiem.entity.MonHoc;

@Repository
public interface MonHocRepository extends CrudRepository<MonHoc, Integer>{
	
}
