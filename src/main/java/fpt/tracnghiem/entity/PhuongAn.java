package fpt.tracnghiem.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Nationalized;


/**
 * The persistent class for the PhuongAn database table.
 * 
 */
@Entity
@Table(name="PhuongAn")
@NamedQuery(name="PhuongAn.findAll", query="SELECT p FROM PhuongAn p")
public class PhuongAn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int idPhuongAn;
	
	@Column()
	private boolean isCorrect;

	@Column(length=1000)
	@Nationalized
	private String noiDung;

	//bi-directional many-to-one association to CauHoi
	@ManyToOne
	@JoinColumn(name="idCauHoi")
	private CauHoi cauHoi;

	public PhuongAn() {
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