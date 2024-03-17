package Logica;

import java.time.LocalTime;
import java.util.LinkedHashSet;

import Modelo.Contrato;
import Modelo.Usuario;
import Modelo.Vehiculo;

public class Sistema {
	
	public int retirarVehiculoPlaca (LinkedHashSet<Vehiculo> vehiculos, LinkedHashSet<Contrato> contratos, String placa, int costoMinuto) {
		int precio=0;
		long tiempo1, tiempo2;
		for (Vehiculo vehiculo: vehiculos) {
			if (vehiculo.getPlaca().compareTo(placa)==0) {
				LocalTime horaSalida = LocalTime.now();
				vehiculo.setHoraSalida(horaSalida);
				tiempo1 = vehiculo.getHoraEntrada().getHour()*60 + vehiculo.getHoraEntrada().getMinute();
				tiempo2= horaSalida.getHour() *60 + horaSalida.getMinute();
				precio = (int) ((tiempo2-tiempo1)*costoMinuto);
				vehiculo.setCosto(precio);
			}
		} 
			
		
		return precio;
	}
	public int retirarVehiculoCodigo (LinkedHashSet<Vehiculo> vehiculos, LinkedHashSet<Contrato> contratos, String placa, int costoMinuto) {
		int precio=0;
		long tiempo1, tiempo2;
		for (Vehiculo vehiculo: vehiculos) {
			if (vehiculo.getPlaca().compareTo(placa)==0) {
				LocalTime horaSalida = LocalTime.now();
				vehiculo.setHoraSalida(horaSalida);
				tiempo1 = vehiculo.getHoraEntrada().getHour()*60 + vehiculo.getHoraEntrada().getMinute();
				tiempo2= horaSalida.getHour() *60 + horaSalida.getMinute();
				precio = (int) ((tiempo2-tiempo1)*costoMinuto);
				vehiculo.setCosto(precio);
			}
		}
		return precio;
	}
	
 public boolean verificarUsuario (Usuario jefe, String nombre, int contraseña) {
	 return jefe.getUsuario().compareTo(nombre)== 0 && jefe.getContraseña() ==contraseña;
 }
}
