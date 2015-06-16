package br.com.lhuckaz.sistema.model;

public class Usuario {

	private String nome;
	private String login;
	private String hash;
	private String salt;

	public Usuario(String nome, String login, String hash, String salt) {
		this.nome = nome;
		this.login = login;
		this.hash = hash;
		this.salt = salt;
	}

	public String getNome() {
		return nome;
	}

	public String getLogin() {
		return login;
	}

	public String getHash() {
		return hash;
	}

	public String getSalt() {
		return salt;
	}

}
