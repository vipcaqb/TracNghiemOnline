package fpt.tracnghiem.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;


/**
 * The persistent class for the tham_gia_thi database table.
 * 
 */
@Entity
@Table(name="tham_gia_thi")
@NamedQuery(name="ThamGiaThi.findAll", query="SELECT t FROM ThamGiaThi t")
public class ThamGiaThi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_tham_gia_thi")
	private int idThamGiaThi;

	@Column(name="ngay_gio_bat_dau")
	private Timestamp ngayGioBatDau;

	@Column(name="ngay_gio_ket_thuc")
	private Timestamp ngayGioKetThuc;

	@Column(name="tong_diem")
	private double tongDiem;

	//bi-directional many-to-one association to DeThi
	@ManyToOne
	@JoinColumn(name="id_bo_de")
	private DeThi deThi;

	//bi-directional many-to-one association to TaiKhoan
	@ManyToOne
	@JoinColumn(name="username")
	private TaiKhoan taiKhoan;

	public ThamGiaThi() {
	}

	public int getIdThamGiaThi() {
		return this.idThamGiaThi;
	}

	public void setIdThamGiaThi(int idThamGiaThi) {
		this.idThamGiaThi = idThamGiaThi;
	}

	public Timestamp getNgayGioBatDau() {
		return this.ngayGioBatDau;
	}

	public void setNgayGioBatDau(Timestamp ngayGioBatDau) {
		this.ngayGioBatDau = ngayGioBatDau;
	}

	public Timestamp getNgayGioKetThuc() {
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

	public DeThi getDeThi() {
		return this.deThi;
	}

	public void setDeThi(DeThi deThi) {
		this.deThi = deThi;
	}

	public TaiKhoan getTaiKhoan() {
		return this.taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

}