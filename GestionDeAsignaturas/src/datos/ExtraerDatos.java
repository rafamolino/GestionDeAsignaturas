package datos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



import objetos.Asignatura;
import objetos.Profesor;

public class ExtraerDatos {
	
	private Map<Integer, Map<Integer,Asignatura>> asignacion;
	private List<Asignatura> asignaturas = new ArrayList<>();
	private List<Profesor> profesores = new ArrayList<>();
	private Map<Integer, Map<Integer, Asignatura>> preferenciasProfesores = new HashMap<>();

	public ExtraerDatos() {
		
		asignacion = new HashMap<>();
	}
	public void extraerDatos(String fichero) {
		
		try {
			// Cargar el archivo Excel
			String excelFilePath = fichero;
			Workbook workbook = new XSSFWorkbook(excelFilePath);
			
			// Crear un mapa para almacenar las preferencias de los profesores

			// Obtener la primera hoja del libro
			Sheet sheet = workbook.getSheetAt(0);
			int lastRow = sheet.getLastRowNum();

			// Recorrer las filas del Excel

			for (Row row : sheet) {
				//// Ignorar las primeras 7 filas (que contienen los nombres de las columnas)
				if (row.getRowNum() < 8) {
					continue;

				}

				// Obtener las celdas en cada fila

				Cell titulacionCell = row.getCell(0);
				Cell asignaturaCell = row.getCell(2);
				Cell actCell = row.getCell(4);
				Cell grupoCell = row.getCell(5);
				Cell cuatrimestreCell = row.getCell(6);
				Cell creditosCell = row.getCell(7);
				Cell horarioCell = row.getCell(9);

				if (titulacionCell != null && asignaturaCell != null) {
					Integer id_asignatura = row.getRowNum()-7;
					String titulacion = titulacionCell.getStringCellValue();
					String nombreAsignatura = asignaturaCell.getStringCellValue();
					String tipoClase = actCell.getStringCellValue();
					Integer grupo = (int) grupoCell.getNumericCellValue();
					Integer cuatrimestre = (int) cuatrimestreCell.getNumericCellValue();
					Double creditos = creditosCell.getNumericCellValue();
					String horario = horarioCell.getStringCellValue();
					Asignatura asignatura = new Asignatura(id_asignatura,titulacion, nombreAsignatura, tipoClase, grupo, cuatrimestre,
							creditos, horario);
					asignaturas.add(asignatura);

				}

			}

			// Obtener la fila que contiene los nombres de los profesores
			Row nombresRow = sheet.getRow(1);

			// Obtener el índice final para el bucle
			int indiceFinal = nombresRow.getLastCellNum();
			// Leer los datos de los profesores
			for (int columnIndex = 10; columnIndex < indiceFinal; columnIndex++) {
				// Obtener el nombre del profesor en la segunda fila
				Cell nombreCell = nombresRow.getCell(columnIndex);
				// Obtener la capacidad efectiva en la tercera fila
				Row capacidadRow = sheet.getRow(2);
				Cell capacidadCell = capacidadRow.getCell(columnIndex);
				if (nombreCell != null && capacidadCell != null) {

					Integer idProfesor = columnIndex - 9;
					String nombre = nombreCell.getStringCellValue();
					Double capacidad = capacidadCell.getNumericCellValue();
					List<Asignatura> pref = new ArrayList<>();
					Profesor profesor = new Profesor(nombre, capacidad, capacidad);
					profesores.add(profesor);

				}
			}

			for (int col = 10; col <= indiceFinal; col++) {
				int idProfesor = col - 9;
				Map<Integer, Asignatura> listPrioridades = new HashMap<>();
				Map<Integer, Asignatura> prioridades = new HashMap<>();
				Cell nombreCell = nombresRow.getCell(col);
				// Obtener la capacidad efectiva en la tercera fila
				Row capacidadRow = sheet.getRow(2);
				Cell capacidadCell = capacidadRow.getCell(col);
				if (nombreCell != null && capacidadCell != null) {
					for (int fila = 8; fila <= lastRow; fila++) {
						Row row = sheet.getRow(fila);

						if (row != null) {
							Cell celda = row.getCell(col);

							if (celda != null && celda.getCellType() == CellType.NUMERIC) {
								int prioridad = (int) celda.getNumericCellValue();
								prioridades.put(prioridad, asignaturas.get(fila - 8));
							}
						}
					}
					preferenciasProfesores.put(idProfesor, prioridades);

				}

			}


			AsignacionAsignaturas asignacionAsignaturas = new AsignacionAsignaturas();
			// Asignar las asignaturas a los profesores
			asignacionAsignaturas.asignarAsignaturas(profesores, preferenciasProfesores);

			// Acceder a la lista de asignaciones
			asignacion = asignacionAsignaturas.getAsignacion();

			

			// Cerrar el libro de Excel
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public List<Asignatura> getAsignaturas() {
		return asignaturas;
	}
	public List<Profesor> getProfesores() {
		return profesores;
	}
	public Map<Integer, Map<Integer,Asignatura>> getAsignacion() {
		return asignacion;
	}
	public Map<Integer, Map<Integer, Asignatura>> getPreferenciasProfesores() {
		return preferenciasProfesores;
	}
	

}
