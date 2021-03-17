package fpt.tracnghiem.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import fpt.tracnghiem.entity.Lop;
import fpt.tracnghiem.entity.Role;
import fpt.tracnghiem.entity.TaiKhoan;
import fpt.tracnghiem.repository.LopRepository;
import fpt.tracnghiem.repository.RoleRepository;
import fpt.tracnghiem.repository.TaiKhoanRepository;

@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent>  {
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired 
	LopRepository lopRepository;
	
	@Autowired
	TaiKhoanRepository taiKhoanRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		//Tự động sinh các ROLE trong database khi còn thiếu
		
		if(roleRepository.findByRoleName("ROLE_USER").isEmpty()) {
			roleRepository.save(new Role("ROLE_USER"));
		}
		
		if(roleRepository.findByRoleName("ROLE_CREATER").isEmpty()) {
			roleRepository.save(new Role("ROLE_CREATER"));
		}
		
		if(roleRepository.findByRoleName("ROLE_ADMIN").isEmpty()) {
			roleRepository.save(new Role("ROLE_ADMIN"));
		}
		
		//Tự động sinh dữ liệu lớp từ 1 đến 12
		
		if(lopRepository.findByTenLop("Lớp 1").isEmpty()) {
			lopRepository.save(new Lop("Lớp 1"));
		}
		if(lopRepository.findByTenLop("Lớp 2").isEmpty()) {
			lopRepository.save(new Lop("Lớp 2"));
		}
		if(lopRepository.findByTenLop("Lớp 3").isEmpty()) {
			lopRepository.save(new Lop("Lớp 3"));
		}
		if(lopRepository.findByTenLop("Lớp 4").isEmpty()) {
			lopRepository.save(new Lop("Lớp 4"));
		}
		if(lopRepository.findByTenLop("Lớp 5").isEmpty()) {
			lopRepository.save(new Lop("Lớp 5"));
		}
		if(lopRepository.findByTenLop("Lớp 6").isEmpty()) {
			lopRepository.save(new Lop("Lớp 6"));
		}
		if(lopRepository.findByTenLop("Lớp 7").isEmpty()) {
			lopRepository.save(new Lop("Lớp 7"));
		}
		if(lopRepository.findByTenLop("Lớp 8").isEmpty()) {
			lopRepository.save(new Lop("Lớp 8"));
		}
		if(lopRepository.findByTenLop("Lớp 9").isEmpty()) {
			lopRepository.save(new Lop("Lớp 9"));
		}
		if(lopRepository.findByTenLop("Lớp 10").isEmpty()) {
			lopRepository.save(new Lop("Lớp 10"));
		}
		if(lopRepository.findByTenLop("Lớp 11").isEmpty()) {
			lopRepository.save(new Lop("Lớp 11"));
		}
		if(lopRepository.findByTenLop("Lớp 12").isEmpty()) {
			lopRepository.save(new Lop("Lớp 12"));
		}
		
		//tao 1 tai khoan la ROLE_USER
		if(taiKhoanRepository.findById("test").isEmpty()) {
			TaiKhoan tk = new TaiKhoan("test","123");
			Optional<Role> x= roleRepository.findByRoleName("ROLE_USER");
			if(x.isPresent()) {
				tk.setRole(x.get());
			}
			taiKhoanRepository.save(tk);
		}
		
	}

}
