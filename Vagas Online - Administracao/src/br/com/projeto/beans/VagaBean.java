package br.com.projeto.beans;

import java.io.Serializable;

public class VagaBean implements Serializable {

	private static final long serialVersionUID = -254053325140513230L;

	private int idEstacionamento;
	private int id;
	private String codigo;
	private int largura;
	private int altura;
	private int comprimento;
	private TipoVagaBean tipoVagaBean;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLargura() {
		return largura;
	}

	public void setLargura(int largura) {
		this.largura = largura;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getIdEstacionamento() {
		return idEstacionamento;
	}

	public void setIdEstacionamento(int idEstacionamento) {
		this.idEstacionamento = idEstacionamento;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public TipoVagaBean getTipoVagaBean() {
		return tipoVagaBean;
	}

	public void setTipoVagaBean(TipoVagaBean tipoVagaBean) {
		this.tipoVagaBean = tipoVagaBean;
	}

	public int getComprimento() {
		return comprimento;
	}

	public void setComprimento(int comprimento) {
		this.comprimento = comprimento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + altura;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + comprimento;
		result = prime * result + id;
		result = prime * result + idEstacionamento;
		result = prime * result + largura;
		result = prime * result
				+ ((tipoVagaBean == null) ? 0 : tipoVagaBean.hashCode());
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
		VagaBean other = (VagaBean) obj;
		if (altura != other.altura)
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (comprimento != other.comprimento)
			return false;
		if (id != other.id)
			return false;
		if (idEstacionamento != other.idEstacionamento)
			return false;
		if (largura != other.largura)
			return false;
		if (tipoVagaBean == null) {
			if (other.tipoVagaBean != null)
				return false;
		} else if (!tipoVagaBean.equals(other.tipoVagaBean))
			return false;
		return true;
	}

}