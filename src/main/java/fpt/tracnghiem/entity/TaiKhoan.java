package fpt.tracnghiem.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.NotFound;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tai_khoan database table.
 * 
 */
@Entity
@Table(name="tai_khoan")
@NamedQuery(name="TaiKhoan.findAll", query="SELECT t FROM TaiKhoan t")
public class TaiKhoan implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(length = 30)
	@Length(min = 4,max=30,message = "Username phải có độ dài từ 4 đến 30 ký tự")
	@Pattern(regexp="^[a-zA-Z0-9]*$",message = "Username chứa ký tự không hợp lệ")
	private String username;

	@Column(name="dia_chi",length = 100)
	@Nationalized
	@Length(max=100,message = "Địa chỉ quá dài")
	private String diaChi;

	@Column(name="diem_tich_luy")
	private int diemTichLuy;

	@Column
	@NotBlank(message="Email không được để trống")
	@Email(message = "Email không hợp lệ")
	private String email;

	@Column
	private boolean enable;

	@Column(name="ho_va_ten")
	@Nationalized
	@NotBlank(message = "Họ tên không được trống")
	private String hoVaTen;

	@Column(name="ngay_sinh")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ngaySinh;

	@Column(name="password",length = 30)
	@Pattern(regexp="^[a-zA-Z0-9]*$",message = "Pass chứa ký tự không hợp lệ")
	@Length(min=6,max=40,message = "Mật khẩu phải có độ dài từ 6 đến 30 ký tự")
	private String password;

	@Column(name="sdt",length = 20)
	@Pattern(regexp="^[0-9]*$",message = "Số điện thoại không hợp lệ")
	private String sdt;

	@Column(name="url_avatar")
	private String urlAvatar;

	//bi-directional many-to-one association to DeThi
	@OneToMany(mappedBy="taiKhoan")
	private List<DeThi> deThis;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="role_id")
	private Role role;

	//bi-directional many-to-one association to ThamGiaThi
	@OneToMany(mappedBy="taiKhoan")
	private List<ThamGiaThi> thamGiaThis;

	public TaiKhoan() {
	}
	
	public TaiKhoan(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public TaiKhoan(String username, String password, String hoVaTen) {
		super();
		this.username = username;
		this.hoVaTen = hoVaTen;
		this.password = password;
	}
	
	

	public TaiKhoan(
			@Length(min = 4, max = 30, message = "Username phải có độ dài từ 4 đến 30 ký tự") @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Username chứa ký tự không hợp lệ") String username,
			@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Pass chứa ký tự không hợp lệ") @Length(min = 6, max = 30, message = "Mật khẩu phải có độ dài từ 6 đến 30 ký tự") String password,
			@NotBlank(message = "Họ tên không được trống") String hoVaTen,
			@NotBlank(message = "Email không được để trống") @Email(message = "Email không hợp lệ") String email
			) {
		super();
		this.username = username;
		this.email = email;
		this.hoVaTen = hoVaTen;
		this.password = password;
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

	public int getDiemTichLuy() {
		return this.diemTichLuy;
	}

	public void setDiemTichLuy(int diemTichLuy) {
		this.diemTichLuy = diemTichLuy;
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

	public Date getNgaySinh() {
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

	public List<DeThi> getDeThis() {
		return this.deThis;
	}

	public void setDeThis(List<DeThi> deThis) {
		this.deThis = deThis;
	}

	public DeThi addDeThi(DeThi deThi) {
		getDeThis().add(deThi);
		deThi.setTaiKhoan(this);

		return deThi;
	}

	public DeThi removeDeThi(DeThi deThi) {
		getDeThis().remove(deThi);
		deThi.setTaiKhoan(null);

		return deThi;
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

	@Override
	public String toString() {
		return "TaiKhoan [username=" + username + ", hoVaTen=" + hoVaTen + ", password=" + password + "]";
	}
	
	
	//set random auto avatar
	

}