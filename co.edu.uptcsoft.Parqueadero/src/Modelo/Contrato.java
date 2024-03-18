package Modelo;

public class Contrato implements Comparable <Contrato> {
	
	//Atributos
	
	private String placa, identificacion, titular, tipoVehiculo, firma, mes;
	private int costo;


	//constructores

	


	public Contrato(String placa, String identificacion, String titular, String tipoVehiculo, int costo,String firma, String mes) {
		super();
		this.placa = placa;
		this.identificacion = identificacion;
		this.titular = titular;
		this.tipoVehiculo = tipoVehiculo;
		this.costo = costo;
		this.firma=firma;
		this.mes= mes;
	}

	//to String

	@Override
	public String toString() {
		return "Contrato [placa= " + placa + ", identificacion= " + identificacion + ", titular= " + titular
				+ ", tipoVehiculo= " + tipoVehiculo + ", costo= " + costo + ",firma: "+firma+"mes de inicio: "+mes+ "]";
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	//getters and setters
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	public String getTitular() {
		return titular;
	}
	public void setTitular(String titular) {
		this.titular = titular;
	}
	public String getTipoVehiculo() {
		return tipoVehiculo;
	}
	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}
	public int getCosto() {
		return costo;
	}
	public void setCosto(int costo) {
		this.costo = costo;
	}

	//compareTo
	@Override
	public int compareTo(Contrato o) {
		return this.getPlaca().compareTo(o.getPlaca());
	}
}
