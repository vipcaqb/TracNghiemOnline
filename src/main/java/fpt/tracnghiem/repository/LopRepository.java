package fpt.tracnghiem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fpt.tracnghiem.entity.Lop;

@Repository
public interface LopRepository extends CrudRepository<Lop, Integer> {
	Optional<Lop> findByTenLop(String tenLop);


	
}
