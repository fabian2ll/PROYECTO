package Modelo;

public class Vehiculo implements Comparable <Vehiculo> {
	
	//atributos
	
	private String placa,ubicacion,tiempo,horaEntrada,horaSalida,codigo;
	private int costoHora;
	
	
	
	//to String
	
	@Override
	public String toString() {
		return "Vehiculo [placa=" + placa + ", ubicacion=" + ubicacion + ", tiempo=" + tiempo + ", horaEntrada="
				+ horaEntrada + ", horaSalida=" + horaSalida + ", codigo=" + codigo + ", costoHora=" + costoHora + "]";
	}
	
	//constructores
	

	public Vehiculo(String placa, String ubicacion, String tiempo, String horaEntrada, String horaSalida,
			int costoHora) {
		super();
		this.placa = placa;
		this.ubicacion = ubicacion;
		this.tiempo = tiempo;
		this.horaEntrada = horaEntrada;
		this.horaSalida = horaSalida;
		this.costoHora = costoHora;
	}
	
	

	public Vehiculo(String placa, String ubicacion, String horaEntrada, int costoHora) {
		super();
		this.placa = placa;
		this.ubicacion = ubicacion;
		this.horaEntrada = horaEntrada;
		this.costoHora = costoHora;
	}
	
	public Vehiculo(String ubicacion, String tiempo, String horaEntrada, String horaSalida, int costoHora) {
		super();
		this.ubicacion = ubicacion;
		this.tiempo = tiempo;
		this.horaEntrada = horaEntrada;
		this.horaSalida = horaSalida;
		this.costoHora = costoHora;
	}
	
	public Vehiculo() { }

	
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
	public String getTiempo() {
		return tiempo;
	}
	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}
	public String getHoraEntrada() {
		return horaEntrada;
	}
	public void setHoraEntrada(String horaEntrada) {
		this.horaEntrada = horaEntrada;
	}
	public String getHoraSalida() {
		return horaSalida;
	}
	public void setHoraSalida(String horaSalida) {
		this.horaSalida = horaSalida;
	}
	public int getCostoHora() {
		return costoHora;
	}
	public void setCostoHora(int costoHora) {
		this.costoHora = costoHora;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	//CompareTo
	public int compareTo(Vehiculo o) {
		
		return this.getPlaca().compareTo(o.getPlaca());
	}



	
	

	
}
