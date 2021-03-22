package fpt.tracnghiem.service;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fpt.tracnghiem.entity.ThamGiaThi;
import fpt.tracnghiem.repository.ThamGiaThiRepository;

@Service
public class ThamGiaThiService {
	@Autowired
	private ThamGiaThiRepository thamGiaThiRepository;
	
	public ThamGiaThi AddThamGiaThi(ThamGiaThi thamGiaThi) {
		return thamGiaThiRepository.save(thamGiaThi);
	}
	

}
