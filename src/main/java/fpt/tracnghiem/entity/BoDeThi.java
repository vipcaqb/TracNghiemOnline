package fpt.tracnghiem.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Nationalized;

import java.util.List;


/**
 * The persistent class for the BoDeThi database table.
 * 
 */
@Entity
@Table(name="BoDeThi")
@NamedQuery(name="BoDeThi.findAll", query="SELECT b FROM BoDeThi b")
public class BoDeThi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int idBoDe;

	@Column(length=255)
	@Nationalized
	private String tenBoDe;

	//bi-directional many-to-one association to Lop
	@ManyToOne
	@JoinColumn(name="idLop")
	private Lop lop;

	//bi-directional many-to-one association to MonHoc
	@ManyToOne
	@JoinColumn(name="idMonHoc")
	private MonHoc monHoc;

	//bi-directional many-to-one association to NganHangDe
	@ManyToOne
	@JoinColumn(name="idNganHangDe")
	private NganHangDe nganHangDe;

	//bi-directional many-to-one association to CauHoi
	@OneToMany(mappedBy="boDeThi")
	private List<CauHoi> cauHois;

	//bi-directional many-to-one association to ThamGiaThi
	@OneToMany(mappedBy="boDeThi")
	private List<ThamGiaThi> thamGiaThis;

	public BoDeThi() {
	}

	public int getIdBoDe() {
		return this.idBoDe;
	}

	public void setIdBoDe(int idBoDe) {
		this.idBoDe = idBoDe;
	}

	public String getTenBoDe() {
		return this.tenBoDe;
	}

	public void setTenBoDe(String tenBoDe) {
		this.tenBoDe = tenBoDe;
	}

	public Lop getLop() {
		return this.lop;
	}

	public void setLop(Lop lop) {
		this.lop = lop;
	}

	public MonHoc getMonHoc() {
		return this.monHoc;
	}

	public void setMonHoc(MonHoc monHoc) {
		this.monHoc = monHoc;
	}

	public NganHangDe getNganHangDe() {
		return this.nganHangDe;
	}

	public void setNganHangDe(NganHangDe nganHangDe) {
		this.nganHangDe = nganHangDe;
	}

	public List<CauHoi> getCauHois() {
		return this.cauHois;
	}

	public void setCauHois(List<CauHoi> cauHois) {
		this.cauHois = cauHois;
	}

	public CauHoi addCauHoi(CauHoi cauHoi) {
		getCauHois().add(cauHoi);
		cauHoi.setBoDeThi(this);

		return cauHoi;
	}

	public CauHoi removeCauHoi(CauHoi cauHoi) {
		getCauHois().remove(cauHoi);
		cauHoi.setBoDeThi(null);

		return cauHoi;
	}

	public List<ThamGiaThi> getThamGiaThis() {
		return this.thamGiaThis;
	}

	public void setThamGiaThis(List<ThamGiaThi> thamGiaThis) {
		this.thamGiaThis = thamGiaThis;
	}

	public ThamGiaThi addThamGiaThi(ThamGiaThi thamGiaThi) {
		getThamGiaThis().add(thamGiaThi);
		thamGiaThi.setBoDeThi(this);

		return thamGiaThi;
	}

	public ThamGiaThi removeThamGiaThi(ThamGiaThi thamGiaThi) {
		getThamGiaThis().remove(thamGiaThi);
		thamGiaThi.setBoDeThi(null);

		return thamGiaThi;
	}

}