package br.com.projeto.beans;

import java.io.Serializable;

import br.com.projeto.daos.EstacionamentoDAO;
import br.com.projeto.enums.StatusEnum;

public class EstacionamentoBean implements Serializable {
	
	private static final long serialVersionUID = 6750485015161261228L;

	private int id;
	private String razaoSocial;
	private String nomeFantasia;
	private String cnpj;
	private String inscricaoMunicipal;
	private String inscricaoEstadual;
	private StatusBean statusBean;
	private EnderecoBean enderecoBean;
	
	private int quantidadeFuncionarios;
	private int quantidadeVagas;
	
	public int getQuantidadeFuncionarios() {
		return quantidadeFuncionarios;
	}
	public void setQuantidadeFuncionarios(int quantidadeFuncionarios) {
		this.quantidadeFuncionarios = quantidadeFuncionarios;
	}
	public int getQuantidadeVagas() {
		return quantidadeVagas;
	}
	public void setQuantidadeVagas(int quantidadeVagas) {
		this.quantidadeVagas = quantidadeVagas;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomeFantasia() {
		return nomeFantasia;
	}
	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getInscricaoMunicipal() {
		return inscricaoMunicipal;
	}
	public void setInscricaoMunicipal(String inscricaoMunicipal) {
		this.inscricaoMunicipal = inscricaoMunicipal;
	}
	public EnderecoBean getEnderecoBean() {
		return enderecoBean;
	}
	public void setEnderecoBean(EnderecoBean enderecoBean) {
		this.enderecoBean = enderecoBean;
	}
	public StatusBean getStatusBean() {
		return statusBean;
	}
	public void setStatusBean(StatusBean statusBean) {
		this.statusBean = statusBean;
	}
	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}
	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}
	public static int obterStatusEstacionamentoEncaminharAoStatusSeguinte(int idEstacionamento) {
		
		int status = 0;
		EstacionamentoBean estacionamentoBean = new EstacionamentoDAO().buscarPorId(idEstacionamento);
		
		if(StatusEnum.PENDENTE.getCodigo() == estacionamentoBean.getStatusBean().getId()) {
			status = StatusEnum.EM_PROGRESSO.getCodigo();
		} else if ( (StatusEnum.EM_PROGRESSO.getCodigo() == estacionamentoBean.getStatusBean().getId()) || (StatusEnum.ATIVO.getCodigo() == estacionamentoBean.getStatusBean().getId())) {
			status = StatusEnum.ATIVO.getCodigo();
		}
		
		return status;
	}
	
	public static void alteraStatusEstacionamentoParaOStatusSeguinte (int idEstacionamento) {
		int status = EstacionamentoBean.obterStatusEstacionamentoEncaminharAoStatusSeguinte(idEstacionamento);
		new EstacionamentoDAO().atualizarStatusEstacionamento(status, idEstacionamento);
	}

	public static int obterStatusEstacionamentoEncaminharAoStatusAnterior(int idEstacionamento) {
	
		int status = 0;
		EstacionamentoBean estacionamentoBean = new EstacionamentoDAO().buscarPorId(idEstacionamento);
		
		if(StatusEnum.ATIVO.getCodigo() == estacionamentoBean.getStatusBean().getId()) {
			status = StatusEnum.EM_PROGRESSO.getCodigo();
		} else if( (StatusEnum.EM_PROGRESSO.getCodigo() == estacionamentoBean.getStatusBean().getId()) || StatusEnum.PENDENTE.getCodigo() == estacionamentoBean.getStatusBean().getId()) {
			status = StatusEnum.PENDENTE.getCodigo();
		} 
				
		return status;
	}
	
	public static void alteraStatusEstacionamentoParaOStatusAnterior (int idEstacionamento) {
		int status = EstacionamentoBean.obterStatusEstacionamentoEncaminharAoStatusAnterior(idEstacionamento);
		new EstacionamentoDAO().atualizarStatusEstacionamento(status, idEstacionamento);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		result = prime * result
				+ ((enderecoBean == null) ? 0 : enderecoBean.hashCode());
		result = prime * result + id;
		result = prime
				* result
				+ ((inscricaoEstadual == null) ? 0 : inscricaoEstadual
						.hashCode());
		result = prime
				* result
				+ ((inscricaoMunicipal == null) ? 0 : inscricaoMunicipal
						.hashCode());
		result = prime * result
				+ ((nomeFantasia == null) ? 0 : nomeFantasia.hashCode());
		result = prime * result + quantidadeFuncionarios;
		result = prime * result + quantidadeVagas;
		result = prime * result
				+ ((razaoSocial == null) ? 0 : razaoSocial.hashCode());
		result = prime * result
				+ ((statusBean == null) ? 0 : statusBean.hashCode());
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
		EstacionamentoBean other = (EstacionamentoBean) obj;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		if (enderecoBean == null) {
			if (other.enderecoBean != null)
				return false;
		} else if (!enderecoBean.equals(other.enderecoBean))
			return false;
		if (id != other.id)
			return false;
		if (inscricaoEstadual == null) {
			if (other.inscricaoEstadual != null)
				return false;
		} else if (!inscricaoEstadual.equals(other.inscricaoEstadual))
			return false;
		if (inscricaoMunicipal == null) {
			if (other.inscricaoMunicipal != null)
				return false;
		} else if (!inscricaoMunicipal.equals(other.inscricaoMunicipal))
			return false;
		if (nomeFantasia == null) {
			if (other.nomeFantasia != null)
				return false;
		} else if (!nomeFantasia.equals(other.nomeFantasia))
			return false;
		if (quantidadeFuncionarios != other.quantidadeFuncionarios)
			return false;
		if (quantidadeVagas != other.quantidadeVagas)
			return false;
		if (razaoSocial == null) {
			if (other.razaoSocial != null)
				return false;
		} else if (!razaoSocial.equals(other.razaoSocial))
			return false;
		if (statusBean == null) {
			if (other.statusBean != null)
				return false;
		} else if (!statusBean.equals(other.statusBean))
			return false;
		return true;
	}

}