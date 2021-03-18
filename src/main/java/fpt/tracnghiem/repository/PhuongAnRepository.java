package fpt.tracnghiem.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fpt.tracnghiem.entity.CauHoi;
import fpt.tracnghiem.entity.PhuongAn;

@Repository
public interface PhuongAnRepository extends CrudRepository<PhuongAn, Integer>{
	List<PhuongAn> findByCauHoi(CauHoi cauHoi);
}
