package fpt.tracnghiem.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the anh database table.
 * 
 */
@Entity
@Table(name="anh")
@NamedQuery(name="Anh.findAll", query="SELECT a FROM Anh a")
public class Anh implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_anh")
	private int idAnh;

	@Column(name="url",length = 100)
	private String url;

	//bi-directional many-to-one association to CauHoi
	@ManyToOne
	@JoinColumn(name="id_cau_hoi")
	private CauHoi cauHoi;

	@Override
	public String toString() {
		return "Anh [idAnh=" + idAnh + ", url=" + url + ", cauHoi=" + cauHoi + "]";
	}

	public Anh() {
	}
	
	public Anh(String url,CauHoi cauHoi) {
		this.url = url;
		this.cauHoi=cauHoi;
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