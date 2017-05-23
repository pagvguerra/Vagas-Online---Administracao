package br.com.projeto.beans;

public class TipoVagaBean {

	private int id;
	private int preco;
	private String nome;
	private int idEstacionamento;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getPreco() {
		return preco;
	}
	public void setPreco(int preco) {
		this.preco = preco;
	}
	public int getIdEstacionamento() {
		return idEstacionamento;
	}
	public void setIdEstacionamento(int idEstacionamento) {
		this.idEstacionamento = idEstacionamento;
	}
}