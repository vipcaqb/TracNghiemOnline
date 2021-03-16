package fpt.tracnghiem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fpt.tracnghiem.entity.MonHoc;
import fpt.tracnghiem.repository.MonHocRepository;

@Service
public class MonHocService {
	@Autowired
	private MonHocRepository monHocRepository;
	
	
	public Iterable<MonHoc> getAllMonHoc() {
		return monHocRepository.findAll();
	}
}
