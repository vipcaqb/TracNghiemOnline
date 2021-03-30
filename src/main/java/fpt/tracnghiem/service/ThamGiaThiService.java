package fpt.tracnghiem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fpt.tracnghiem.entity.DeThi;
import fpt.tracnghiem.entity.TaiKhoan;
import fpt.tracnghiem.entity.ThamGiaThi;
import fpt.tracnghiem.repository.ThamGiaThiRepository;

@Service
public class ThamGiaThiService {
	@Autowired
	ThamGiaThiRepository thamGiaThiRepository;
	public List<ThamGiaThi> GetAllThamGiaThi(TaiKhoan taiKhoan) {
		return thamGiaThiRepository.findAllByTaiKhoan(taiKhoan);
	}
	public List<ThamGiaThi> findByDeThi(DeThi deThi) {
		return thamGiaThiRepository.findByDeThi(deThi);
	}
	public void DeleteById(int id) {
		thamGiaThiRepository.deleteById(id);
	}
}
