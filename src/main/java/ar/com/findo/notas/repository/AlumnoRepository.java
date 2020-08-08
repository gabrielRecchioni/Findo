package ar.com.findo.notas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.findo.notas.model.Alumno;
@Repository
public interface AlumnoRepository  extends JpaRepository<Alumno, Long>{

}
