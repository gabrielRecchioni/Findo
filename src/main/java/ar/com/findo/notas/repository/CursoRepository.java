package ar.com.findo.notas.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ar.com.findo.notas.model.Curso;
@Repository
public interface CursoRepository extends JpaRepository<Curso,Long> {
	
	
	/*
	 * Query para obtener los cursos que se encuentren activos dependiendo de la fecha ingresada  
	 * 
	 */
	
	@Query(value = "from Curso c where :fecha BETWEEN c.fechaInicio AND c.fechaFin")
	List<Curso> obtenerEntreFechas(@Param(value = "fecha") LocalDate fecha);
	
	
	
	
}
