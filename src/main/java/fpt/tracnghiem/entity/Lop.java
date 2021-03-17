package fpt.tracnghiem.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Nationalized;

import java.util.List;


/**
 * The persistent class for the lop database table.
 * 
 */
@Entity
@Table(name="lop")
@NamedQuery(name="Lop.findAll", query="SELECT l FROM Lop l")
public class Lop implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_lop")
	private int idLop;

	@Column(name="ten_lop",unique = true)
	@Nationalized
	private String tenLop;

	//bi-directional many-to-one association to DeThi
	@OneToMany(mappedBy="lop")
	private List<DeThi> deThis;

	public Lop() {
	}
	
	public Lop(String tenLop) {
		this.tenLop=tenLop;
	}

	public int getIdLop() {
		return this.idLop;
	}

	public void setIdLop(int idLop) {
		this.idLop = idLop;
	}

	public String getTenLop() {
		return this.tenLop;
	}

	public void setTenLop(String tenLop) {
		this.tenLop = tenLop;
	}

	public List<DeThi> getDeThis() {
		return this.deThis;
	}

	public void setDeThis(List<DeThi> deThis) {
		this.deThis = deThis;
	}

	public DeThi addDeThi(DeThi deThi) {
		getDeThis().add(deThi);
		deThi.setLop(this);

		return deThi;
	}

	public DeThi removeDeThi(DeThi deThi) {
		getDeThis().remove(deThi);
		deThi.setLop(null);

		return deThi;
	}

}