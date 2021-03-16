package fpt.tracnghiem.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fpt.tracnghiem.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
	Optional<Role> findByRoleName(String roleName);
}
