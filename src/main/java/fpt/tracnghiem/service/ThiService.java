package fpt.tracnghiem.service;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fpt.tracnghiem.entity.DeThi;
import fpt.tracnghiem.entity.TaiKhoan;
import fpt.tracnghiem.entity.ThamGiaThi;
import fpt.tracnghiem.repository.ThiRepository;

/**
 * The Class ThiService.
 */
@Service
public class ThiService {
	
	/** The thi repository. */
	@Autowired
	ThiRepository thiRepository;
	
	@Autowired
	TaiKhoanService taiKhoanService;
	
	/**
	 * Khi bắt đầu cuộc thi sẽ tạo các thông tin ban đầu giữa người dùng và đề thi đó
	 *
	 * @param taiKhoan the tai khoan
	 * @param deThi the de thi
	 * @return the tham gia thi
	 */
	public ThamGiaThi batDauThi(TaiKhoan taiKhoan, DeThi deThi) {
		ThamGiaThi thamGiaThi = new ThamGiaThi();
		thamGiaThi.setFinished(false);
		thamGiaThi.setDeThi(deThi);
		thamGiaThi.setNgayGioBatDau(new Timestamp(System.currentTimeMillis()));
		
		return thiRepository.save(thamGiaThi);
	}
	
	/**
	 * Kết thúc cuộc thi điền các thông tin còn lại vào bảng tham gia thi
	 *
	 * @param baiDangThi the bai dang thi
	 * @param tongDiem the tong diem
	 */
	@Transactional
	public void hoanThanhBaiThi(ThamGiaThi baiDangThi,Integer tongDiem,String usernameDangThi) {
		baiDangThi.setFinished(true);
		baiDangThi.setNgayGioKetThuc(new Timestamp(System.currentTimeMillis()));
		baiDangThi.setTongDiem(tongDiem);
		thiRepository.save(baiDangThi);
		
		TaiKhoan taiKhoan = taiKhoanService.findById(usernameDangThi).get();
		taiKhoanService.tangDiemTichLuy(taiKhoan, tongDiem);
	}
}
