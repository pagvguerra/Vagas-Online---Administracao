package br.com.projeto.beans;

import java.io.Serializable;

public class EstacionamentoTipoPagamentoBean implements Serializable {

	private static final long serialVersionUID = 377473801616006787L;

	private int idEstacionamento;
	private int idTipoPagamento;
	
	public int getIdEstacionamento() {
		return idEstacionamento;
	}
	public void setIdEstacionamento(int idEstacionamento) {
		this.idEstacionamento = idEstacionamento;
	}
	public int getIdTipoPagamento() {
		return idTipoPagamento;
	}
	public void setIdTipoPagamento(int idTipoPagamento) {
		this.idTipoPagamento = idTipoPagamento;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idEstacionamento;
		result = prime * result + idTipoPagamento;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EstacionamentoTipoPagamentoBean other = (EstacionamentoTipoPagamentoBean) obj;
		if (idEstacionamento != other.idEstacionamento)
			return false;
		if (idTipoPagamento != other.idTipoPagamento)
			return false;
		return true;
	}
	
}