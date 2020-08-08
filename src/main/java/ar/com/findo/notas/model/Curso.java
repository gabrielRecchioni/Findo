package ar.com.findo.notas.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name = "cursos")
public class Curso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id_cursos")
	Long id;
	
	String nombre;
	
	@JsonFormat(pattern="dd-MM-yyyy")
	LocalDate fechaInicio;
	
	@JsonFormat(pattern="dd-MM-yyyy")
	LocalDate fechaFin;

	int horasSemanales;
	/* 
	 * Como la relacion entre alumnos y cursos va a ser, que un alumno puede anotarse en muchos cursos, y el cada curso pueden anotarse muchos alumnos 
	elijo usar la anotacion Many to many para utilizar lo automatico de hibernate a la hora de crear la tabla intermedia  entre los cursos y
	los alumnos, para evitar mappear por mi cuenta esa tabla extra..  
	 */
	 @ManyToMany(targetEntity = Alumno.class,fetch = FetchType.EAGER,cascade =CascadeType.REFRESH)
	    @JoinTable(name = "alumnos_curso", joinColumns = { @JoinColumn(name = "id_cursos") }, 
	                       inverseJoinColumns = { @JoinColumn(name = "id_alumnos") })
		 @JsonIgnoreProperties("cursos")
	 
	List<Alumno> alumnos;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public LocalDate getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}
	public int getHorasSemanales() {
		return horasSemanales;
	}
	public void setHorasSemanales(int horasSemanales) {
		this.horasSemanales = horasSemanales;
	}
	public List<Alumno> getAlumnos() {
		return alumnos;
	}
	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}
	public Long getId() {
		return id;
	}
	




}
