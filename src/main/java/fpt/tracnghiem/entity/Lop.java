package fpt.tracnghiem.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Nationalized;

import java.util.List;


/**
 * The persistent class for the Lop database table.
 * 
 */
@Entity
@Table(name="Lop")
@NamedQuery(name="Lop.findAll", query="SELECT l FROM Lop l")
public class Lop implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int idLop;

	@Column(length=250)
	@Nationalized
	private String tenLop;

	//bi-directional many-to-one association to BoDeThi
	@OneToMany(mappedBy="lop")
	private List<BoDeThi> boDeThis;

	public Lop() {
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

	public List<BoDeThi> getBoDeThis() {
		return this.boDeThis;
	}

	public void setBoDeThis(List<BoDeThi> boDeThis) {
		this.boDeThis = boDeThis;
	}

	public BoDeThi addBoDeThi(BoDeThi boDeThi) {
		getBoDeThis().add(boDeThi);
		boDeThi.setLop(this);

		return boDeThi;
	}

	public BoDeThi removeBoDeThi(BoDeThi boDeThi) {
		getBoDeThis().remove(boDeThi);
		boDeThi.setLop(null);

		return boDeThi;
	}

}