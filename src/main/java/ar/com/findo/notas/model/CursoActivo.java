package ar.com.findo.notas.model;
/*
 * Clase utilizada para la devolucion del metodo de los cursos activos 
 */
public class CursoActivo {
	
	private int horasSemanalesTotales;
	private int cantidadAlumnos;
	private int edadPromedioAlumnos;
	
	
	public int getHorasSemanalesTotales() {
		return horasSemanalesTotales;
	}
	public void setHorasSemanalesTotales(int horasSemanalesTotales) {
		this.horasSemanalesTotales = horasSemanalesTotales;
	}
	public int getCantidadAlumnos() {
		return cantidadAlumnos;
	}
	public void setCantidadAlumnos(int cantidadAlumnos) {
		this.cantidadAlumnos = cantidadAlumnos;
	}
	public int getEdadPromedioAlumnos() {
		return edadPromedioAlumnos;
	}
	public void setEdadPromedioAlumnos(int edadPromedioAlumnos) {
		this.edadPromedioAlumnos = edadPromedioAlumnos;
	}
	
	

}
