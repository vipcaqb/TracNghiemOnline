package fpt.tracnghiem.model;

import java.util.List;

import fpt.tracnghiem.entity.CauHoi;

public class KetQuaBaiThi {
	Integer maDe;
	String tenDe;
	Integer thoiGianThi;
	String monHoc;
	String tenLop;
	Integer soCauDung;
	Integer tongSoCau;
	Integer diemSo;

	public Integer getMaDe() {
		return maDe;
	}

	public void setMaDe(Integer i) {
		this.maDe = i;
	}

	public String getTenDe() {
		return tenDe;
	}

	public void setTenDe(String tenDe) {
		this.tenDe = tenDe;
	}

	public Integer getThoiGianThi() {
		return thoiGianThi;
	}

	public void setThoiGianThi(Integer thoiGianThi) {
		this.thoiGianThi = thoiGianThi;
	}

	public String getMonHoc() {
		return monHoc;
	}

	public void setMonHoc(String monHoc) {
		this.monHoc = monHoc;
	}

	public String getTenLop() {
		return tenLop;
	}

	public void setTenLop(String tenLop) {
		this.tenLop = tenLop;
	}

	public Integer getSoCauDung() {
		return soCauDung;
	}

	public void setSoCauDung(Integer soCauDung) {
		this.soCauDung = soCauDung;
	}

	public Integer getTongSoCau() {
		return tongSoCau;
	}

	public void setTongSoCau(Integer tongSoCau) {
		this.tongSoCau = tongSoCau;
	}
	
	public void getTongDiem() {
		float a = (float)soCauDung/(float)tongSoCau;
		diemSo= (int) (a*100);
	}
	
	

	public Integer getDiemSo() {
		return diemSo;
	}

	public KetQuaBaiThi(Integer maDe, String tenDe, Integer thoiGianThi, String monHoc, String tenLop, Integer soCauDung,
			Integer tongSoCau) {
		super();
		this.maDe = maDe;
		this.tenDe = tenDe;
		this.thoiGianThi = thoiGianThi;
		this.monHoc = monHoc;
		this.tenLop = tenLop;
		this.soCauDung = soCauDung;
		this.tongSoCau = tongSoCau;
	}

	public KetQuaBaiThi() {
		super();
	}
}
