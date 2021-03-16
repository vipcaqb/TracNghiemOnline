package fpt.tracnghiem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fpt.tracnghiem.entity.Anh;

@Repository
public interface AnhRepository extends CrudRepository<Anh, Integer> {
}
