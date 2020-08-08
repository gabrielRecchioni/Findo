package ar.com.findo.notas.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name = "alumnos")
public class Alumno {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id_alumnos")
	Long id;
	String nombre ;
	String apellido;
	String libreta;
	@JsonFormat(pattern="dd-MM-yyyy")
	LocalDate fechaNacimiento;

	@ManyToMany(mappedBy="alumnos",cascade = {CascadeType.MERGE,CascadeType.PERSIST})
	 @JsonIgnoreProperties("alumnos")
	List<Curso> cursando ;

	
	public List<Curso> getCursando() {
		return cursando;
	}



	public void setCursando(List<Curso> cursando) {
		this.cursando = cursando;
	}



	public Long getId() {
		return id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getApellido() {
		return apellido;
	}



	public void setApellido(String apellido) {
		this.apellido = apellido;
	}



	public String getLibreta() {
		return libreta;
	}



	public void setLibreta(String libreta) {
		this.libreta = libreta;
	}



	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}



	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}



}
