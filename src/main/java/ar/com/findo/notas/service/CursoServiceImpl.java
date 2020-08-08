package ar.com.findo.notas.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ar.com.findo.notas.model.Alumno;
import ar.com.findo.notas.model.Curso;
import ar.com.findo.notas.model.CursoActivo;
import ar.com.findo.notas.repository.AlumnoRepository;
import ar.com.findo.notas.repository.CursoRepository;

@Service
public class CursoServiceImpl implements CursoService {

	@Autowired
	CursoRepository cursoRepo;
	@Autowired
	AlumnoRepository alumnoRepo;

	@Override
	public Curso guardarCurso(Curso curso) {
		return cursoRepo.save(curso);
	}

	@Override
	public List<CursoActivo> obtenerCursosActivos(LocalDate fecha) {
		LocalDate hoy = LocalDate.now();
		// cursos activos entre fecha
		List<Curso> cursos = cursoRepo.obtenerEntreFechas(fecha);

		List<CursoActivo> activos = new ArrayList<>();
		/*
		 * 
		 * recorro los cursos utilizo el for each de esta manera para hacer un poco mas
		 * legible el codigo
		 */

		for (Curso c : cursos) {
			List<Integer> edades = new ArrayList<>();
			List<LocalDate> fechasN = new ArrayList<>();
			CursoActivo ca = new CursoActivo();
			// obtengo los alumnos del curso
			List<Alumno> alumnos = c.getAlumnos();

			if (!alumnos.isEmpty()) {

				ca.setCantidadAlumnos(Math.toIntExact((alumnos.stream().count())));

				fechasN = alumnos.stream().map(alumno -> alumno.getFechaNacimiento()).collect(Collectors.toList());
				/*
				 * recorro las lista de fechas de nacimiento de los alumnos para poder obrener
				 * mediante el metodo between de period la edad de cada uno
				 */
				for (LocalDate f : fechasN) {
					edades.add(Period.between(f, hoy).getYears());

				}
				/*
				 * recorro la lista de edades y obtengo el promedio
				 */
				Double prom = (edades.stream().mapToDouble(val -> val).average().getAsDouble());
				ca.setEdadPromedioAlumnos(prom.intValue());

			} else {
				ca.setCantidadAlumnos(0);
				ca.setEdadPromedioAlumnos(0);
			}
			ca.setHorasSemanalesTotales(c.getHorasSemanales());
			activos.add(ca);
//	

		}
		return activos;
	}

	@Override
	public ResponseEntity<List<Alumno>> obtenerAlumnosMasJovenes(Long idCurso, int cantidad) {
		// obtengo el curso
		Optional<Curso> c = cursoRepo.findById(idCurso);
		if (!c.isPresent() || c.get().getAlumnos().isEmpty()) {
			return new ResponseEntity<List<Alumno>>(HttpStatus.NOT_FOUND);
		}

		List<Alumno> alumnos = c.get().getAlumnos();
		// ordeno la lista de alumnos por edad
		alumnos.sort(Comparator.comparing(Alumno::getFechaNacimiento).reversed());

		if (alumnos.size() < cantidad)
			return new ResponseEntity<List<Alumno>>(alumnos, HttpStatus.OK);
		else
			return new ResponseEntity<List<Alumno>>(alumnos.subList(0, cantidad), HttpStatus.OK);

	}

	@Override
	public ResponseEntity<Curso> anotarAlumno(Long idAlumno, Long idCurso) {
		Optional<Curso> cur = cursoRepo.findById(idCurso);
		Optional<Alumno> alum = alumnoRepo.findById(idAlumno);

		List<Integer> horasSemanales = new ArrayList<>();
		// Chequeo si el curso existe y si el alumno existe
		if (!cur.isPresent() || !alum.isPresent()) {
			return new ResponseEntity<Curso>(HttpStatus.NOT_FOUND);

		} else {
			Curso c = cur.get();
			Alumno alumno = alum.get();
			if (!alumno.getCursando().isEmpty()) {
				// lista de los cursos donde esta anotado el alumno
				List<Curso> cursos = alumno.getCursando();
				// recorro la lista de cursos y obtengo una lista con las horas
				horasSemanales = cursos.stream().map(x -> x.getHorasSemanales()).collect(Collectors.toList());
			}
			if (horasSemanales.stream().mapToInt(val -> val).sum() + c.getHorasSemanales() <= 20) {
				// Agrego el alumno al curso
				c.getAlumnos().add(alumno);
				// cursoRepo.save gracias a jpaRepository funciona tambien como un update
				return new ResponseEntity<Curso>(cursoRepo.save(c), HttpStatus.OK);

			} else {
				return new ResponseEntity<Curso>(HttpStatus.BAD_REQUEST);
			}
		}

	}

}
