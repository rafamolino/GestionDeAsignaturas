package database;

public class AsignaturaDB {
	private Integer id_asignatura;
	private String nombre_asignatura;
	private String nombre_titulacion;
	private String tipo_clase;
	private Integer grupo;
	private Integer cuatrimestre;
	private Double creditos;
	private String horario;
	
	public AsignaturaDB(Integer id_asignatura, String nombre_asignatura, String nombre_titulacion, String tipo_clase,
			Integer grupo, Integer cuatrimestre, Double creditos, String horario) {
		this.id_asignatura = id_asignatura;
		this.nombre_asignatura = nombre_asignatura;
		this.nombre_titulacion = nombre_titulacion;
		this.tipo_clase = tipo_clase;
		this.grupo = grupo;
		this.cuatrimestre = cuatrimestre;
		this.creditos = creditos;
		this.horario = horario;
	}

	public Integer getId_asignatura() {
		return id_asignatura;
	}

	public String getNombre_asignatura() {
		return nombre_asignatura;
	}

	public String getNombre_titulacion() {
		return nombre_titulacion;
	}

	public String getTipo_clase() {
		return tipo_clase;
	}

	public Integer getGrupo() {
		return grupo;
	}

	public Integer getCuatrimestre() {
		return cuatrimestre;
	}

	public Double getCreditos() {
		return creditos;
	}

	public String getHorario() {
		return horario;
	}


}
