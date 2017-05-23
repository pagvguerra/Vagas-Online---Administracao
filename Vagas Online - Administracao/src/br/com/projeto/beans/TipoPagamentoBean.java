package br.com.projeto.beans;

import java.io.Serializable;

public class TipoPagamentoBean implements Serializable {

	private static final long serialVersionUID = -7874307450552026956L;

	private int id;
	private int nome;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNome() {
		return nome;
	}
	public void setNome(int nome) {
		this.nome = nome;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + nome;
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
		TipoPagamentoBean other = (TipoPagamentoBean) obj;
		if (id != other.id)
			return false;
		if (nome != other.nome)
			return false;
		return true;
	}

}