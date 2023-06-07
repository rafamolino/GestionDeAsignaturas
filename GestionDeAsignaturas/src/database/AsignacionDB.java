package database;

public class AsignacionDB {
	
	private Integer id_profesor;
	private	String nombre_asignatura;
	private String nombre_titulacion;
	private Integer cuatrimestre;
	private String grupo;
	private String horario;
	private Double creditos;
	
	
	public AsignacionDB(Integer id_profesor, String nombre_asignatura, String nombre_titulacion, Integer cuatrimestre, String grupo,
			String horario, Double creditos) {
		this.id_profesor = id_profesor;
		this.nombre_asignatura = nombre_asignatura;
		this.nombre_titulacion = nombre_titulacion;
		this.cuatrimestre = cuatrimestre;
		this.grupo = grupo;
		this.horario = horario;
		this.creditos = creditos;
	}


	public Integer getId_profesor() {
		return id_profesor;
	}


	public String getNombre_asignatura() {
		return nombre_asignatura;
	}


	public String getNombre_titulacion() {
		return nombre_titulacion;
	}


	public Integer getCuatrimestre() {
		return cuatrimestre;
	}


	public String getGrupo() {
		return grupo;
	}


	public String getHorario() {
		return horario;
	}


	public Double getCreditos() {
		return creditos;
	}



}
