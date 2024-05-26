package entities;

public class Empresa {

	@Override
	public String toString() {
		return "Empresa [id=" + id + ", razaoSocial=" + razaoSocial + ", ramo=" + ramo + ", descricao=" + descricao
				+ ", email=" + email + ", senha=" + senha + ", CNPJ=" + CNPJ + "]";
	}

	private int id;
	private String razaoSocial;
	private String ramo;
	private String descricao;
	private String email;
	private String senha;
	private String CNPJ;

	public Empresa(int id, String razaoSocial, String rmao, String descricao, String email, String senha, String CNPJ) {
		super();
		this.id = id;
		this.razaoSocial = razaoSocial;
		this.ramo = rmao;
		this.descricao = descricao;
		this.email = email;
		this.senha = senha;
		this.CNPJ = CNPJ;
	}

	public Empresa() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getRamo() {
		return ramo;
	}

	public void setRamo(String ramo) {
		this.ramo = ramo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	public String getCNPJ() {
		return CNPJ;
	}

	public void setCNPJ(String CNPJ) {
		this.CNPJ = CNPJ;
	}

}
