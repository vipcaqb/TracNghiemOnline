package fpt.tracnghiem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import fpt.tracnghiem.entity.Role;
import fpt.tracnghiem.entity.TaiKhoan;
import fpt.tracnghiem.repository.TaiKhoanRepository;

@Service	
public class TaiKhoanService {
	@Autowired
	TaiKhoanRepository taiKhoanRepository;

	public TaiKhoan save(TaiKhoan taiKhoan) throws Exception {
		Optional<TaiKhoan> o = taiKhoanRepository.findById(taiKhoan.getUsername());
		if(o.isPresent()) {
			throw new DuplicateKeyException("Usename đã tồn tại");
		}
		else {
			taiKhoan.setUrlAvatar("/image/defaultAvatar.png");
			return taiKhoanRepository.save(taiKhoan);
		}
	}
	

	public Optional<TaiKhoan> findById(String id) {
		return taiKhoanRepository.findById(id);
	}
	
	public List<TaiKhoan> findByUsernameAndPassword(String username,String password){
		return taiKhoanRepository.findByUsernameAndPassword(username, password);
	}
	
	public TaiKhoan update(TaiKhoan taiKhoan) {
		return taiKhoanRepository.save(taiKhoan);
	}
	public List<TaiKhoan> findTop6UserMaxPoint(Role role){
		return taiKhoanRepository.findTop10ByOrderByDiemTichLuyDesc();
	}
	
	public TaiKhoan tangDiemTichLuy(TaiKhoan taiKhoan, int diemTichLuy) {
		taiKhoan.setDiemTichLuy(taiKhoan.getDiemTichLuy()+diemTichLuy);
		return taiKhoanRepository.save(taiKhoan);
	}
	
	public List<TaiKhoan> top10TaiKhoan(){
		return taiKhoanRepository.findTop10ByOrderByDiemTichLuyDesc();
	}
	
}
