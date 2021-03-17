package fpt.tracnghiem.model;

import javax.swing.text.html.parser.TagElement;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


public class ExamInformation{
	private int idDe;
	private String tenDe;
	private String tenMonHoc;
	private String tenLop;
	public int getIdDe() {
		return idDe;
	}
	public void setIdDe(int idDe) {
		this.idDe = idDe;
	}
	public String getTenDe() {
		return tenDe;
	}
	public void setTenDe(String tenDe) {
		this.tenDe = tenDe;
	}
	public String getTenMonHoc() {
		return tenMonHoc;
	}
	public void setTenMonHoc(String tenMonHoc) {
		this.tenMonHoc = tenMonHoc;
	}
	public String getTenLop() {
		return tenLop;
	}
	public void setTenLop(String tenLop) {
		this.tenLop = tenLop;
	}
	public ExamInformation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ExamInformation(int idDe, String tenDe, String tenMonHoc, String tenLop) {
		super();
		this.idDe = idDe;
		this.tenDe = tenDe;
		this.tenMonHoc = tenMonHoc;
		this.tenLop = tenLop;
	}

	
}
