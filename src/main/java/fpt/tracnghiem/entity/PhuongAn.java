package fpt.tracnghiem.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Nationalized;


/**
 * The persistent class for the phuong_an database table.
 * 
 */
@Entity
@Table(name="phuong_an")
@NamedQuery(name="PhuongAn.findAll", query="SELECT p FROM PhuongAn p")
public class PhuongAn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_phuong_an")
	private int idPhuongAn;

	@Column(name="is_correct")
	private boolean isCorrect;

	@Column(name="noi_dung",length = 3000)
	@Nationalized
	private String noiDung;

	//bi-directional many-to-one association to CauHoi
	@ManyToOne
	@JoinColumn(name="id_cau_hoi")
	private CauHoi cauHoi;

	public PhuongAn() {
	}
	
	@Override
	public String toString() {
		return "PhuongAn [idPhuongAn=" + idPhuongAn + ", isCorrect=" + isCorrect + ", noiDung=" + noiDung + "]";
	}

	public PhuongAn(String noiDung,boolean isCorrect,CauHoi cauHoi) {
		this.noiDung=noiDung;
		this.isCorrect=isCorrect;
		this.cauHoi=cauHoi;
	}

	public int getIdPhuongAn() {
		return this.idPhuongAn;
	}

	public void setIdPhuongAn(int idPhuongAn) {
		this.idPhuongAn = idPhuongAn;
	}

	public boolean getIsCorrect() {
		return this.isCorrect;
	}

	public void setIsCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	public String getNoiDung() {
		return this.noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	public CauHoi getCauHoi() {
		return this.cauHoi;
	}

	public void setCauHoi(CauHoi cauHoi) {
		this.cauHoi = cauHoi;
	}

}