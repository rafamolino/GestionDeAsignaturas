package datos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import objetos.Asignatura;
import objetos.Profesor;

public class AsignacionAsignaturas {
	private Map<Integer, Map<Integer, Asignatura>> asignacion;

	public AsignacionAsignaturas() {
		asignacion = new HashMap<>();
	}

	public void asignarAsignaturas(List<Profesor> profesores,
			Map<Integer, Map<Integer, Asignatura>> preferenciasProfesores) {
		for (int i = 0; i < profesores.size(); i++) {
			int idProfesor = i + 1;
			Map<Integer, Asignatura> asignaturasAsignadas = new HashMap<>();
			double capacidadEfectiva = profesores.get(i).getCapacidadEfectiva();
			double exceso = profesores.get(i).getExceso();
			
			if(preferenciasProfesores.containsKey(idProfesor)) {
				
				// Verificar si el profesor tiene preferencias
				if (!preferenciasProfesores.get(idProfesor).isEmpty()) {
					Map<Integer, Asignatura> preferencias = preferenciasProfesores.get(idProfesor);

					for (int j = 0; j < preferencias.size(); j++) {// Bucle que recorre cada preferencia del profesor en el
																	// que estemos 
						int numPreferencia = j + 1;
						Asignatura asignaturaPrefencia = preferenciasProfesores.get(idProfesor).get(numPreferencia);
						double creditosAsignatura = asignaturaPrefencia.getCreditos();
						// Verificar si el profesor tiene créditos para dar alguna otra asignatura
						boolean creditosDisponibles = false;
						if ((creditosAsignatura / 3) <= exceso) {
							creditosDisponibles = true;
						}

						// Verificar si la asignatura está asignada a otro profesor
						boolean asignadaPreviamente = false;
						for (int k = 0; k < asignacion.size(); k++) {
							 Map<Integer, Asignatura> lista = asignacion.get(k+1);
							 for (Asignatura asignatura : lista.values()) {
								if(asignatura.getId_asignatura().equals(asignaturaPrefencia.getId_asignatura())) {
									asignadaPreviamente = true;
									break;
								}
							}							

						}

						// Verificar la compatibilidad horaria
						boolean compatibilidadHoraria = comprobarHorario(asignaturaPrefencia, asignaturasAsignadas);

						if (creditosDisponibles && !asignadaPreviamente && compatibilidadHoraria) {
							asignaturasAsignadas.put(numPreferencia,asignaturaPrefencia);
							exceso -= creditosAsignatura;

						}

					}
					if (asignaturasAsignadas.isEmpty()) {
						for (int j = 0; j < preferencias.size(); j++) {
							int numPreferencia = j + 1;
							Asignatura asignaturaPrefencia = preferenciasProfesores.get(idProfesor).get(j + 1);
							int profesorSustitucion = buscarProfesorAsignatura(asignaturaPrefencia, (i), profesores);
							if (profesorSustitucion > 0) {
								// Quitar la asignatura asignada previamente al profesor anterior
								Map<Integer, Asignatura> asignaturasPrevias = asignacion.get(profesorSustitucion - 1);
								asignaturasPrevias.remove(numPreferencia);
								asignaturasAsignadas.put(numPreferencia,asignaturaPrefencia);
								asignacion.remove(profesorSustitucion - 1);
								asignacion.put((profesorSustitucion - 1), asignaturasPrevias);
								break;
							}

						}

					}

				}
			}



			asignacion.put(idProfesor, asignaturasAsignadas);
		}
	}

	public boolean comprobarHorario(Asignatura asignatura, Map<Integer, Asignatura> asignaturasAsignadas) {
		boolean resultado = true;
		if (asignaturasAsignadas.isEmpty()) {
			return true;
		} else {
			for (Asignatura asignaturaAsignada : asignaturasAsignadas.values()) {

				if (!esCompatibleHorario(asignatura, asignaturaAsignada)) {
					return false;
				}
			}

		}

		return resultado;
	}

	public boolean esCompatibleHorario(Asignatura asignatura1, Asignatura asignatura2) {
		// Comparar horarios
		boolean cond1 = (asignatura1.getHorario().equals( asignatura2.getHorario()))
				&& (asignatura1.getCuatrimestre().equals(asignatura2.getCuatrimestre()));
		boolean cond2 = true;
		if (asignatura1.getHorario().contains(",") && asignatura2.getHorario().contains(",")) {
			String[] lista = asignatura1.getHorario().split(",");
			for (int i = 0; i < lista.length; i++) {
				if (asignatura2.getHorario().contains(lista[i])) {
					cond2 = false;
				}

			}
		} else if (asignatura1.getHorario().contains(",") && !asignatura2.getHorario().contains(",")) {
			String[] lista = asignatura1.getHorario().split(",");
			for (int i = 0; i < lista.length; i++) {
				String dia = lista[i];
				if (dia.contains("X") && asignatura2.getHorario().contains("Mi")
						&& (asignatura2.getHorario().contains("17") || asignatura2.getHorario().contains("19"))
						&& asignatura1.getCuatrimestre().equals(asignatura2.getCuatrimestre())) {
					cond2 = false;

				} else if (dia.contains("L") && asignatura2.getHorario().contains("L")
						&& (asignatura2.getHorario().contains("17") || asignatura2.getHorario().contains("19"))
						&& asignatura1.getCuatrimestre().equals(asignatura2.getCuatrimestre())) {
					cond2 = false;
				} else if (dia.contains("M") && asignatura2.getHorario().contains("Ma")
						&& (asignatura2.getHorario().contains("17") || asignatura2.getHorario().contains("19"))
						&& asignatura1.getCuatrimestre().equals(asignatura2.getCuatrimestre())) {
					cond2 = false;
				} else if (dia.contains("J") && asignatura2.getHorario().contains("J")
						&& (asignatura2.getHorario().contains("17") || asignatura2.getHorario().contains("19"))
						&& asignatura1.getCuatrimestre().equals(asignatura2.getCuatrimestre())) {
					cond2 = false;
				}

			}

		} else if (!asignatura1.getHorario().contains(",") && asignatura2.getHorario().contains(",")) {
			if (asignatura1.getHorario().contains("L") && asignatura2.getHorario().contains("L")
					&& (asignatura1.getHorario().contains("17") || asignatura1.getHorario().contains("19"))
					&& asignatura1.getCuatrimestre().equals(asignatura2.getCuatrimestre())) {
				cond2 = false;
			} else if (asignatura1.getHorario().contains("Ma") && asignatura2.getHorario().contains("M")
					&& (asignatura1.getHorario().contains("17") || asignatura1.getHorario().contains("19"))
					&& asignatura1.getCuatrimestre().equals(asignatura2.getCuatrimestre())) {
				cond2 = false;
			} else if (asignatura1.getHorario().contains("Mi") && asignatura2.getHorario().contains("X")
					&& (asignatura1.getHorario().contains("17") || asignatura1.getHorario().contains("19"))
					&& asignatura1.getCuatrimestre().equals(asignatura2.getCuatrimestre())) {
				cond2 = false;
			} else if (asignatura1.getHorario().contains("J") && asignatura2.getHorario().contains("J")
					&& (asignatura1.getHorario().contains("17") || asignatura1.getHorario().contains("19"))
					&& asignatura1.getCuatrimestre().equals(asignatura2.getCuatrimestre())) {
				cond2 = false;
			}

		}

		return !cond1 && cond2;
	}

	private int buscarProfesorAsignatura(Asignatura asignatura, Integer idProfesor, List<Profesor> profesores) {
		for (int i = idProfesor; i >= 1; i--) {
			Map<Integer, Asignatura> asignaturasAsignadas = asignacion.get(i);
			for (Asignatura asig : asignaturasAsignadas.values()) {
				if(asig.equals(asignatura)) {
					return i + 1;
				}
			}

		}
		return -1; // Si no se encuentra asignada a ningún profesor
	}

	public Map<Integer, Map<Integer, Asignatura>> getAsignacion() {
		return asignacion;
	}
}
