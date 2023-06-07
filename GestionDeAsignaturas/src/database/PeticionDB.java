package database;

public class PeticionDB {
	private Integer id_profesor;
	private Integer preferencia;
	private Integer id_asignatura;
	private	String asignatura;

	public PeticionDB(Integer id_profesor, Integer preferencia, Integer id_asignatura, String asignatura) {
		this.id_profesor = id_profesor;
		this.preferencia = preferencia;
		this.id_asignatura = id_asignatura;
		this.asignatura = asignatura;
	}

	public Integer getId_profesor() {
		return id_profesor;
	}

	public Integer getPreferencia() {
		return preferencia;
	}

	public Integer getId_asignatura() {
		return id_asignatura;
	}

	public String getAsignatura() {
		return asignatura;
	}
	


}
