package fpt.tracnghiem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fpt.tracnghiem.entity.PhuongAn;

@Repository
public interface PhuongAnRepository extends CrudRepository<PhuongAn, Integer>{

}
