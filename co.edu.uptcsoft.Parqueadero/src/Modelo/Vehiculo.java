package Modelo;

import java.time.LocalTime;

public class Vehiculo implements Comparable<Vehiculo> {
	
	//atributos
	private String tipo, placa,ubicacion;
	private int costo,codigo;
	LocalTime horaEntrada,horaSalida;
	
	
	
	
	public Vehiculo(String tipo, String placa, String ubicacion, LocalTime horaEntrada, LocalTime horaSalida, int codigo,
			int costo) {
		super();
		this.tipo= tipo;
		this.placa = placa;
		this.ubicacion = ubicacion;
		this.horaEntrada = horaEntrada;
		this.horaSalida = horaSalida;
		this.codigo = codigo;
		this.costo = costo;
	}

	public Vehiculo(String tipo, String ubicacion, LocalTime horaEntrada, LocalTime horaSalida, int codigo, int costo) {
		super();
		this.tipo= tipo;
		this.ubicacion = ubicacion;
		this.horaEntrada = horaEntrada;
		this.horaSalida = horaSalida;
		this.codigo = codigo;
		this.costo = costo;
		}

	//getters and setters
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public LocalTime getHoraEntrada() {
		return horaEntrada;
	}
	public void setHoraEntrada(LocalTime horaEntrada) {
		this.horaEntrada = horaEntrada;
	}
	public LocalTime getHoraSalida() {
		return horaSalida;
	}
	public void setHoraSalida(LocalTime horaSalida) {
		this.horaSalida = horaSalida;
	}
	public int getCosto() {
		return costo;
	}
	public void setCosto(int costo) {
		this.costo = costo;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	//CompareTo
	public int compareTo(Vehiculo o) {		
		return this.getPlaca().compareTo(o.getPlaca());
	}	
}
