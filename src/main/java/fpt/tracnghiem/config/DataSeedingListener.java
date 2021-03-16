package fpt.tracnghiem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import fpt.tracnghiem.entity.Role;
import fpt.tracnghiem.repository.RoleRepository;

@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent>  {
	
	@Autowired
	RoleRepository roleRepository;
	

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
		
	}

}
