package fpt.tracnghiem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fpt.tracnghiem.entity.ThamGiaThi;

@Repository
public interface ThiRepository extends CrudRepository<ThamGiaThi, Integer> {
}
