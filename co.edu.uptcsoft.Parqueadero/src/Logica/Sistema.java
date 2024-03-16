package Logica;

import Modelo.Usuario;

public class Sistema {
	
	
	
 public boolean verificarUsuario (Usuario jefe, String nombre, int contraseña) {
	 return jefe.getUsuario().compareTo(nombre)== 0 && jefe.getContraseña() ==contraseña;
 }
}
