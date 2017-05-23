package br.com.projeto.beans;

import java.io.Serializable;

public class AdministradorEstacionamentoBean extends UsuarioBean implements Serializable {

	private static final long serialVersionUID = -6214683397948925944L;

	private String status;
	
	public AdministradorEstacionamentoBean() {
		super();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdministradorEstacionamentoBean other = (AdministradorEstacionamentoBean) obj;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	
}