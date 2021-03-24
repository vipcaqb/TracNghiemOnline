package fpt.tracnghiem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fpt.tracnghiem.entity.Role;
import fpt.tracnghiem.entity.TaiKhoan;

@Repository
public interface TaiKhoanRepository extends CrudRepository<TaiKhoan, String> {
	public List<TaiKhoan> findByUsernameAndPassword(String username,String password);
	List<TaiKhoan> findTop10ByOrderByDiemTichLuyDesc();
	public List<TaiKhoan> findTop6ByRoleOrderByDiemTichLuyDesc(Role role);
}
