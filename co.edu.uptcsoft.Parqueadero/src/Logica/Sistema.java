package Logica;

import java.time.LocalTime;
import java.util.LinkedHashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Modelo.Contrato;
import Modelo.Usuario;
import Modelo.Vehiculo;

public class Sistema {
	int posicion;
	String tipo;

	public int retirarVehiculoPlaca (LinkedHashSet <Vehiculo> vehiculos ,LinkedHashSet<Vehiculo> vehiculosTotales, LinkedHashSet<Contrato> contratos, String placa, int costoMinuto) {
		int precio=0;
		long tiempo1, tiempo2;
	
		for (Vehiculo vehiculo: vehiculosTotales) {
			if (placa.compareTo(vehiculo.getPlaca())==0) {
				LocalTime horaSalida = LocalTime.now();
				vehiculo.setHoraSalida(horaSalida);
				tiempo1 = vehiculo.getHoraEntrada().getHour()*60 + vehiculo.getHoraEntrada().getMinute();
				tiempo2= horaSalida.getHour() *60 + horaSalida.getMinute();
				precio = (int) ((tiempo2-tiempo1)*costoMinuto);
				vehiculo.setCosto(precio);
				posicion = Integer.parseInt(vehiculo.getUbicacion().substring(1));
				tipo = vehiculo.getTipo();
				vehiculos.remove(vehiculo);
			} 
			for (Contrato contrato: contratos) {
				if (contrato.getPlaca().compareTo(placa)==0 ) {
					precio=0;
					vehiculo.setCosto(precio);
				}
						
			
				}
		
		}
		
		return precio;
	}
	public int retirarVehiculoCodigo (LinkedHashSet <Vehiculo> vehiculos, LinkedHashSet<Vehiculo> vehiculosTotales, LinkedHashSet<Contrato> contratos, int codigo, int costoMinuto) {
		int precio=0;
		long tiempo1, tiempo2;
		for (Vehiculo vehiculo: vehiculosTotales) {
			if (codigo == vehiculo.getCodigo()) {
				LocalTime horaSalida = LocalTime.now();
				vehiculo.setHoraSalida(horaSalida);
				tiempo1 = vehiculo.getHoraEntrada().getHour()*60 + vehiculo.getHoraEntrada().getMinute();
				tiempo2= horaSalida.getHour() *60 + horaSalida.getMinute();
				precio = (int) ((tiempo2-tiempo1)*costoMinuto);
				vehiculo.setCosto(precio);
				posicion = Integer.parseInt(vehiculo.getUbicacion().substring(1));
				tipo = vehiculo.getTipo();
				vehiculos.remove(vehiculo);
			} /*for (Contrato contrato: contratos) {
					if (contrato.getCodigo().compareTo(vehiculo.getPlaca())==0) {
						precio=0;
					}*/
		}
		return precio;
	}
 public boolean verificarUsuario (Usuario jefe, String nombre, int contraseña) {
	 return jefe.getUsuario().compareTo(nombre)== 0 && jefe.getContraseña() ==contraseña;
 }

 public boolean verificarEspacioDisponible(int[] vectorEspacios){
	boolean disponible = false;
	for (int i : vectorEspacios) {
		if(i==0){
			disponible=true;
		}
 	}
	return disponible;
 }

 public  String AsignacionCarro(int [] espaciosCarros) {
		if(verificarEspacioDisponible(espaciosCarros)) {
			String ubicacion= " ";
			if (espaciosCarros[espaciosCarros.length-1]==1) {
				System.out.println("no hay");
			}
			for (int i=0; i<espaciosCarros.length; i++) {
				if (espaciosCarros[i]==0) {
					ubicacion = "A"+i;
					espaciosCarros[i]=1;
				} 
				if ((i=espaciosCarros.length-1)==1) {
					return "No existe un espacio disponible";
				}
			}
			return ubicacion;
		}
		else{
			return "No hay espacio disponible";
		}
	}

	public  String AsignacionBici(int [] espaciosBicicletas) {
		String ubicacion = null;

		if (espaciosBicicletas[espaciosBicicletas.length-1]==1) {
			System.out.println("no hay");
		}
		for (int i=0; i<espaciosBicicletas.length; i++) {
			if (espaciosBicicletas[i]==0) {
				ubicacion = "B"+i;
				espaciosBicicletas[i]=1;
				break;
			} 
		
		
			/*if ((i=espaciosBicicletas.length-1)==1) {
				return "No existe un espacio disponible";
			}*/
			
		} 
		return ubicacion;
	}
	public  String AsignacionMoto(int [] espaciosMotos) {
		String ubicacion= null;
		/*if (espaciosMotos[espaciosMotos.length-1]==1) {
			return "no hay";
		}*/
		for (int i=0; i<espaciosMotos.length; i++) {
			if (espaciosMotos[i]==0) {
				espaciosMotos[i]=1;
				ubicacion = "M" + i;
				break;
				//ubicacion = "M"+(i+1);
				//espaciosMotos[i]=1;
			}
		}

			/*if ((i=espaciosMotos.length-1)==1) {
				return "No existe un espacio disponible";
		}*/

		return ubicacion;
	}
	public  void actualizarArray (int [] espaciosBici, int[] espaciosMotos, int[] espaciosCarros) {
		switch (tipo) {
		case "Moto":
			espaciosMotos[posicion]=0;
			break;
		case "Bicileta":
			espaciosBici[posicion]=0;
			break;
		case "Automovil":
			espaciosCarros[posicion]=0;
			break;
		}
			
	}
	//exepcion placa
	public boolean  verificarPlaca(String placa) throws Exception {
        // Definir el patrón para una placa en Colombia (tres letras seguidas de tres números)
        Pattern pattern = Pattern.compile("[A-Z]{3}\\d{3}");
        Matcher matcher = pattern.matcher(placa);
        if (!matcher.matches()) {
            throw new Exception("La placa ingresada es inválida");
        }
		return true;
	}
}
