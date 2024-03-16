package Modelo;

public class Vehiculo implements Comparable <Vehiculo> {
	
	//atributos
	
	private String tipo, placa,ubicacion,horaEntrada,horaSalida;
	private int costoHora,codigo;
	
	
	
	//to String
	
	@Override
	public String toString() {
		return "Vehiculo [tipo=" + tipo + " placa=" + placa + ", ubicacion=" + ubicacion + ","+ "horaEntrada="
				+ horaEntrada + ", horaSalida=" + horaSalida + ", codigo=" + codigo + ", costoHora=" + costoHora + "]";
	}
	
	//constructores
	
	public Vehiculo(String tipo, String placa, String ubicacion, String horaEntrada, String horaSalida, int codigo,
			int costoHora) {
		super();
		this.tipo= tipo;
		this.placa = placa;
		this.ubicacion = ubicacion;
		this.horaEntrada = horaEntrada;
		this.horaSalida = horaSalida;
		this.codigo = codigo;
		this.costoHora = costoHora;
	}

	public Vehiculo(String tipo, String ubicacion, String horaEntrada, String horaSalida, int codigo, int costoHora) {
		super();
		this.tipo= tipo;
		this.ubicacion = ubicacion;
		this.horaEntrada = horaEntrada;
		this.horaSalida = horaSalida;
		this.codigo = codigo;
		this.costoHora = costoHora;
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
