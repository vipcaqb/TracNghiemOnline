package fpt.tracnghiem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fpt.tracnghiem.entity.DeThi;
import fpt.tracnghiem.model.ExamInformation;

@Repository
public interface DeThiRepository  extends CrudRepository<DeThi, Integer>{
	@Query("SELECT NEW fpt.tracnghiem.model.ExamInformation (a.idDe,a.tenDe, b.tenMonHoc, c.tenLop) FROM DeThi a INNER JOIN Lop c ON a.lop.idLop = c.idLop INNER JOIN MonHoc b ON a.monHoc.idMonHoc = b.idMonHoc")
	List<ExamInformation> getExamInformation();
}
