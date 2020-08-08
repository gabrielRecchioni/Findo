package ar.com.findo.notas.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.findo.notas.model.Alumno;
import ar.com.findo.notas.model.Curso;
import ar.com.findo.notas.model.CursoActivo;
import ar.com.findo.notas.repository.AlumnoRepository;
import ar.com.findo.notas.repository.CursoRepository;
import ar.com.findo.notas.service.AlumnoService;
import ar.com.findo.notas.service.CursoService;

@RestController
@RequestMapping("/api/v1")
public class NotasController {
	/*
	 * voy a utilizar un solo controlador para llamar a las 2 capas de servicios en
	 * las cuales, cada una va a tener un repository propio para mantener los
	 * metodos separados, como por ejemplo los de guardar un alumno nuevo o guardar
	 * un Curso nuevo .
	 * 
	 */
	@Autowired
	CursoService cursoService;
	@Autowired
	AlumnoService alumnoService;

	@PostMapping("/alumnos")
	@CrossOrigin
	public Alumno crearAlumno(@RequestBody Alumno alumno) {
		return alumnoService.guardarAlumno(alumno);
	}

	@PostMapping("/cursos")
	@CrossOrigin
	public Curso crearCurso(@RequestBody Curso curso) {
		return cursoService.guardarCurso(curso);
	}

	@GetMapping("/alumnos-curso")
	@CrossOrigin
	public ResponseEntity<List<Alumno>> obtenerAlumnosMasJovenes(@RequestParam Long idCurso,
			@RequestParam int cantidad) {

		return cursoService.obtenerAlumnosMasJovenes(idCurso, cantidad);
	}

	@PostMapping("/inscribir")
	@CrossOrigin
	public ResponseEntity<Curso> inscribirAlumno(@RequestParam Long idAlumno, @RequestParam Long idCurso) {
		return cursoService.anotarAlumno(idAlumno, idCurso);
	}

	@GetMapping("/cursos-activos")
	@CrossOrigin
	public List<CursoActivo> obtenerCursosActivos(
			@RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate fecha) {
		return cursoService.obtenerCursosActivos(fecha);
	}

}
