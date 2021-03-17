package fpt.tracnghiem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fpt.tracnghiem.entity.CauHoi;

@Repository
public interface CauHoiRepository extends CrudRepository<CauHoi, Integer> {

}
