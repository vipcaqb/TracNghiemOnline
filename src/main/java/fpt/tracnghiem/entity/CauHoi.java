package fpt.tracnghiem.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Nationalized;

import java.util.List;


/**
 * The persistent class for the CauHoi database table.
 * 
 */
@Entity
@Table(name="CauHoi")
@NamedQuery(name="CauHoi.findAll", query="SELECT c FROM CauHoi c")
public class CauHoi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int idCauHoi;

	@Column(length=1000)
	@Nationalized
	private String giaiThich;

	@Column(name="MaDe", length=50)
	@Nationalized
	private String maDe;

	//bi-directional many-to-one association to Anh
	@OneToMany(mappedBy="cauHoi")
	private List<Anh> anhs;

	//bi-directional many-to-one association to BoDeThi
	@ManyToOne
	@JoinColumn(name="idBoDe")
	private BoDeThi boDeThi;

	//bi-directional many-to-one association to PhuongAn
	@OneToMany(mappedBy="cauHoi")
	private List<PhuongAn> phuongAns;

	public CauHoi() {
	}

	public int getIdCauHoi() {
		return this.idCauHoi;
	}

	public void setIdCauHoi(int idCauHoi) {
		this.idCauHoi = idCauHoi;
	}

	public String getGiaiThich() {
		return this.giaiThich;
	}

	public void setGiaiThich(String giaiThich) {
		this.giaiThich = giaiThich;
	}

	public String getMaDe() {
		return this.maDe;
	}

	public void setMaDe(String maDe) {
		this.maDe = maDe;
	}

	public List<Anh> getAnhs() {
		return this.anhs;
	}

	public void setAnhs(List<Anh> anhs) {
		this.anhs = anhs;
	}

	public Anh addAnh(Anh anh) {
		getAnhs().add(anh);
		anh.setCauHoi(this);

		return anh;
	}

	public Anh removeAnh(Anh anh) {
		getAnhs().remove(anh);
		anh.setCauHoi(null);

		return anh;
	}

	public BoDeThi getBoDeThi() {
		return this.boDeThi;
	}

	public void setBoDeThi(BoDeThi boDeThi) {
		this.boDeThi = boDeThi;
	}

	public List<PhuongAn> getPhuongAns() {
		return this.phuongAns;
	}

	public void setPhuongAns(List<PhuongAn> phuongAns) {
		this.phuongAns = phuongAns;
	}

	public PhuongAn addPhuongAn(PhuongAn phuongAn) {
		getPhuongAns().add(phuongAn);
		phuongAn.setCauHoi(this);

		return phuongAn;
	}

	public PhuongAn removePhuongAn(PhuongAn phuongAn) {
		getPhuongAns().remove(phuongAn);
		phuongAn.setCauHoi(null);

		return phuongAn;
	}

}