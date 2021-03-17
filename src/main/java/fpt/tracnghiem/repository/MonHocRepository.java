package fpt.tracnghiem.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fpt.tracnghiem.entity.MonHoc;

@Repository
public interface MonHocRepository extends CrudRepository<MonHoc, Integer>{
	Optional<MonHoc> findByTenMonHoc(String tenMonHoc);
}
