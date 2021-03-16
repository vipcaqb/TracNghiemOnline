package fpt.tracnghiem.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the ThamGiaThi database table.
 * 
 */
@Entity
@Table(name="ThamGiaThi")
@NamedQuery(name="ThamGiaThi.findAll", query="SELECT t FROM ThamGiaThi t")
public class ThamGiaThi implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ThamGiaThiPK id;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date ngayGioBatDau;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date ngayGioKetThuc;

	@Column(precision=53)
	private double tongDiem;

	//bi-directional many-to-one association to BoDeThi
	@ManyToOne
	@JoinColumn(name="idBoDe", nullable=false, insertable=false, updatable=false)
	private BoDeThi boDeThi;

	//bi-directional many-to-one association to TaiKhoan
	@ManyToOne
	@JoinColumn(name="username", nullable=false, insertable=false, updatable=false)
	private TaiKhoan taiKhoan;

	public ThamGiaThi() {
	}

	public ThamGiaThiPK getId() {
		return this.id;
	}

	public void setId(ThamGiaThiPK id) {
		this.id = id;
	}

	public Date getNgayGioBatDau() {
		return this.ngayGioBatDau;
	}

	public void setNgayGioBatDau(Timestamp ngayGioBatDau) {
		this.ngayGioBatDau = ngayGioBatDau;
	}

	public Date getNgayGioKetThuc() {
		return this.ngayGioKetThuc;
	}

	public void setNgayGioKetThuc(Timestamp ngayGioKetThuc) {
		this.ngayGioKetThuc = ngayGioKetThuc;
	}

	public double getTongDiem() {
		return this.tongDiem;
	}

	public void setTongDiem(double tongDiem) {
		this.tongDiem = tongDiem;
	}

	public BoDeThi getBoDeThi() {
		return this.boDeThi;
	}

	public void setBoDeThi(BoDeThi boDeThi) {
		this.boDeThi = boDeThi;
	}

	public TaiKhoan getTaiKhoan() {
		return this.taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

}