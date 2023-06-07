package database;

public class ProfesorDB {
	
	private Integer id_profesor;
	private String nombre_profesor;
	private Double capacidad_efectiva;
	private Double excedente;
	
	public ProfesorDB(Integer id_profesor, String nombre_profesor, Double capacidad_efectiva, Double excedente) {
		
		this.id_profesor = id_profesor;
		this.nombre_profesor = nombre_profesor;
		this.capacidad_efectiva = capacidad_efectiva;
		this.excedente = excedente;
	}

	public Integer getId_profesor() {
		return id_profesor;
	}

	public String getNombre_profesor() {
		return nombre_profesor;
	}

	public Double getCapacidad_efectiva() {
		return capacidad_efectiva;
	}

	public Double getExcedente() {
		return excedente;
	}

}
