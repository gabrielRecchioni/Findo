package ar.com.findo.notas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.findo.notas.model.Alumno;
import ar.com.findo.notas.model.Curso;
import ar.com.findo.notas.repository.AlumnoRepository;

@Service
public class AlumnoServiceImpl implements AlumnoService{
	
	@Autowired
	
	AlumnoRepository alumnoRepo;
	
	
	@Override
	public Alumno guardarAlumno(Alumno alumno) {
		return alumnoRepo.save(alumno);
	}
	
	
	
	
	
	
	
}
