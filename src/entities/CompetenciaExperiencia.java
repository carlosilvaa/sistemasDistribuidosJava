package entities;

public class CompetenciaExperiencia {

	private String competencia;
	private int experiencia;

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
		return "CompetenciaExperiencia [competencia=" + competencia + ", experiencia=" + experiencia + "]";
	}




}
