package fpt.tracnghiem.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fpt.tracnghiem.entity.TaiKhoan;
import fpt.tracnghiem.entity.ThamGiaThi;
import fpt.tracnghiem.entity.DeThi;
@Repository
public interface ThamGiaThiRepository extends CrudRepository<ThamGiaThi, Integer>{
	List<ThamGiaThi> findAllByTaiKhoan(TaiKhoan taiKhoan);
	List<ThamGiaThi> findByDeThi(DeThi dethi);
}
