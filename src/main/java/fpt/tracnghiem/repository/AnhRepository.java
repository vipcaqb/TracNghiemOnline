package fpt.tracnghiem.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fpt.tracnghiem.entity.Anh;
import fpt.tracnghiem.entity.CauHoi;

@Repository
public interface AnhRepository extends CrudRepository<Anh, Integer> {
	List<Anh> findByCauHoi(CauHoi cauHoi);
}
