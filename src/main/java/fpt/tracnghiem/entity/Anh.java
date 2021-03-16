package fpt.tracnghiem.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Anh database table.
 * 
 */
@Entity
@Table(name="Anh")
@NamedQuery(name="Anh.findAll", query="SELECT a FROM Anh a")
public class Anh implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int idAnh;

	@Column(length=50)
	private String url;

	//bi-directional many-to-one association to CauHoi
	@ManyToOne
	@JoinColumn(name="idCauHoi")
	private CauHoi cauHoi;

	public Anh() {
	}

	public int getIdAnh() {
		return this.idAnh;
	}

	public void setIdAnh(int idAnh) {
		this.idAnh = idAnh;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public CauHoi getCauHoi() {
		return this.cauHoi;
	}

	public void setCauHoi(CauHoi cauHoi) {
		this.cauHoi = cauHoi;
	}

}