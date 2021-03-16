package fpt.tracnghiem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fpt.tracnghiem.entity.TaiKhoan;
import fpt.tracnghiem.repository.TaiKhoanRepository;

@Service	
public class TaiKhoanService {
	@Autowired
	TaiKhoanRepository taiKhoanRepository;

	public TaiKhoan save(TaiKhoan taiKhoan) {
		return taiKhoanRepository.save(taiKhoan);
	}

	public Optional<TaiKhoan> findById(String id) {
		return taiKhoanRepository.findById(id);
	}
	
	public List<TaiKhoan> findByUsernameAndPassword(String username,String password){
		return taiKhoanRepository.findByUsernameAndPassword(username, password);
	}
	
	
}
