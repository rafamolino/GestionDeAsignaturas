package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hpsf.Array;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import datos.AsignacionAsignaturas;
import objetos.Asignatura;
import objetos.Profesor;

public class ProyectoTest {
	
	@Test
	public void testCompatibilidadHoraria1() {
		AsignacionAsignaturas asignacionAsignaturas = new AsignacionAsignaturas();
		Asignatura asignatura1 = new Asignatura(1, "Ingenieria del Software", "Fundamentos de programacion", "A", 1, 1, 3.6, "L. - 10:40");
		Asignatura asignatura2 = new Asignatura(2, "Ingenieria del Software", "Sistemas Operativos", "A", 1, 1, 3.6, "L. - 10:40");
		Boolean resultado = asignacionAsignaturas.esCompatibleHorario(asignatura1, asignatura2);
		Assertions.assertFalse(resultado);		
	}
	@Test
	public void testCompatibilidadHoraria2() {
		AsignacionAsignaturas asignacionAsignaturas = new AsignacionAsignaturas();
		Asignatura asignatura1 = new Asignatura(1, "Ingenieria del Software", "Fundamentos de programacion", "A", 1, 1, 3.6, "L. - 10:40");
		Asignatura asignatura2 = new Asignatura(2, "Ingenieria del Software", "Sistemas Operativos", "A", 1, 2, 3.6, "L. - 10:40");
		Boolean resultado = asignacionAsignaturas.esCompatibleHorario(asignatura1, asignatura2);
		Assertions.assertTrue(resultado);		
	}
	@Test
	public void testCompatibilidadHoraria3() {
		AsignacionAsignaturas asignacionAsignaturas = new AsignacionAsignaturas();
		Asignatura asignatura1 = new Asignatura(1, "Ingenieria del Software", "Fundamentos de programacion", "A", 1, 1, 3.6, "L. - 17:40");
		Asignatura asignatura2 = new Asignatura(2, "Ingenieria del Software", "Sistemas Operativos", "A", 1, 1, 3.6, "L,M,X,J");
		Boolean resultado = asignacionAsignaturas.esCompatibleHorario(asignatura1, asignatura2);
		Assertions.assertFalse(resultado);		
	}
	@Test
	public void testCompatibilidadHoraria4() {
		AsignacionAsignaturas asignacionAsignaturas = new AsignacionAsignaturas();
		Asignatura asignatura1 = new Asignatura(1, "Ingenieria del Software", "Fundamentos de programacion", "A", 1, 1, 3.6, "L. - 10:40");
		Asignatura asignatura2 = new Asignatura(2, "Ingenieria del Software", "Sistemas Operativos", "A", 1, 1, 3.6, "L,M,X,J");
		Boolean resultado = asignacionAsignaturas.esCompatibleHorario(asignatura1, asignatura2);
		Assertions.assertTrue(resultado);		
	}
	@Test
	public void testCompatibilidadHoraria5() {
		AsignacionAsignaturas asignacionAsignaturas = new AsignacionAsignaturas();
		Asignatura asignatura1 = new Asignatura(1, "Ingenieria del Software", "Fundamentos de programacion", "A", 1, 1, 3.6, "X,J");
		Asignatura asignatura2 = new Asignatura(2, "Ingenieria del Software", "Sistemas Operativos", "A", 1, 1, 3.6, "L,M,X,J");
		Boolean resultado = asignacionAsignaturas.esCompatibleHorario(asignatura1, asignatura2);
		Assertions.assertFalse(resultado);		
	}
	@Test
	public void testCompatibilidadHoraria6() {
		AsignacionAsignaturas asignacionAsignaturas = new AsignacionAsignaturas();
		Asignatura asignatura1 = new Asignatura(1, "Ingenieria del Software", "Fundamentos de programacion", "A", 1, 1, 3.6, "X,J");
		Asignatura asignatura2 = new Asignatura(2, "Ingenieria del Software", "Sistemas Operativos", "A", 1, 1, 3.6, "L,M");
		Boolean resultado = asignacionAsignaturas.esCompatibleHorario(asignatura1, asignatura2);
		Assertions.assertTrue(resultado);		
	}
	//Comprobación de no asignación de asignatura por estar asignada a otro profesor
	@Test
	public void testAsignacionPrevia() {
		AsignacionAsignaturas asignacionAsignaturas = new AsignacionAsignaturas();
		Profesor profesor1 = new Profesor("Rafael Molino Alvarez", 8.00, 8.00);
		Profesor profesor2 = new Profesor("Javier Molino Alvarez", 1.00, 1.00); 
		Asignatura asignatura1 = new Asignatura(1, "Ingenieria del Software", "Fundamentos de programacion", "A", 1, 1, 3.6, "L. - 10:40");
		Asignatura asignatura2 = new Asignatura(2, "Ingenieria del Software", "Sistemas Operativos", "A", 1, 2, 3.6, "J. - 17:40");
		Asignatura asignatura3 = new Asignatura(3, "Ingenieria del Software", "Analisis de Datos y Algoritmos", "A", 1, 2, 3.6, "M. - 17:40");
		List<Profesor> profesores = new ArrayList<>();
		profesores.add(profesor1);
		profesores.add(profesor2);
		Map<Integer, Map<Integer, Asignatura>> preferenciasProfesores = new HashMap<>();
		Map<Integer, Asignatura> preferencias1 = new HashMap<>();
		Map<Integer, Asignatura> preferencias2 = new HashMap<>();

		preferencias1.put(1, asignatura1);
		preferencias1.put(2, asignatura2);
		preferencias2.put(1, asignatura1);
		preferencias2.put(2, asignatura2);
		preferencias2.put(3, asignatura3);
		preferenciasProfesores.put(1, preferencias1);
		preferenciasProfesores.put(2, preferencias2);
		asignacionAsignaturas.asignarAsignaturas(profesores, preferenciasProfesores);
		Map<Integer, Map<Integer, Asignatura>> datos = asignacionAsignaturas.getAsignacion();
		System.out.println(datos.get(1));
		Boolean resultado = datos.get(2).containsValue(asignatura1) && datos.get(2).containsValue(asignatura2);
		Assertions.assertFalse(resultado);
	}
	//Comprobación de no asignación de asignatura por falta de créditos
	@Test
	public void testCreditosDisponibles() {
		AsignacionAsignaturas asignacionAsignaturas = new AsignacionAsignaturas();
		Profesor profesor1 = new Profesor("Rafael Molino Alvarez", 4.00, 4.00);
		Asignatura asignatura1 = new Asignatura(1, "Ingenieria del Software", "Fundamentos de programacion", "A", 1, 1, 3.6, "L. - 10:40");
		Asignatura asignatura2 = new Asignatura(2, "Ingenieria del Software", "Sistemas Operativos", "A", 1, 2, 3.6, "J. - 17:40");
		List<Profesor> profesores = new ArrayList<>();
		profesores.add(profesor1);
		Map<Integer, Map<Integer, Asignatura>> preferenciasProfesores = new HashMap<>();
		Map<Integer, Asignatura> preferencias1 = new HashMap<>();
		preferencias1.put(1, asignatura1);
		preferencias1.put(2, asignatura2);
		preferenciasProfesores.put(1, preferencias1);
		asignacionAsignaturas.asignarAsignaturas(profesores, preferenciasProfesores);
		Map<Integer, Map<Integer, Asignatura>> datos = asignacionAsignaturas.getAsignacion();
		System.out.println(datos.get(1));
		Boolean resultado = datos.get(1).containsValue(asignatura2);
		Assertions.assertFalse(resultado);
	}
	
	


}
