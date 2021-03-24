package fpt.tracnghiem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fpt.tracnghiem.entity.PhuongAn;
import fpt.tracnghiem.repository.PhuongAnRepository;

@Service
public class PhuongAnService {
	@Autowired
	PhuongAnRepository phuongAnRepository;
	
	public void save(PhuongAn phuongAn) {
		phuongAnRepository.save(phuongAn);
	}
	
	
}
