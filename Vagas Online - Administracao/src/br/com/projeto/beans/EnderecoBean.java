package br.com.projeto.beans;

import java.io.Serializable;

public class EnderecoBean implements Serializable{

	private static final long serialVersionUID = -7483888490913726022L;
	
	private int id;
	private TipoLogradouroBean tipoLogradouroBean;
	private String nomeLogradouro;
	private int numero;
	private String cep;
	private BairroBean bairroBean;
	private CidadeBean cidadeBean;
	private EstadoBean estadoBean;
	private PaisBean paisBean;
	private CoordenadaBean coordenadaBean;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public TipoLogradouroBean getTipoLogradouroBean() {
		return tipoLogradouroBean;
	}
	public void setTipoLogradouroBean(TipoLogradouroBean tipoLogradouroBean) {
		this.tipoLogradouroBean = tipoLogradouroBean;
	}
	public String getNomeLogradouro() {
		return nomeLogradouro;
	}
	public void setNomeLogradouro(String nomeLogradouro) {
		this.nomeLogradouro = nomeLogradouro;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public BairroBean getBairroBean() {
		return bairroBean;
	}
	public void setBairroBean(BairroBean bairroBean) {
		this.bairroBean = bairroBean;
	}
	public CidadeBean getCidadeBean() {
		return cidadeBean;
	}
	public void setCidadeBean(CidadeBean cidadeBean) {
		this.cidadeBean = cidadeBean;
	}
	public EstadoBean getEstadoBean() {
		return estadoBean;
	}
	public void setEstadoBean(EstadoBean estadoBean) {
		this.estadoBean = estadoBean;
	}
	public PaisBean getPaisBean() {
		return paisBean;
	}
	public void setPaisBean(PaisBean paisBean) {
		this.paisBean = paisBean;
	}
	public CoordenadaBean getCoordenadaBean() {
		return coordenadaBean;
	}
	public void setCoordenadaBean(CoordenadaBean coordenadaBean) {
		this.coordenadaBean = coordenadaBean;
	}

}