package application;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import database.AsignacionDB;
import database.AsignaturaDB;
import database.DataBase;
import database.PeticionDB;
import database.ProfesorDB;
import datos.AsignacionAsignaturas;
import datos.ExtraerDatos;
import datos.GenerarPDF;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import objetos.Asignatura;
import objetos.Profesor;
import javafx.stage.Stage;

public class PrincipalController {

	@FXML
	private TableView<AsignacionDB> tablaVista;
	@FXML
	private TableColumn<AsignacionDB, String> col_profesor;
	@FXML
	private TableColumn<AsignacionDB, String> col_asignatura;
	@FXML
	private TableColumn<AsignacionDB, String> col_titulacion;
	@FXML
	private TableColumn<AsignacionDB, String> col_cuatrimestre;
	@FXML
	private TableColumn<AsignacionDB, String> col_grupo;
	@FXML
	private TableColumn<AsignacionDB, String> col_horario;
	@FXML
	private TableColumn<AsignacionDB, String> col_creditos;
	@FXML
	private TableView<ProfesorDB> tablaProfesores;
	@FXML
	private TableColumn<ProfesorDB, String> colP_profesor;
	@FXML
	private TableColumn<ProfesorDB, String> colP_capacidad;
	@FXML
	private TableColumn<ProfesorDB, String> colP_excedente;
	@FXML
	private TableView<PeticionDB> tablaPeticiones;
	@FXML
	private TableColumn<PeticionDB, String> colPe_profesor;
	@FXML
	private TableColumn<PeticionDB, String> colPe_preferencia;
	@FXML
	private TableColumn<PeticionDB, String> colPe_id_asignatura;
	@FXML
	private TableColumn<PeticionDB, String> colPe_asignatura;
	@FXML
	private TableView<AsignaturaDB> tablaAsignaturas;
	@FXML
	private TableColumn<AsignaturaDB, String> colA_asignatura;
	@FXML
	private TableColumn<AsignaturaDB, String> colA_titulacion;
	@FXML
	private TableColumn<AsignaturaDB, String> colA_act;
	@FXML
	private TableColumn<AsignaturaDB, String> colA_grupo;
	@FXML
	private TableColumn<AsignaturaDB, String> colA_cuatrimestre;
	@FXML
	private TableColumn<AsignaturaDB, String> colA_horario;
	@FXML
	private TableColumn<AsignaturaDB, String> colA_creditos;
	@FXML
	private Button btnCargarDatos;
	@FXML
	private Button btnAsignaciones;
	@FXML
	private Button btnProfesores;
	@FXML
	private Button btnAsignaturas;
	@FXML
	private Button btnPeticiones;
	@FXML
	private Button btnModificarDatos;
	@FXML
	private AnchorPane ventanaAsignaciones;
	@FXML
	private AnchorPane ventanaCarga;
	@FXML
	private AnchorPane ventanaModificaciones;
	@FXML
	private AnchorPane ventanaProfesores;
	@FXML
	private AnchorPane ventanaAsignaturas;
	@FXML
	private AnchorPane ventanaPeticiones;
	@FXML
	private Button btnSeleccionarFichero;
	@FXML
	private Label labFichero;
	@FXML
	private Button btnEjecutar;
	@FXML
	private Button btnDescargarPDF;

	@FXML
	private TextField buscarAsignacion;
	@FXML
	private TextField labBuscarProfesor;
	@FXML
	private TextField labBuscarAsignatura;
	@FXML
	private TextField txtProfesor;
	@FXML
	private TextField txtProfesorID;
	@FXML
	private TextField txtCapacidad;
	@FXML
	private TextField txtExcedente;
	@FXML
	private TextField txtAsignaturaID;
	@FXML
	private TextField txtAsignatura;
	@FXML
	private TextField txtTitulacion;
	@FXML
	private TextField txtCuatrimestre;
	@FXML
	private TextField txtAct;
	@FXML
	private TextField txtGrupo;
	@FXML
	private TextField txtCreditos;
	@FXML
	private TextField txtHorario;
	@FXML
	private ComboBox<Integer> comboIdProfesor;
	@FXML
	private ComboBox<Integer> comboIdAsignaturas;
	@FXML
	private Spinner<Integer> spinnerPreferencia;

	private Stage stagePrincipal;
	private Connection connect;
	private PreparedStatement prepare;
	private ResultSet result;
	private Map<Integer, Map<Integer, Asignatura>> asignaciones;
	private Map<Integer, Map<Integer, Asignatura>> listaPreferenciasProfesores;
	private List<Profesor> profesores;

	public void obtenerAsignaturas() {
		List<Integer> listaIdAsignaturas = new ArrayList<>();

		String sql = "SELECT id_asignatura FROM asignaturas";
		DataBase db = new DataBase();
		connect = db.connectDB();

		try {
			prepare = connect.prepareStatement(sql);
			result = prepare.executeQuery();

			while (result.next()) {
				listaIdAsignaturas.add(result.getInt("id_asignatura"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		comboIdAsignaturas.setItems(FXCollections.observableArrayList(listaIdAsignaturas));

	}

	public void obtenerProfesores() {
		List<Integer> listaIdProfesores = new ArrayList<>();

		String sql = "SELECT id_profesor FROM profesores";
		DataBase db = new DataBase();
		connect = db.connectDB();

		try {
			prepare = connect.prepareStatement(sql);
			result = prepare.executeQuery();

			while (result.next()) {
				listaIdProfesores.add(result.getInt("id_profesor"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		comboIdProfesor.setItems(FXCollections.observableArrayList(listaIdProfesores));

	}

	public ObservableList<AsignaturaDB> listaDatosAsignaturas() {
		ObservableList<AsignaturaDB> listaDatos = FXCollections.observableArrayList();
		String sql = "SELECT * FROM asignaturas";
		DataBase db = new DataBase();
		connect = db.connectDB();

		try {
			prepare = connect.prepareStatement(sql);
			result = prepare.executeQuery();
			AsignaturaDB asignatura;

			while (result.next()) {
				asignatura = new AsignaturaDB(result.getInt("id_asignatura"), result.getString("nombre_asignatura"),
						result.getString("nombre_titulacion"), result.getString("tipo_clase"), result.getInt("grupo"),
						result.getInt("cuatrimestre"), result.getDouble("creditos"), result.getString("horario"));
				listaDatos.add(asignatura);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaDatos;
	}

	private ObservableList<AsignaturaDB> listaAsignaturas;

	public void mostrarListaAsignaturas() {
		listaAsignaturas = listaDatosAsignaturas();

		colA_asignatura.setCellValueFactory(new PropertyValueFactory<>("nombre_asignatura"));
		colA_titulacion.setCellValueFactory(new PropertyValueFactory<>("nombre_titulacion"));
		colA_act.setCellValueFactory(new PropertyValueFactory<>("tipo_clase"));
		colA_grupo.setCellValueFactory(new PropertyValueFactory<>("grupo"));
		colA_cuatrimestre.setCellValueFactory(new PropertyValueFactory<>("cuatrimestre"));
		colA_creditos.setCellValueFactory(new PropertyValueFactory<>("creditos"));
		colA_horario.setCellValueFactory(new PropertyValueFactory<>("horario"));

		tablaAsignaturas.setItems(listaAsignaturas);

	}

	public void asignaturaSeleccionado() {

		AsignaturaDB asignatura = tablaAsignaturas.getSelectionModel().getSelectedItem();
		int num = tablaAsignaturas.getSelectionModel().getSelectedIndex();

		if ((num - 1) < -1) {
			return;
		}
		txtAsignaturaID.setEditable(false);
		txtAsignaturaID.setText(String.valueOf(asignatura.getId_asignatura()));
		txtAsignatura.setText(asignatura.getNombre_asignatura());
		txtTitulacion.setText(String.valueOf(asignatura.getNombre_titulacion()));
		txtAct.setText(String.valueOf(asignatura.getTipo_clase()));
		txtGrupo.setText(String.valueOf(asignatura.getGrupo()));
		txtCuatrimestre.setText(String.valueOf(asignatura.getCuatrimestre()));
		txtHorario.setText(String.valueOf(asignatura.getHorario()));
		txtCreditos.setText(String.valueOf(asignatura.getCreditos()));

	}

	public ObservableList<PeticionDB> listaDatosPeticiones() {
		ObservableList<PeticionDB> listaDatos = FXCollections.observableArrayList();
		String sql = "SELECT p.id_profesor, p.preferencia,a.id_asignatura, CONCAT(a.nombre_asignatura, ' - C', a.cuatrimestre, ' - G', a.grupo) AS asignatura FROM peticiones p INNER JOIN asignaturas a ON p.id_asignatura=a.id_asignatura order by p.id_profesor;";
		DataBase db = new DataBase();
		connect = db.connectDB();

		try {
			prepare = connect.prepareStatement(sql);
			result = prepare.executeQuery();
			PeticionDB peticion;

			while (result.next()) {
				peticion = new PeticionDB(result.getInt("id_profesor"), result.getInt("preferencia"),
						result.getInt("id_asignatura"), result.getString("asignatura"));
				listaDatos.add(peticion);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaDatos;
	}

	private ObservableList<PeticionDB> listaPeticiones;

	public void mostrarListaPeticiones() {
		listaPeticiones = listaDatosPeticiones();

		colPe_profesor.setCellValueFactory(new PropertyValueFactory<>("id_profesor"));
		colPe_preferencia.setCellValueFactory(new PropertyValueFactory<>("preferencia"));
		colPe_id_asignatura.setCellValueFactory(new PropertyValueFactory<>("id_asignatura"));
		colPe_asignatura.setCellValueFactory(new PropertyValueFactory<>("asignatura"));

		tablaPeticiones.setItems(listaPeticiones);

	}

	public ObservableList<ProfesorDB> listaDatosProfesores() {
		ObservableList<ProfesorDB> listaDatos = FXCollections.observableArrayList();
		String sql = "SELECT * FROM profesores";
		DataBase db = new DataBase();
		connect = db.connectDB();

		try {
			prepare = connect.prepareStatement(sql);
			result = prepare.executeQuery();
			ProfesorDB profesor;

			while (result.next()) {
				profesor = new ProfesorDB(result.getInt("id_profesor"), result.getString("nombre_profesor"),
						result.getDouble("capacidad_efectiva"), result.getDouble("excedente"));
				listaDatos.add(profesor);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaDatos;
	}

	private ObservableList<ProfesorDB> listaProfesores;

	public void mostrarListaProfesores() {
		listaProfesores = listaDatosProfesores();

		colP_profesor.setCellValueFactory(new PropertyValueFactory<>("id_profesor"));
		colP_capacidad.setCellValueFactory(new PropertyValueFactory<>("capacidad_efectiva"));
		colP_excedente.setCellValueFactory(new PropertyValueFactory<>("excedente"));

		tablaProfesores.setItems(listaProfesores);

	}

	public void profesorSeleccionado() {

		ProfesorDB profesor = tablaProfesores.getSelectionModel().getSelectedItem();
		int num = tablaProfesores.getSelectionModel().getSelectedIndex();

		if ((num - 1) < -1) {
			return;
		}
		txtProfesorID.setEditable(false);
		txtProfesor.setEditable(false);
		txtProfesorID.setText(String.valueOf(profesor.getId_profesor()));
		txtProfesor.setText(profesor.getNombre_profesor());
		txtCapacidad.setText(String.valueOf(profesor.getCapacidad_efectiva()));
		txtExcedente.setText(String.valueOf(profesor.getExcedente()));

	}

	public ObservableList<AsignacionDB> listaDatosAsignaciones() {
		ObservableList<AsignacionDB> listaDatos = FXCollections.observableArrayList();
		String sql = "SELECT * FROM profesores P inner join asignaciones A on p.id_profesor=A.id_profesor inner join asignaturas asig on a.id_asignatura=asig.id_asignatura";
		DataBase db = new DataBase();
		connect = db.connectDB();

		try {
			prepare = connect.prepareStatement(sql);
			result = prepare.executeQuery();
			AsignacionDB asignacion;

			while (result.next()) {
				String grupo = result.getInt("grupo") + " - " +  result.getString("tipo_clase");
				asignacion = new AsignacionDB(result.getInt("id_profesor"), result.getString("nombre_asignatura"),
						result.getString("nombre_titulacion"), result.getInt("cuatrimestre"), grupo,
						result.getString("horario"), result.getDouble("creditos"));
				listaDatos.add(asignacion);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaDatos;

	}

	private ObservableList<AsignacionDB> listaAsignaciones;

	public void mostrarListaAsignaciones() {
		listaAsignaciones = listaDatosAsignaciones();

		col_profesor.setCellValueFactory(new PropertyValueFactory<>("id_profesor"));
		col_asignatura.setCellValueFactory(new PropertyValueFactory<>("nombre_asignatura"));
		col_titulacion.setCellValueFactory(new PropertyValueFactory<>("nombre_titulacion"));
		col_cuatrimestre.setCellValueFactory(new PropertyValueFactory<>("cuatrimestre"));
		col_grupo.setCellValueFactory(new PropertyValueFactory<>("grupo"));
		col_horario.setCellValueFactory(new PropertyValueFactory<>("horario"));
		col_creditos.setCellValueFactory(new PropertyValueFactory<>("creditos"));

		tablaVista.setItems(listaAsignaciones);

	}

	public void eliminarDatos() {
		String sqlProfesores = "DELETE FROM profesores";
		String sqlAsignaturas = "DELETE FROM asignaturas";
		String sqlAsignaciones = "DELETE FROM asignaciones";
		String sqlPeticiones = "DELETE FROM peticiones";

		DataBase db = new DataBase();
		connect = db.connectDB();
		PreparedStatement prepareProfesores = null;
		PreparedStatement prepareAsignaturas = null;
		PreparedStatement prepareAsignaciones = null;
		PreparedStatement preparePeticiones = null;

		try {
			// Eliminar datos de la tabla "asignaciones"
			prepareAsignaciones = connect.prepareStatement(sqlAsignaciones);
			prepareAsignaciones.executeUpdate();

			// Eliminar datos de la tabla "profesores"
			prepareProfesores = connect.prepareStatement(sqlProfesores);
			prepareProfesores.executeUpdate();

			// Eliminar datos de la tabla "asignaturas"
			prepareAsignaturas = connect.prepareStatement(sqlAsignaturas);
			prepareAsignaturas.executeUpdate();

			// Eliminar datos de la tabla "peticiones"
			preparePeticiones = connect.prepareStatement(sqlPeticiones);
			preparePeticiones.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void añadirProfesores(List<Profesor> profesores, List<Double> excedentes) {
		String sql = "INSERT INTO profesores " + "(id_profesor, nombre_profesor, capacidad_efectiva, excedente) "
				+ "VALUES(?,?,?,?) ";

		DataBase db = new DataBase();
		connect = db.connectDB();

		try {

			connect.setAutoCommit(false); // Desactivar el modo autocommit

			prepare = connect.prepareStatement(sql);

			for (int i = 0; i < profesores.size(); i++) {

				double excedente = Math.round((profesores.get(i).getCapacidadEfectiva() - excedentes.get(i)) * 100.0)
						/ 100.0;
				double capacidadEfctiva = Math.round(profesores.get(i).getCapacidadEfectiva() * 100.0) / 100.0;



				prepare.setInt(1, i + 1);
				prepare.setString(2, profesores.get(i).getNombreProfesor());
				prepare.setDouble(3, capacidadEfctiva);
				prepare.setDouble(4, excedente);

				prepare.addBatch(); // Agregar cada inserción al lote

			}

			prepare.executeBatch(); // Ejecutar el lote de inserción

			connect.commit(); // Confirmar los cambios en la base de datos

		} catch (Exception e) {
			try {
				connect.rollback(); // Revertir los cambios en caso de error
			} catch (SQLException rollbackException) {
				rollbackException.printStackTrace();
			}
			e.printStackTrace();
		}

	}

	public void añadirAsignaturas(List<Asignatura> asignaturas) {
		String sql = "INSERT INTO asignaturas "
				+ "(id_asignatura, nombre_asignatura, nombre_titulacion, tipo_clase,grupo, cuatrimestre, creditos, horario) "
				+ "VALUES(?,?,?,?,?,?,?,?) ";

		DataBase db = new DataBase();
		connect = db.connectDB();

		try {

			connect.setAutoCommit(false); // Desactivar el modo autocommit

			prepare = connect.prepareStatement(sql);

			for (int i = 0; i < asignaturas.size(); i++) {
				prepare.setInt(1, i + 1);
				prepare.setString(2, asignaturas.get(i).getNombreAsignatura());
				prepare.setString(3, asignaturas.get(i).getTitulacion());
				prepare.setString(4, asignaturas.get(i).getTipoClase());
				prepare.setInt(5, asignaturas.get(i).getGrupo());
				prepare.setInt(6, asignaturas.get(i).getCuatrimestre());
				prepare.setDouble(7, asignaturas.get(i).getCreditos());
				prepare.setString(8, asignaturas.get(i).getHorario());

				prepare.addBatch(); // Agregar cada inserción al lote

			}

			prepare.executeBatch(); // Ejecutar el lote de inserción

			connect.commit(); // Confirmar los cambios en la base de datos

		} catch (Exception e) {
			try {
				connect.rollback(); // Revertir los cambios en caso de error
			} catch (SQLException rollbackException) {
				rollbackException.printStackTrace();
			}
			e.printStackTrace();
		}

	}

	public void añadirAsignaciones(Map<Integer, Map<Integer, Asignatura>> datosAsignaciones) {


		Integer contador =0;
		String sql = "INSERT INTO asignaciones " + "(id_profesor, id_asignatura) " + "VALUES(?,?)";

		DataBase db = new DataBase();
		connect = db.connectDB();

		try {

			connect.setAutoCommit(false); // Desactivar el modo autocommit

			prepare = connect.prepareStatement(sql);

			for (int i = 0; i < datosAsignaciones.size(); i++) {
				Map<Integer, Asignatura> lista = datosAsignaciones.get(i + 1);
				List<Asignatura> asignaturas = new ArrayList<>();
				for (Asignatura asig : lista.values()) {
					asignaturas.add(asig);
				}

				for (int j = 0; j < asignaturas.size(); j++) {
					Asignatura asignatura = asignaturas.get(j);
					contador+=1;
					prepare.setInt(1, i + 1);
					prepare.setInt(2, asignatura.getId_asignatura());

					prepare.addBatch(); // Agregar cada inserción al lote

				}
			}

			prepare.executeBatch(); // Ejecutar el lote de inserción

			connect.commit(); // Confirmar los cambios en la base de datos
			

		} catch (Exception e) {
			try {
				connect.rollback(); // Revertir los cambios en caso de error
			} catch (SQLException rollbackException) {
				rollbackException.printStackTrace();
			}
			e.printStackTrace();
		}

	}

	public void añadirPeticiones(Map<Integer, Map<Integer, Asignatura>> datosAsignaciones) {

		String sql = "INSERT INTO peticiones " + "(id_profesor,preferencia,id_asignatura) " + "VALUES(?,?,?)";
		DataBase db = new DataBase();
		connect = db.connectDB();

		try {

			connect.setAutoCommit(false); // Desactivar el modo autocommit

			prepare = connect.prepareStatement(sql);

			for (int i = 0; i < datosAsignaciones.size(); i++) {
				Map<Integer, Asignatura> lista = datosAsignaciones.get(i + 1);
				for (Asignatura asig : lista.values()) {
					Integer clave = buscarClave(lista, asig);
					prepare.setInt(1, i + 1);
					prepare.setInt(2, clave);
					prepare.setInt(3, asig.getId_asignatura());

					prepare.addBatch(); // Agregar cada inserción al lote

				}

			}

			prepare.executeBatch(); // Ejecutar el lote de inserción

			connect.commit(); // Confirmar los cambios en la base de datos

		} catch (Exception e) {
			try {
				connect.rollback(); // Revertir los cambios en caso de error
			} catch (SQLException rollbackException) {
				rollbackException.printStackTrace();
			}
			e.printStackTrace();
		}

	}

	public static <K, V> K buscarClave(Map<K, V> map, V valorBuscado) {
		for (Map.Entry<K, V> entry : map.entrySet()) {
			if (entry.getValue().equals(valorBuscado)) {
				return entry.getKey();
			}
		}
		return null;
	}

	public void actualizarProfesor() {
		String profesorID = txtProfesorID.getText();
		if (!profesorID.isEmpty()) {
			String capacidad = txtCapacidad.getText();
			String sql = "UPDATE profesores SET capacidad_efectiva=" + capacidad + " WHERE id_profesor=" + profesorID;

			DataBase db = new DataBase();
			connect = db.connectDB();

			PreparedStatement prepareProfesores = null;

			try {

				prepareProfesores = connect.prepareStatement(sql);
				prepareProfesores.executeUpdate();

				mostrarListaProfesores();

			} catch (Exception e) {

				Alert al = new Alert(AlertType.ERROR);
				al.setTitle("Error en la modificacion de datos");
				al.setHeaderText(null);
				al.setContentText("Comprueba bien los datos insertados o consulte con el administrador");
				al.showAndWait();

			}
		} else {
			Alert al = new Alert(AlertType.ERROR);
			al.setTitle("Error en la modificacion de datos");
			al.setHeaderText(null);
			al.setContentText("No hay campo ID");
			al.showAndWait();
		}

	}

	public void actualizarAsignatura() {
		String asignaturaID = txtAsignaturaID.getText();
		if (!asignaturaID.isEmpty()) {
			String creditos = txtCreditos.getText();
			String horario = txtHorario.getText();
			String sql = "UPDATE asignaturas SET creditos=" + creditos + ", horario='" + horario
					+ "' WHERE id_asignatura=" + asignaturaID;

			DataBase db = new DataBase();
			connect = db.connectDB();

			PreparedStatement prepareAsignaturas = null;

			try {

				prepareAsignaturas = connect.prepareStatement(sql);
				prepareAsignaturas.executeUpdate();

				mostrarListaAsignaturas();

			} catch (Exception e) {

				Alert al = new Alert(AlertType.ERROR);
				al.setTitle("Error en la modificacion de datos");
				al.setHeaderText(null);
				al.setContentText("Comprueba bien los datos insertados o consulte con el administrador");
				al.showAndWait();

			}
		} else {
			Alert al = new Alert(AlertType.ERROR);
			al.setTitle("Error en la modificacion de datos");
			al.setHeaderText(null);
			al.setContentText("No hay campo ID");
			al.showAndWait();
		}

	}

	public void eliminarAsignatura() {
		String asignaturaID = txtAsignaturaID.getText();
		if (!asignaturaID.isEmpty()) {
			String sql = "Delete from asignaturas WHERE id_asignatura=" + asignaturaID;

			DataBase db = new DataBase();
			connect = db.connectDB();

			PreparedStatement prepareAsignaturas = null;

			try {

				prepareAsignaturas = connect.prepareStatement(sql);
				prepareAsignaturas.executeUpdate();

				mostrarListaAsignaturas();

			} catch (Exception e) {

				Alert al = new Alert(AlertType.ERROR);
				al.setTitle("Error en la eliminación de datos");
				al.setHeaderText(null);
				al.setContentText("Ocurrió un error en el proceso de eliminación.");
				al.showAndWait();

			}
			limpiarAsignatura();
		} else {
			Alert al = new Alert(AlertType.ERROR);
			al.setTitle("Error en la eliminación de datos");
			al.setHeaderText(null);
			al.setContentText("No hay campo ID");
			al.showAndWait();
		}

	}

	public void eliminarProfesor() {
		String profesorID = txtProfesorID.getText();
		if (!profesorID.isEmpty()) {
			String sql = "Delete from profesores WHERE id_profesor=" + profesorID;

			DataBase db = new DataBase();
			connect = db.connectDB();

			PreparedStatement prepareProfesores = null;

			try {

				prepareProfesores = connect.prepareStatement(sql);
				prepareProfesores.executeUpdate();

				mostrarListaProfesores();

			} catch (Exception e) {

				Alert al = new Alert(AlertType.ERROR);
				al.setTitle("Error en la eliminación de datos");
				al.setHeaderText(null);
				al.setContentText("Ocurrió un error en el proceso de eliminación.");
				al.showAndWait();

			}
			limpiarProfesor();
		} else {
			Alert al = new Alert(AlertType.ERROR);
			al.setTitle("Error en la eliminación de datos");
			al.setHeaderText(null);
			al.setContentText("No hay campo ID");
			al.showAndWait();
		}

	}

	public void eliminarPeticion() {
		Integer profesorID = comboIdProfesor.getValue();
		Integer asignaturaID = comboIdAsignaturas.getValue();
		if (profesorID != null && asignaturaID != null) {
			String consulta = "SELECT COUNT(*) as registros from peticiones WHERE id_profesor=" + profesorID
					+ " AND id_asignatura=" + asignaturaID;
			String consulta2 = "SELECT max(preferencia) as maximo FROM peticiones where id_profesor=" + profesorID;
			String consulta3 = "SELECT preferencia FROM peticiones where id_profesor=" + profesorID+ " AND id_asignatura=" +asignaturaID;

			String sql = "Delete from peticiones WHERE id_profesor=" + profesorID + " AND id_asignatura="
					+ asignaturaID;

			DataBase db = new DataBase();
			connect = db.connectDB();

			PreparedStatement preparePeticiones = null;
			PreparedStatement prepareConsulta = null;
			PreparedStatement prepareConsulta2 = null;
			PreparedStatement prepareConsulta3= null;
			
			


			try {
				prepareConsulta2 = connect.prepareStatement(consulta2);
				result = prepareConsulta2.executeQuery();
				Integer preferencia_Maxima=0;
				while (result.next()) {
					preferencia_Maxima = result.getInt("maximo");
				}
				prepareConsulta3 = connect.prepareStatement(consulta3);
				result = prepareConsulta3.executeQuery();
				Integer preferencia_Eliminar = 0;
				while (result.next()) {
					preferencia_Eliminar = result.getInt("preferencia");
				}
				

				prepareConsulta = connect.prepareStatement(consulta);
				result = prepareConsulta.executeQuery();
				Integer registros = 0;
				while (result.next()) {
					registros = result.getInt("registros");
				}
				if (registros > 0) {
					if(preferencia_Eliminar==preferencia_Maxima) {
						preparePeticiones = connect.prepareStatement(sql);
						preparePeticiones.executeUpdate();

						mostrarListaPeticiones();						
					}
					else {
						Alert al = new Alert(AlertType.ERROR);
						al.setTitle("Eliminación de petición");
						al.setHeaderText(null);
						al.setContentText("Antes de eliminar esa petición tienes que eliminar la petición cuya perferencia sea mayor.");
						al.showAndWait();
					}
					
				} else {
					Alert al = new Alert(AlertType.ERROR);
					al.setTitle("Eliminación de petición");
					al.setHeaderText(null);
					al.setContentText("No existe esa petición por lo que no se puede eliminar.");
					al.showAndWait();
				}

			} catch (Exception e) {

				Alert al = new Alert(AlertType.ERROR);
				al.setTitle("Error en la eliminación de datos");
				al.setHeaderText(null);
				al.setContentText("Ocurrió un error en el proceso de eliminación.");
				al.showAndWait();

			}
			limpiarProfesor();
		} else {
			Alert al = new Alert(AlertType.ERROR);
			al.setTitle("Error en la eliminación de datos");
			al.setHeaderText(null);
			al.setContentText("No hay campo ID");
			al.showAndWait();
		}

	}

	public void limpiarAsignatura() {
		txtAsignatura.setText("");
		txtAsignaturaID.setText("");
		txtTitulacion.setText("");
		txtAct.setText("");
		txtGrupo.setText("");
		txtCuatrimestre.setText("");
		txtHorario.setText("");
		txtCreditos.setText("");
		txtAsignaturaID.setEditable(true);

	}

	public void limpiarProfesor() {
		txtProfesor.setText("");
		txtProfesorID.setText("");
		txtCapacidad.setText("");
		txtExcedente.setText("");
		txtProfesorID.setEditable(true);
		txtProfesor.setEditable(true);
	}

	public void añadirProfesor() {

		String profesorID = txtProfesorID.getText();
		String profesor = txtProfesor.getText();
		String capacidad = txtCapacidad.getText();
		if (!profesorID.isEmpty() && !profesor.isEmpty() && !capacidad.isEmpty()) {
			String sql = "INSERT INTO profesores " + "(id_profesor, nombre_profesor, capacidad_efectiva, excedente) "
					+ "VALUES(" + profesorID + ",'" + profesor + "'," + capacidad + "," + 0.0 + ")";

			DataBase db = new DataBase();
			connect = db.connectDB();

			PreparedStatement prepareProfesores = null;

			try {

				prepareProfesores = connect.prepareStatement(sql);
				prepareProfesores.executeUpdate();

				mostrarListaProfesores();

			} catch (Exception e) {

				Alert al = new Alert(AlertType.ERROR);
				al.setTitle("Error en la insercción de datos");
				al.setHeaderText(null);
				al.setContentText("Ocurrió un error en el proceso de insercción.");
				al.showAndWait();

			}
			limpiarProfesor();
		} else {
			Alert al = new Alert(AlertType.ERROR);
			al.setTitle("Error en la insercción de datos");
			al.setHeaderText(null);
			al.setContentText("Introduce todos los datos");
			al.showAndWait();
		}

	}

	public void añadirPeticion() {

		Integer profesorID = comboIdProfesor.getValue();
		Integer preferencia = spinnerPreferencia.getValue();
		Integer asignaturaID = comboIdAsignaturas.getValue();
		if (profesorID != null && preferencia != null && asignaturaID != null) {
			String sql = "INSERT INTO peticiones " + "(id_profesor, preferencia, id_asignatura) " + "VALUES("
					+ profesorID + "," + preferencia + "," + asignaturaID + ")";
			String consulta = "SELECT max(preferencia) as maximo FROM peticiones where id_profesor=" + profesorID;
			String consulta2 = "SELECT COUNT(*) as registros from peticiones WHERE id_profesor=" + profesorID
					+ " AND id_asignatura=" + asignaturaID;
			DataBase db = new DataBase();
			connect = db.connectDB();

			PreparedStatement preparePeticiones = null;
			PreparedStatement prepareConsulta = null;
			PreparedStatement prepareConsulta2 = null;

			try {

				prepareConsulta = connect.prepareStatement(consulta);
				result = prepareConsulta.executeQuery();
				Integer maximo = 0;
				while (result.next()) {
					maximo = result.getInt("maximo");
				}

				prepareConsulta2 = connect.prepareStatement(consulta2);
				result = prepareConsulta2.executeQuery();
				Integer registros = 0;
				while (result.next()) {
					registros = result.getInt("registros");
				}
				if (preferencia == (maximo + 1) && registros == 0) {
					preparePeticiones = connect.prepareStatement(sql);
					preparePeticiones.executeUpdate();
					Alert al = new Alert(AlertType.CONFIRMATION);
					al.setTitle("Añadir petición");
					al.setHeaderText(null);
					al.setContentText("Se añadió la petición correctamente.");
					al.showAndWait();

					mostrarListaPeticiones();
				} else {
					Alert al = new Alert(AlertType.ERROR);
					al.setTitle("Error al añadir petición");
					al.setHeaderText(null);
					al.setContentText(
							"Compruebe bien los datos. Hay ya un registro con esos datos o la preferencia asignada no es valida. Recuerda que el valor de la preferencia debe ser el siguiente a la última preferencia que se encuentra");
					al.showAndWait();
				}

			} catch (Exception e) {

				Alert al = new Alert(AlertType.ERROR);
				al.setTitle("Error en la insercción de datos");
				al.setHeaderText(null);
				al.setContentText("Ocurrió un error en el proceso de insercción.");
				al.showAndWait();

			}

		} else {
			Alert al = new Alert(AlertType.ERROR);
			al.setTitle("Error en la insercción de datos");
			al.setHeaderText(null);
			al.setContentText("Introduce todos los datos");
			al.showAndWait();
		}

	}

	public void añadirAsignaturas() {

		String asignaturaID = txtAsignaturaID.getText();
		String asignatura = txtAsignatura.getText();
		String titulacion = txtTitulacion.getText();
		String act = txtAct.getText();
		String grupo = txtGrupo.getText();
		String cuatrimestre = txtCuatrimestre.getText();
		String horario = txtHorario.getText();
		String creditos = txtCreditos.getText();
		if (!asignaturaID.isEmpty() && !asignatura.isEmpty() && !titulacion.isEmpty() && !act.isEmpty()
				&& !grupo.isEmpty() && !cuatrimestre.isEmpty() && !horario.isEmpty() && !creditos.isEmpty()) {
			String sql = "INSERT INTO asignaturas "
					+ "(id_asignatura, nombre_asignatura, nombre_titulacion, tipo_clase,grupo, cuatrimestre, creditos, horario) "
					+ "VALUES(" + asignaturaID + ",'" + asignatura + "','" + titulacion + "','" + act + "'," + grupo
					+ "," + cuatrimestre + "," + creditos + ",'" + horario + "')";

			DataBase db = new DataBase();
			connect = db.connectDB();

			PreparedStatement prepareAsignaturas = null;

			try {

				prepareAsignaturas = connect.prepareStatement(sql);
				prepareAsignaturas.executeUpdate();

				mostrarListaAsignaturas();

			} catch (Exception e) {

				Alert al = new Alert(AlertType.ERROR);
				al.setTitle("Error en la insercción de datos");
				al.setHeaderText(null);
				al.setContentText("Ocurrió un error en el proceso de insercción.");
				al.showAndWait();

			}
			limpiarAsignatura();
		} else {
			Alert al = new Alert(AlertType.ERROR);
			al.setTitle("Error en la insercción de datos");
			al.setHeaderText(null);
			al.setContentText("Introduce todos los datos");
			al.showAndWait();
		}

	}

	public void filtroBusquedaAsignacion() {

		ObservableList<AsignacionDB> filter = listaAsignaciones;
		String datoFiltro = buscarAsignacion.getText();
		ObservableList<AsignacionDB> listaFiltrada = FXCollections.observableArrayList();

		for (int i = 0; i < filter.size(); i++) {

			AsignacionDB asignacion = filter.get(i);

			if (asignacion.getId_profesor().toString().contains(datoFiltro)) {
				listaFiltrada.add(asignacion);
			} else if (asignacion.getNombre_asignatura().toLowerCase().contains(datoFiltro)) {
				listaFiltrada.add(asignacion);
			} else if (asignacion.getNombre_titulacion().toLowerCase().contains(datoFiltro)) {
				listaFiltrada.add(asignacion);
			} else if (asignacion.getCuatrimestre().toString().contains(datoFiltro)) {
				listaFiltrada.add(asignacion);
			} else if (asignacion.getGrupo().toString().contains(datoFiltro)) {
				listaFiltrada.add(asignacion);
			} else if (asignacion.getHorario().toLowerCase().contains(datoFiltro)) {
				listaFiltrada.add(asignacion);
			} else if (asignacion.getCreditos().toString().contains(datoFiltro)) {
				listaFiltrada.add(asignacion);
			}
		}

		SortedList<AsignacionDB> sortList = new SortedList<>(listaFiltrada);

		sortList.comparatorProperty().bind(tablaVista.comparatorProperty());
		tablaVista.setItems(sortList);
	}

	public void filtroBusquedaProfesor() {

		ObservableList<ProfesorDB> filter = listaProfesores;
		String datoFiltro = labBuscarProfesor.getText();
		ObservableList<ProfesorDB> listaFiltrada = FXCollections.observableArrayList();

		for (int i = 0; i < filter.size(); i++) {

			ProfesorDB profesor = filter.get(i);

			if (profesor.getId_profesor().toString().contains(datoFiltro)) {
				listaFiltrada.add(profesor);
			} else if (profesor.getCapacidad_efectiva().toString().contains(datoFiltro)) {
				listaFiltrada.add(profesor);
			}

		}

		SortedList<ProfesorDB> sortList = new SortedList<>(listaFiltrada);

		sortList.comparatorProperty().bind(tablaProfesores.comparatorProperty());
		tablaProfesores.setItems(sortList);
	}

	public void filtroBusquedaAsignatura() {

		ObservableList<AsignaturaDB> filter = listaAsignaturas;
		String datoFiltro = labBuscarAsignatura.getText();
		ObservableList<AsignaturaDB> listaFiltrada = FXCollections.observableArrayList();

		for (int i = 0; i < filter.size(); i++) {

			AsignaturaDB asignatura = filter.get(i);

			if (asignatura.getNombre_asignatura().toLowerCase().contains(datoFiltro)) {
				listaFiltrada.add(asignatura);
			} else if (asignatura.getNombre_titulacion().toLowerCase().contains(datoFiltro)) {
				listaFiltrada.add(asignatura);
			}

		}

		SortedList<AsignaturaDB> sortList = new SortedList<>(listaFiltrada);

		sortList.comparatorProperty().bind(tablaAsignaturas.comparatorProperty());
		tablaAsignaturas.setItems(sortList);
	}

	public void cambiarLista(ActionEvent event) {
		if (event.getSource() == btnAsignaturas) {
			ventanaProfesores.setVisible(false);
			ventanaAsignaturas.setVisible(true);
			ventanaPeticiones.setVisible(false);

		} else if (event.getSource() == btnProfesores) {
			ventanaAsignaturas.setVisible(false);
			ventanaProfesores.setVisible(true);
			ventanaPeticiones.setVisible(false);
		} else if (event.getSource() == btnPeticiones) {
			ventanaProfesores.setVisible(false);
			ventanaAsignaturas.setVisible(false);
			ventanaPeticiones.setVisible(true);
		}
	}

	public void cambiarVentana(ActionEvent event) {
		SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(
				1, 20);
		if (event.getSource() == btnCargarDatos) {
			ventanaAsignaciones.setVisible(false);
			ventanaCarga.setVisible(true);
			ventanaModificaciones.setVisible(false);
			btnCargarDatos.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c)");
			btnAsignaciones.setStyle("-fx-background-color:transparent");
			btnModificarDatos.setStyle("-fx-background-color:transparent");

		} else if (event.getSource() == btnAsignaciones) {
			mostrarListaAsignaciones();
			ventanaAsignaciones.setVisible(true);
			ventanaCarga.setVisible(false);
			ventanaModificaciones.setVisible(false);
			btnCargarDatos.setStyle("-fx-background-color:transparent");
			btnAsignaciones.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c)");
			btnModificarDatos.setStyle("-fx-background-color:transparent");
		} else if (event.getSource() == btnModificarDatos) {
			mostrarListaProfesores();
			mostrarListaAsignaturas();
			mostrarListaPeticiones();
			obtenerProfesores();
			obtenerAsignaturas();
			spinnerPreferencia.setValueFactory(valueFactory);
			ventanaAsignaciones.setVisible(false);
			ventanaCarga.setVisible(false);
			ventanaModificaciones.setVisible(true);
			btnCargarDatos.setStyle("-fx-background-color:transparent");
			btnAsignaciones.setStyle("-fx-background-color:transparent");
			btnModificarDatos.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c)");
		}
	}

	// Event Listener on Button.onAction
	@FXML
	public void btnCargarDatos(ActionEvent event) {

	}

	// Event Listener on Button.onAction
	@FXML
	public void btnAsignaciones(ActionEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on Button.onAction
	@FXML
	public void btnModificarDatos(ActionEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on Button[#btnSeleccionarFichero].onAction
	@FXML
	public void seleccionarFichero(ActionEvent event) {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(new ExtensionFilter("Excel Files", "*.xlsx"));
		File f = fc.showOpenDialog(null);
		if (f != null) {
			labFichero.setText(f.getAbsolutePath());
		}
	}

	// Event Listener on Button[#btnDescargarPDF].onAction
	@FXML
	public void descargarPDF(ActionEvent event) throws IOException {
		FileChooser fc = new FileChooser();
		fc.setTitle("Guardar PDF");
		fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos PDF", "*.pdf"));

		File archivo = fc.showSaveDialog(btnDescargarPDF.getScene().getWindow());
		String destino = archivo.getAbsolutePath();
		GenerarPDF gpdf = new GenerarPDF();
		gpdf.generarPDF(tablaVista, destino);

	}

	public void ejecutarAlgoritmoAsignacion() {
		String sqlProfesores = "SELECT * FROM profesores";
		String sqlPeticiones = "SELECT PE.id_profesor, PE.preferencia, A.id_asignatura,A.nombre_asignatura,A.nombre_titulacion, A.tipo_clase, A.grupo, A.cuatrimestre, A.creditos, A.horario FROM peticiones PE INNER JOIN profesores P ON PE.id_profesor=P.id_profesor INNER JOIN asignaturas A ON PE.id_asignatura=A.id_asignatura";
		String sqlAsignaciones = "DELETE FROM asignaciones";

		DataBase db = new DataBase();
		connect = db.connectDB();

		List<Profesor> listaA_profesores = new ArrayList<>();
		Map<Integer, Map<Integer, Asignatura>> listaA_preferenciasProfesores = new HashMap<>();
		PreparedStatement prepareProfesores = null;
		PreparedStatement preparePeticiones = null;
		PreparedStatement prepareAsignaciones = null;

		try {

			prepareProfesores = connect.prepareStatement(sqlProfesores);
			result = prepareProfesores.executeQuery();
			while (result.next()) {
				listaA_profesores.add(new Profesor(result.getString("nombre_profesor"), result.getDouble("capacidad_efectiva"),
						result.getDouble("capacidad_efectiva")));
			}

			preparePeticiones = connect.prepareStatement(sqlPeticiones);
			result = preparePeticiones.executeQuery();
			while (result.next()) {
				Integer idProfesor = result.getInt("id_profesor");
				Integer preferencia = result.getInt("preferencia");
				Asignatura asignatura = new Asignatura(result.getInt("id_asignatura"),
						result.getString("nombre_titulacion"), result.getString("nombre_asignatura"),
						result.getString("tipo_clase"), result.getInt("grupo"), result.getInt("cuatrimestre"),
						result.getDouble("creditos"), result.getString("horario"));
				Map<Integer, Asignatura> asignaturaPreferencia = new HashMap<>();
				asignaturaPreferencia.put(preferencia, asignatura);
				if (listaA_preferenciasProfesores.containsKey(idProfesor)) {
					listaA_preferenciasProfesores.get(idProfesor).put(preferencia, asignatura);
				} else {
					listaA_preferenciasProfesores.put(idProfesor, asignaturaPreferencia);
				}
			}
			

			try {
				AsignacionAsignaturas algoritmoAsignacion = new AsignacionAsignaturas();
				algoritmoAsignacion.asignarAsignaturas(listaA_profesores, listaA_preferenciasProfesores);
				Map<Integer, Map<Integer, Asignatura>> listaAsignacion = algoritmoAsignacion.getAsignacion();

				// Eliminar datos de la tabla "asignaciones"
				prepareAsignaciones = connect.prepareStatement(sqlAsignaciones);
				prepareAsignaciones.executeUpdate();

				añadirAsignaciones(listaAsignacion);
				mostrarListaAsignaciones();
				Alert al = new Alert(AlertType.CONFIRMATION);
				al.setTitle("Ejecución del algoritmo");
				al.setHeaderText(null);
				al.setContentText("Algoritmo ejecutado con éxito. Ve a la ventana de asignaciones para comprobarlo.");
				al.showAndWait();

			} catch (Exception e) {
				System.out.println(e);
				Alert al = new Alert(AlertType.ERROR);
				al.setTitle("Error en la ejecución del algoritmo");
				al.setHeaderText(null);
				al.setContentText("Ocurrió un error en el proceso de asignación.");
				al.showAndWait();
			}

		} catch (Exception e) {
			System.out.println(e);
			Alert al = new Alert(AlertType.ERROR);
			al.setTitle("Error en la ejecución del algoritmo");
			al.setHeaderText(null);
			al.setContentText("Ocurrió un error en la lectura de datos.");
			al.showAndWait();

		}

	}

	// Event Listener on Button[#btnEjecutar].onAction
	@FXML
	public void ejecutarCargaDatos(ActionEvent event) {
		if (!labFichero.getText().isEmpty()) {
			ExtraerDatos ed = new ExtraerDatos();
			ed.extraerDatos(labFichero.getText());
			List<Asignatura> asignaturas = ed.getAsignaturas();
			profesores = ed.getProfesores();
			asignaciones = ed.getAsignacion();
			listaPreferenciasProfesores = ed.getPreferenciasProfesores();
			List<Double> excedentes = new ArrayList<>();

			for (int i = 0; i < asignaciones.size(); i++) {
				Double creditos = 0.0;
				Map<Integer, Asignatura> asignacionProfesor = asignaciones.get(i + 1);
				for (Asignatura asig : asignacionProfesor.values()) {
					creditos += asig.getCreditos();
				}

				excedentes.add(creditos);
			}
			eliminarDatos();
			try {

				añadirProfesores(profesores, excedentes);
				añadirAsignaturas(asignaturas);
				añadirAsignaciones(asignaciones);
				añadirPeticiones(listaPreferenciasProfesores);

				Alert al = new Alert(AlertType.CONFIRMATION);
				al.setTitle("Carga de datos");
				al.setHeaderText(null);
				al.setContentText("Datos cargados correctamente!");
				al.showAndWait();

			} catch (Exception e) {
				// TODO: handle exception
			}
			mostrarListaAsignaciones();

		} else {
			Alert al = new Alert(AlertType.ERROR);
			al.setTitle("Error en la carga de datos");
			al.setHeaderText(null);
			al.setContentText("Selecciona un fichero primero");
			al.showAndWait();
		}
	}
}
