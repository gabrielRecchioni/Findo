package ar.com.findo.notas.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;

import ar.com.findo.notas.model.Alumno;
import ar.com.findo.notas.model.Curso;
import ar.com.findo.notas.model.CursoActivo;

public interface CursoService {
	
	public Curso guardarCurso(Curso curso);
	
	public List<CursoActivo>  obtenerCursosActivos(LocalDate fecha);
	 
	public ResponseEntity<List<Alumno>> obtenerAlumnosMasJovenes(Long idCurso, int cantidad);
	
	public  ResponseEntity<Curso> anotarAlumno(Long idAlumno , Long idCurso);


	 
}
