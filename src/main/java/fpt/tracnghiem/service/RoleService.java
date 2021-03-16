package fpt.tracnghiem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fpt.tracnghiem.entity.Role;
import fpt.tracnghiem.repository.RoleRepository;

@Service
public class RoleService {
	@Autowired
	RoleRepository roleRepository;

	public Optional<Role> findById(Integer id) {
		return roleRepository.findById(id);
	}
	
	public Optional<Role> findByRoleName(String roleName){
		return roleRepository.findByRoleName(roleName);
	}
	
}
