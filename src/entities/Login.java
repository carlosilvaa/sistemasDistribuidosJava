package entities;

public class Login {
    
    private String email;
    private String senha;
    private int idLoginCandidato;
    private String token;
    private Usuario usuario;
    
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getIdLoginCandidato() {
        return idLoginCandidato;
    }

    public void setIdLoginCandidato(int idLoginCandidato) {
        this.idLoginCandidato = idLoginCandidato;
    }

   
}