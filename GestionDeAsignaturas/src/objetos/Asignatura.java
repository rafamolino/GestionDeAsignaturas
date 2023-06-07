package objetos;

import java.util.HashMap;
import java.util.Map;

public class Asignatura {
	private Integer id_asignatura;
	private String titulacion;
	private String nombreAsignatura;
	private String tipoClase;
	private int grupo;
	private int cuatrimestre;
	private double creditos;
	private String horario;
	private Map<Integer, Integer> prioridadesProfesores;

	public Asignatura(Integer id_asignatura, String titulacion,  String nombreAsignatura,String tipoClase, int grupo, int cuatrimestre, double creditos, String horario) {
		this.id_asignatura = id_asignatura;
		this.titulacion = titulacion;
		this.nombreAsignatura = nombreAsignatura;
		this.tipoClase = tipoClase;
		this.grupo = grupo;
		this.cuatrimestre = cuatrimestre;
		this.creditos = creditos;
		this.horario = horario;
		this.prioridadesProfesores = new HashMap<>();

	}
	
	 public void setPrioridadProfesor(int idProfesor, int prioridad) {
	        prioridadesProfesores.put(idProfesor, prioridad);
	    }

	    public int getPrioridadProfesor(int idProfesor) {
	        return prioridadesProfesores.getOrDefault(idProfesor, 0);
	    }

	public String getTitulacion() {
		return titulacion;
	}

	public void setTitulacion(String titulacion) {
		this.titulacion = titulacion;
	}

	


	@Override
	public String toString() {
		return "Asignatura [id_asignatura=" + id_asignatura + ", titulacion=" + titulacion + ", nombreAsignatura="
				+ nombreAsignatura + ", tipoClase=" + tipoClase + ", grupo=" + grupo + ", cuatrimestre=" + cuatrimestre
				+ ", creditos=" + creditos + ", horario=" + horario + ", prioridadesProfesores=" + prioridadesProfesores
				+ "]";
	}

	public String getNombreAsignatura() {
		return nombreAsignatura;
	}

	public void setNombreAsignatura(String nombreAsignatura) {
		this.nombreAsignatura = nombreAsignatura;
	}

	public String getTipoClase() {
		return tipoClase;
	}

	public void setTipoClase(String tipoClase) {
		this.tipoClase = tipoClase;
	}

	public Integer getGrupo() {
		return grupo;
	}

	public void setGrupo(Integer grupo) {
		this.grupo = grupo;
	}

	public Integer getCuatrimestre() {
		return cuatrimestre;
	}

	public void setCuatrimestre(Integer cuatrimestre) {
		this.cuatrimestre = cuatrimestre;
	}

	public Double getCreditos() {
		return creditos;
	}

	public void setCreditos(Double creditos) {
		this.creditos = creditos;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public Integer getId_asignatura() {
		return id_asignatura;
	}

	public void setId_asignatura(Integer id_asignatura) {
		this.id_asignatura = id_asignatura;
	}

	// Getter y Setter para los atributos

}
