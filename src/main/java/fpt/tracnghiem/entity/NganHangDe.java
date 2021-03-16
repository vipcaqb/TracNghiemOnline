package fpt.tracnghiem.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Nationalized;

import java.util.List;


/**
 * The persistent class for the NganHangDe database table.
 * 
 */
@Entity
@Table(name="NganHangDe")
@NamedQuery(name="NganHangDe.findAll", query="SELECT n FROM NganHangDe n")
public class NganHangDe implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int idNganHangDe;

	@Column(length=250)
	@Nationalized
	private String tenNganHangDe;

	//bi-directional many-to-one association to BoDeThi
	@OneToMany(mappedBy="nganHangDe")
	private List<BoDeThi> boDeThis;

	public NganHangDe() {
	}

	public int getIdNganHangDe() {
		return this.idNganHangDe;
	}

	public void setIdNganHangDe(int idNganHangDe) {
		this.idNganHangDe = idNganHangDe;
	}

	public String getTenNganHangDe() {
		return this.tenNganHangDe;
	}

	public void setTenNganHangDe(String tenNganHangDe) {
		this.tenNganHangDe = tenNganHangDe;
	}

	public List<BoDeThi> getBoDeThis() {
		return this.boDeThis;
	}

	public void setBoDeThis(List<BoDeThi> boDeThis) {
		this.boDeThis = boDeThis;
	}

	public BoDeThi addBoDeThi(BoDeThi boDeThi) {
		getBoDeThis().add(boDeThi);
		boDeThi.setNganHangDe(this);

		return boDeThi;
	}

	public BoDeThi removeBoDeThi(BoDeThi boDeThi) {
		getBoDeThis().remove(boDeThi);
		boDeThi.setNganHangDe(null);

		return boDeThi;
	}

}