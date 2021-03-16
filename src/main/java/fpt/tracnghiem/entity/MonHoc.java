package fpt.tracnghiem.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Nationalized;

import java.util.List;


/**
 * The persistent class for the MonHoc database table.
 * 
 */
@Entity
@Table(name="MonHoc")
@NamedQuery(name="MonHoc.findAll", query="SELECT m FROM MonHoc m")
public class MonHoc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int idMonHoc;

	@Column(length=255)
	@Nationalized
	private String tenMonHoc;

	//bi-directional many-to-one association to BoDeThi
	@OneToMany(mappedBy="monHoc")
	private List<BoDeThi> boDeThis;

	public MonHoc() {
	}

	public int getIdMonHoc() {
		return this.idMonHoc;
	}

	public void setIdMonHoc(int idMonHoc) {
		this.idMonHoc = idMonHoc;
	}

	public String getTenMonHoc() {
		return this.tenMonHoc;
	}

	public void setTenMonHoc(String tenMonHoc) {
		this.tenMonHoc = tenMonHoc;
	}

	public List<BoDeThi> getBoDeThis() {
		return this.boDeThis;
	}

	public void setBoDeThis(List<BoDeThi> boDeThis) {
		this.boDeThis = boDeThis;
	}

	public BoDeThi addBoDeThi(BoDeThi boDeThi) {
		getBoDeThis().add(boDeThi);
		boDeThi.setMonHoc(this);

		return boDeThi;
	}

	public BoDeThi removeBoDeThi(BoDeThi boDeThi) {
		getBoDeThis().remove(boDeThi);
		boDeThi.setMonHoc(null);

		return boDeThi;
	}

}