package entities;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import dao.EmpresaDAO;

public class Vaga {
	private int id;
	private String nome;
	private Double faixaSalarial;
	private List<String> competencias;
	private String descricao;
	private String email;
	private String estado;

	public Vaga() {
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getCodigo() {
		return id;
	}

	public void setCodigo(int codigo) {
		this.id = codigo;
	}

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

	public double getFaixaSalarial() {
		return faixaSalarial;
	}

	public void setFaixaSalarial(Double faixaSalarial) {
		this.faixaSalarial = faixaSalarial;
	}

	public List<String> getCompetencias() {
		return competencias;
	}

	public void setCompetencias(List<String> competencias) {
		this.competencias = competencias;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}


	@Override
	public String toString() {
		return "Vaga [id=" + id + ", nome=" + nome + ", faixaSalarial=" + faixaSalarial + ", competencias="
				+ competencias + ", descricao=" + descricao + ", email=" + email + ", estado=" + estado
				 + "]";
	}


}
