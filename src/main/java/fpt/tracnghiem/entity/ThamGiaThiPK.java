package fpt.tracnghiem.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ThamGiaThi database table.
 * 
 */
@Embeddable
public class ThamGiaThiPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false, unique=true, nullable=false, length=50)
	private String username;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int idBoDe;

	public ThamGiaThiPK() {
	}
	public String getUsername() {
		return this.username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getIdBoDe() {
		return this.idBoDe;
	}
	public void setIdBoDe(int idBoDe) {
		this.idBoDe = idBoDe;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ThamGiaThiPK)) {
			return false;
		}
		ThamGiaThiPK castOther = (ThamGiaThiPK)other;
		return 
			this.username.equals(castOther.username)
			&& (this.idBoDe == castOther.idBoDe);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.username.hashCode();
		hash = hash * prime + this.idBoDe;
		
		return hash;
	}
}