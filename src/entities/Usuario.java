package entities;

public class Usuario {
	
	private String nome;
	private String email;
	private String senha;
	private int id;
	
	public Usuario() { }

	public Usuario(String email, String senha, String nome) {
		this.nome = nome;
		this.senha = senha;
		this.email = email;
	}	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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
