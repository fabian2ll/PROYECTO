package UI;

import java.util.LinkedHashSet;
import java.util.TreeSet;

import javax.swing.ImageIcon;

import javax.swing.JOptionPane;

import Logica.Sistema;
import Modelo.Contrato;
import Modelo.Usuario;
import Modelo.Vehiculo;

public class RunParking {

	
	//variables de la clase
	public static Sistema sistema= new Sistema();
	public static Usuario jefe;
	public static Usuario trabajador;
	public static int [][] espacios;
	public static LinkedHashSet <Vehiculo> vehiculos = new LinkedHashSet<Vehiculo>();
	public static LinkedHashSet <Contrato> contratos = new LinkedHashSet <Contrato>(); 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		 JOptionPane.showMessageDialog(null, "Bienvenido, para comenzar es necesario que el jefe del establecimiento se registre", "Inicio Sistema", 0, new ImageIcon("C:\\Users\\USER\\eclipse-workspace\\co.edu.uptcsoft.Parqueadero\\src\\Logica\\icono.png"));
		 JOptionPane.showMessageDialog(null, registroJefe(), "Inicio Sistema", 0, new ImageIcon("C:\\Users\\USER\\eclipse-workspace\\co.edu.uptcsoft.Parqueadero\\src\\Logica\\icono.png"));
			
		 //registro de espacios en el parqueadero 
		 int filas = Integer.parseInt((String) JOptionPane.showInputDialog(null, "Escriba el numero de filas del parqueadero ", "Creacion de espacios", 0, new ImageIcon("C:\\Users\\USER\\eclipse-workspace\\co.edu.uptcsoft.Parqueadero\\src\\Logica\\icono.png"), null, null));
		 int columnas = Integer.parseInt((String) JOptionPane.showInputDialog(null, "Escriba el numero de columnas del parqueadero", "Creacion de espacios", 0, new ImageIcon("C:\\Users\\USER\\eclipse-workspace\\co.edu.uptcsoft.Parqueadero\\src\\Logica\\icono.png"), null, null));
	     espacios = new int [filas][columnas];
	     
	     //mostrar matriz
	     llenarMatriz();
	     
	    
	     //eleccion de trabajador
		int opcion = JOptionPane.showConfirmDialog(null, "Usted mismo va a trabajar?", "Trabajador", JOptionPane.YES_NO_OPTION, 0, new ImageIcon("C:\\Users\\USER\\eclipse-workspace\\co.edu.uptcsoft.Parqueadero\\src\\Logica\\icono.png"));
		switch (opcion) {
		case 0: 
			trabajador =jefe;
			break;
		case 1: 
			JOptionPane.showMessageDialog(null, registroTrabajador(), "Inicio Sistema", 0, new ImageIcon("C:\\Users\\USER\\eclipse-workspace\\co.edu.uptcsoft.Parqueadero\\src\\Logica\\icono.png"));
			break;
		}
	
		
		
		//eleccion del menu de opciones 
		switch (menu()) {
		case 1: 
			
			
			 //mostrar matriz
		     mostrarEspacios();
		     ingresoVehiculo();
		     
		     
		     
			break;
		case 2:	
			
			
			
			
			
			break;
		case 3: 
			
			String nombre = (String) JOptionPane.showInputDialog(null, "Escriba el nombre del usuario a registrar", "Registro", 0, new ImageIcon("C:\\Users\\USER\\eclipse-workspace\\co.edu.uptcsoft.Parqueadero\\src\\Logica\\icono.png"), null, null);
			int contraseña = Integer.parseInt((String) JOptionPane.showInputDialog(null, "Escriba su contraseña", "Registro", 0, new ImageIcon("C:\\Users\\USER\\eclipse-workspace\\co.edu.uptcsoft.Parqueadero\\src\\Logica\\icono.png"), null, null));
			if (sistema.verificarUsuario(jefe, nombre, contraseña)) {
				switch (menuAdministrativo()) {
				case 1:
					// mostrar todos los vehiculos, incluyendo los de contrato si ingresaron en alguna hora
					
					
					break;
				case 2: 
					//mostrar todos los contratos registrados con sus datos especificos
					
					
					break;
				}
			}else {
				//mensaje de alerta, usuario incorrecto
			}
			
			
			break;
		}

	}

	public static int menu() {
		int opcionMenu = Integer.parseInt((String) JOptionPane.showInputDialog(null, "1.Ingreso de vehiculo \n 2.Inscripcion de mensualidades \n 3. Registro de vehiculos estacionados ", "Menu de Opciones", 0, new ImageIcon("C:\\Users\\USER\\eclipse-workspace\\co.edu.uptcsoft.Parqueadero\\src\\Logica\\icono.png"), null, null));
	return opcionMenu;
	}
	
	public static String registroJefe() {
		
		String nombre = (String) JOptionPane.showInputDialog(null, "Escriba el nombre del usuario a registrar", "Registro", 0, new ImageIcon("C:\\Users\\USER\\eclipse-workspace\\co.edu.uptcsoft.Parqueadero\\src\\Logica\\icono.png"), null, null);
		int contraseña = Integer.parseInt((String) JOptionPane.showInputDialog(null, "Escriba su contraseña", "Registro", 0, new ImageIcon("C:\\Users\\USER\\eclipse-workspace\\co.edu.uptcsoft.Parqueadero\\src\\Logica\\icono.png"), null, null));
		jefe= new Usuario(nombre,contraseña);
		return "registro exitoso";
	}
	
	public static String registroTrabajador() {
		String nombre = (String) JOptionPane.showInputDialog(null, "Escriba el nombre del usuario a registrar", "Registro", 0, new ImageIcon("C:\\Users\\USER\\eclipse-workspace\\co.edu.uptcsoft.Parqueadero\\src\\Logica\\icono.png"), null, null);
		int contraseña = Integer.parseInt((String) JOptionPane.showInputDialog(null, "Escriba su contraseña", "Registro", 0, new ImageIcon("C:\\Users\\USER\\eclipse-workspace\\co.edu.uptcsoft.Parqueadero\\src\\Logica\\icono.png"), null, null));
		trabajador= new Usuario(nombre,contraseña);
		return "registro exitoso";
	}
	public static String ingresoVehiculo() {
		
		
		
		return "Ingreso exitoso";
	}
	
	public static String registroMensualidades() {
		
		
		return "Registro exitoso";
		
	}
	public static void llenarMatriz() {
		for (int i=0; i<espacios.length; i++) {
			for (int j=0; j< espacios[i].length; j++) {
				espacios [i][j] = 0;
			}
			 
		}
	}
	
	public static void mostrarEspacios() {
		String espaciosMostrar = " ";
		for (int i=0; i<espacios.length; i++) {
			for (int j=0; j< espacios[i].length; j++) {
				espaciosMostrar += espacios[i][j];
				espaciosMostrar+= "  ";
			}
			espaciosMostrar += " \n";
		}
		JOptionPane.showMessageDialog(null, espaciosMostrar, "Espacios", 0, new ImageIcon("C:\\Users\\USER\\eclipse-workspace\\co.edu.uptcsoft.Parqueadero\\src\\Logica\\icono.png"));
	}
	
public static int menuAdministrativo() {
	int opcionMenu = Integer.parseInt((String) JOptionPane.showInputDialog(null, "1.Vehiculos ingresados \n 2.Contratos", "Menu de Opciones Administrativas", 0, new ImageIcon("C:\\Users\\USER\\eclipse-workspace\\co.edu.uptcsoft.Parqueadero\\src\\Logica\\icono.png"), null, null));

	return opcionMenu;
}
	
}
