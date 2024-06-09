package entities;

public class CompetenciaExperiencia {

	private int idCompetenciaExperiencia;
	private Usuario usuario;
	private String competencia;
	private int experiencia;

	// Getters and setters
	public int getIdCompetenciaExperiencia() {
		return idCompetenciaExperiencia;
	}

	public void setIdCompetenciaExperiencia(int idCompetenciaExperiencia) {
		this.idCompetenciaExperiencia = idCompetenciaExperiencia;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getCompetencia() {
		return competencia;
	}

	public void setCompetencia(String competencia) {
		this.competencia = competencia;
	}

	public int getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}

	@Override
	public String toString() {
		return "CompetenciaExperiencia [idCompetenciaExperiencia=" + idCompetenciaExperiencia + ", usuario=" + usuario
				+ ", competencia=" + competencia + ", experiencia=" + experiencia + "]";
	}
}
