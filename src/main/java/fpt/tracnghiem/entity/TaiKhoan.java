package fpt.tracnghiem.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Nationalized;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TaiKhoan database table.
 * 
 */
@Entity
@Table(name="TaiKhoan")
@NamedQuery(name="TaiKhoan.findAll", query="SELECT t FROM TaiKhoan t")
public class TaiKhoan implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=50)
	private String username;

	@Column(length=250)
	@Nationalized
	private String diaChi;

	@Column(length=50)
	private String email;

	@Column
	private boolean enable;

	@Column(length=250)
	@Nationalized
	private String hoVaTen;

	@Column
	@Temporal(TemporalType.DATE) 
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ngaySinh;

	@Column(length=50)
	private String password;

	@Column(length=50)
	private String sdt;

	@Column(length=250)
	private String urlAvatar;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="roleId")
	private Role role;

	//bi-directional many-to-one association to ThamGiaThi
	@OneToMany(mappedBy="taiKhoan")
	private List<ThamGiaThi> thamGiaThis;

	public TaiKhoan() {
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDiaChi() {
		return this.diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getEnable() {
		return this.enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getHoVaTen() {
		return this.hoVaTen;
	}

	public void setHoVaTen(String hoVaTen) {
		this.hoVaTen = hoVaTen;
	}

	public Object getNgaySinh() {
		return this.ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSdt() {
		return this.sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getUrlAvatar() {
		return this.urlAvatar;
	}

	public void setUrlAvatar(String urlAvatar) {
		this.urlAvatar = urlAvatar;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<ThamGiaThi> getThamGiaThis() {
		return this.thamGiaThis;
	}

	public void setThamGiaThis(List<ThamGiaThi> thamGiaThis) {
		this.thamGiaThis = thamGiaThis;
	}

	public ThamGiaThi addThamGiaThi(ThamGiaThi thamGiaThi) {
		getThamGiaThis().add(thamGiaThi);
		thamGiaThi.setTaiKhoan(this);

		return thamGiaThi;
	}

	public ThamGiaThi removeThamGiaThi(ThamGiaThi thamGiaThi) {
		getThamGiaThis().remove(thamGiaThi);
		thamGiaThi.setTaiKhoan(null);

		return thamGiaThi;
	}

}