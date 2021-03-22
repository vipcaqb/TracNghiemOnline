package fpt.tracnghiem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fpt.tracnghiem.entity.Lop;
import fpt.tracnghiem.repository.LopRepository;

@Service
public class LopService {
	@Autowired
	private LopRepository lopRepository;
	
	public Iterable<Lop> getAllLop() {
		return lopRepository.findAll();
	}
	public  Optional<Lop> findById(int id){
		return lopRepository.findById(id);
	}
}
