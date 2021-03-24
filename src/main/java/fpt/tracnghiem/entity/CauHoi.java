package fpt.tracnghiem.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Nationalized;


/**
 * The persistent class for the cau_hoi database table.
 * 
 */
@Entity
@Table(name="cau_hoi")
@NamedQuery(name="CauHoi.findAll", query="SELECT c FROM CauHoi c")
public class CauHoi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cau_hoi")
	private int idCauHoi;
	
	@Column(name="giai_thich",length = 3000)
	@Nationalized
	@NotBlank(message = "Không được để trống trường này")
	@Size(min = 4,max=30,message = "Giải thích phải có độ dài từ 4 đến 30 ký tự")
	@Pattern(regexp="^[a-zA-Z0-9]*$",message = "Giải thích chứa ký tự không hợp lệ")
	private String giaiThich;
	@NotBlank(message = "Không được để trống trường này")
	@Size(min = 4,max=30,message = "Nội dung phải có độ dài từ 4 đến 30 ký tự")
	@Pattern(regexp="^[a-zA-Z0-9]*$",message = "Nội dung chứa ký tự không hợp lệ")
	@Column(name="noi_dung",length = 3000)
	@Nationalized
	private String noiDung;

	//bi-directional many-to-one association to Anh
	@OneToMany(mappedBy="cauHoi")
	private List<Anh> anhs;

	//bi-directional many-to-one association to DeThi
	@ManyToOne
	@JoinColumn(name="id_bo_de")
	private DeThi deThi;

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

	public DeThi getDeThi() {
		return this.deThi;
	}

	public void setDeThi(DeThi deThi) {
		this.deThi = deThi;
	}

	@Override
	public String toString() {
		return "CauHoi [idCauHoi=" + idCauHoi + ", giaiThich=" + giaiThich + ", noiDung=" + noiDung + "]";
	}

	public String getNoiDung() {
		return noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
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