package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Promocion extends Propuesta{
	
	private Integer id;
	private Integer tipoPromocion;
	private Integer tipoAtracciones;
	private String nombre;
	private String descripcion;
	private Double variable;
	protected LinkedList<Attraction> atraccionesIncluidas = new LinkedList<Attraction>();
	
	//borrar
	private Integer capacity = 10;
	
	private Map<String, String> errors;
	
	public Promocion(Integer id, Integer tipoPromocion, Integer tipoAtracciones, String nombre, String descripcion, Double variable) {
		super();
		this.id = id;
		this.tipoPromocion = tipoPromocion;
		this.tipoAtracciones = tipoAtracciones;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.variable = variable;
	}
	public Double getVariable() {
		return variable;
	}
	public void setVariable(Double variable) {
		this.variable = variable;
	}
	@Override
	public Integer getCost(){
		return 0;
	}
	
	@Override
	public Double getDuration(){
		return 0.0;
	}
	
	@Override
	public Integer getCapacity(){
		return 1;
	}
	
	public boolean isValid() {
		validate();
		return errors.isEmpty();
	}
	
	public void validate() {
		errors = new HashMap<String, String>();

		if (tipoPromocion <= 0) {
			errors.put("tipoPromocion", "Debe ser positivo");
		}
		if (tipoAtracciones <= 0) {
			errors.put("tipoAtracciones", "Debe ser positivo");
		}
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTipoPromocion() {
		return tipoPromocion;
	}

	public void setTipoPromocion(Integer tipoPromocion) {
		this.tipoPromocion = tipoPromocion;
	}

	public Integer getTipoAtracciones() {
		return tipoAtracciones;
	}

	public void setTipoAtracciones(Integer tipoAtracciones) {
		this.tipoAtracciones = tipoAtracciones;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	@Override
	public String toString() {
		return "Promocion [id=" + id + ", tipoPromocion=" + tipoPromocion + ", tipoAtracciones=" + tipoAtracciones
				+ ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}
	
	public boolean canHost(int i) {
		return getCapacity() >= i;
	}

	public void host(int i) {
		this.capacity -= i;
	}

}
