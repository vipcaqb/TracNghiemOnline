package fpt.tracnghiem.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Role database table.
 * 
 */
@Entity
@Table(name="Role")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int roleId;

	@Column(length=50)
	private String roleName;

	//bi-directional many-to-one association to TaiKhoan
	@OneToMany(mappedBy="role")
	private List<TaiKhoan> taiKhoans;

	public Role() {
	}
	
	public Role(String roleName) {
		this.roleName = roleName;
	}

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<TaiKhoan> getTaiKhoans() {
		return this.taiKhoans;
	}

	public void setTaiKhoans(List<TaiKhoan> taiKhoans) {
		this.taiKhoans = taiKhoans;
	}

	public TaiKhoan addTaiKhoan(TaiKhoan taiKhoan) {
		getTaiKhoans().add(taiKhoan);
		taiKhoan.setRole(this);

		return taiKhoan;
	}

	public TaiKhoan removeTaiKhoan(TaiKhoan taiKhoan) {
		getTaiKhoans().remove(taiKhoan);
		taiKhoan.setRole(null);

		return taiKhoan;
	}

}