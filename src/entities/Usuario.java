package entities;

import exceptions.*;

public class Usuario {
	
	private String nome;
	private String email;
	private int senha;
	private int id;
	
	public Usuario() { }

	public Usuario(String nome, int senha, String email) {
		this.nome = nome;
		this.senha = senha;
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Usuario [nome=" + nome + ", senha=" + senha + ", id=" + id + "]";
	}

	public void realizarLogin(String nome, int senha) throws InvalidLogin{	
		
			if(nome != this.nome || senha != this.senha) {
				throw new InvalidLogin("Login Incorreto");
			}
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getSenha() {
		return senha;
	}

	public void setSenha(int senha2) {
		this.senha = senha2;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	


}
