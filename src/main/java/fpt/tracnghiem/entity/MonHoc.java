package fpt.tracnghiem.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Nationalized;

import java.util.List;


/**
 * The persistent class for the mon_hoc database table.
 * 
 */
@Entity
@Table(name="mon_hoc")
@NamedQuery(name="MonHoc.findAll", query="SELECT m FROM MonHoc m")
public class MonHoc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_mon_hoc")
	private int idMonHoc;

	@Column(name="ten_mon_hoc",unique = true)
	@Nationalized
	private String tenMonHoc;

	//bi-directional many-to-one association to DeThi
	@OneToMany(mappedBy="monHoc")
	private List<DeThi> deThis;

	public MonHoc() {
	}
	
	public MonHoc(String tenMonHoc) {
		this.tenMonHoc = tenMonHoc;
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

	public List<DeThi> getDeThis() {
		return this.deThis;
	}

	public void setDeThis(List<DeThi> deThis) {
		this.deThis = deThis;
	}

	public DeThi addDeThi(DeThi deThi) {
		getDeThis().add(deThi);
		deThi.setMonHoc(this);

		return deThi;
	}

	public DeThi removeDeThi(DeThi deThi) {
		getDeThis().remove(deThi);
		deThi.setMonHoc(null);

		return deThi;
	}

}