package objetos;

import java.util.List;

public class Profesor {
	
	private String nombreProfesor;
	private Double capacidadEfectiva;
	private Double exceso;
	private List<Asignatura> preferencias;
	
	public Profesor(String nombreProfesor, Double capacidadEfectiva,Double exceso) {
		this.nombreProfesor = nombreProfesor;
		this.capacidadEfectiva = capacidadEfectiva;
		this.exceso = exceso;
	}

	public String getNombreProfesor() {
		return nombreProfesor;
	}

	public void setNombreProfesor(String nombreProfesor) {
		this.nombreProfesor = nombreProfesor;
	}

	public Double getCapacidadEfectiva() {
		return capacidadEfectiva;
	}

	public void setCapacidadEfectiva(Double capacidadEfectiva) {
		this.capacidadEfectiva = capacidadEfectiva;
	}

	

	@Override
	public String toString() {
		return "Profesor [nombreProfesor=" + nombreProfesor + ", capacidadEfectiva=" + capacidadEfectiva + ", exceso="
				+ exceso + ", preferencias=" + preferencias + "]";
	}

	public List<Asignatura> getPreferencias() {
		return preferencias;
	}

	public void setPreferencias(List<Asignatura> preferencias) {
		this.preferencias = preferencias;
	}

	public Double getExceso() {
		return exceso;
	}

	public void setExceso(Double exceso) {
		this.exceso = exceso;
	}
	
	

}
