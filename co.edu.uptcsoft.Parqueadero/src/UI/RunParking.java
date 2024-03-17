package UI;

import java.time.LocalTime;
import java.util.LinkedHashSet;

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
	public static int [] espaciosCarros;
	public static int [] espaciosBicicletas;
	public static int [] espaciosMotos;
	public static String [] opciones =  {"Bicicleta","Moto", "Automovil"};
	public static int codigo=0;
	public static LocalTime hora;
	public static LinkedHashSet <Vehiculo> vehiculos = new LinkedHashSet<Vehiculo>();
	public static LinkedHashSet <Contrato> contratos = new LinkedHashSet<Contrato>(); 
	static ImageIcon imagen = new ImageIcon("co.edu.uptcsoft.Parqueadero\\src\\UI\\icono.png");

	public static void main(String[] args){
		

		 JOptionPane.showMessageDialog(null, "Bienvenido, para comenzar es necesario que el jefe del establecimiento se registre", "Inicio Sistema", 0, imagen);
		 JOptionPane.showMessageDialog(null, registroJefe(), "Inicio Sistema", 0, imagen);
			
		 //registro de espacios en el parqueadero  

		int numBici = verificarCantidadEspacios("BICICLETAS");
		espaciosBicicletas= new int[numBici];
		int numCarro = verificarCantidadEspacios("CARROS");
		espaciosCarros=new int [numCarro];
		int numMoto = verificarCantidadEspacios("MOTOS");
		espaciosMotos=new int [numMoto];
		

	     //mostrar matriz
	     llenarArrays();
	     
	    
	    //eleccion de trabajador
		int opcion = JOptionPane.showConfirmDialog(null, "Usted mismo va a trabajar?", "Trabajador", JOptionPane.YES_NO_OPTION, 0, imagen);
		switch (opcion) {
		case 0: 
			trabajador =jefe;
			break;
		case 1: 
			JOptionPane.showMessageDialog(null, registroTrabajador(), "Inicio Sistema", 0, imagen);
			break;
		}
	
		int op=0;
		
		//eleccion del menu de opciones 
		do {
			op=menu();
		switch (op) {
		case 1: 
			
			
			 //mostrar matriz
		     ingresoVehiculo();
		     
		     
		     
			break;
		case 2:	
			
			
			
			
			
			break;
		case 3: 
			
			String nombre = (String) JOptionPane.showInputDialog(null, "Escriba el nombre del usuario a registrar", "Registro", 0, imagen, null, null);
			int contraseña = Integer.parseInt((String) JOptionPane.showInputDialog(null, "Escriba su contraseña", "Registro", 0, imagen, null, null));
			if (sistema.verificarUsuario(jefe, nombre, contraseña)==true) {
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
case 4: 
			
			break;
		}
		}while (op<5 | op>0); 

	}

	public static int verificarCantidadEspacios(String tipoVehiculo) {
		int cantVehiculo = 0;
		do{
			try {
				cantVehiculo = Integer.parseInt((String) JOptionPane.showInputDialog(null, "Escriba el numero de espacios para " + tipoVehiculo, "Creacion de espacios", 0, imagen, null, null));
				if (cantVehiculo < 0) {
					throw new Exception("Error: Ingresa un numero positivo");
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Error: Ingresa un numero valido", "Error", JOptionPane.ERROR_MESSAGE);
				continue;
			} catch (Exception e){
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				continue;
			}
			return cantVehiculo;
		}while(true);
	}

	public static int menu() {
		int opcionMenu = Integer.parseInt((String) JOptionPane.showInputDialog(null, "1.Ingreso de vehiculo \n 2.Inscripcion de mensualidades \n 3. Registro de vehiculos estacionados \n 4.Retirar vehiculo  ", "Menu de Opciones", 0, imagen, null, null));
	return opcionMenu;
	}
	
	public static String registroJefe() {
		String nombre = null;
		int contraseña = 0;
		do{
			try {
				nombre = (String) JOptionPane.showInputDialog(null, "Escriba el nombre del usuario a registrar", "Registro", 0, imagen, null, null);
				contraseña = Integer.parseInt((String) JOptionPane.showInputDialog(null, "Escriba su contraseña", "Registro", 0, imagen, null, null));
				if (nombre == null || nombre.isEmpty() || contraseña <= 0) {
					throw new Exception("Error: Usuario o contraseña invalidos");
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Error: Ingresa una contraseña valida", "Error", JOptionPane.ERROR_MESSAGE);
				continue;
			} catch (Exception e){
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				continue;
			}
			
			jefe = new Usuario(nombre, contraseña);
			return "registro exitoso";
			
		}while(true);
		
	}
	
	public static String registroTrabajador() {
		String nombre = null;
		int contraseña = 0;
		do{
			try {
				nombre = (String) JOptionPane.showInputDialog(null, "Escriba el nombre del usuario a registrar", "Registro", 0, imagen, null, null);
				contraseña = Integer.parseInt((String) JOptionPane.showInputDialog(null, "Escriba su contraseña", "Registro", 0, imagen, null, null));
				if (nombre == null || nombre.isEmpty() || contraseña <= 0) {
					throw new Exception("Error: Usuario o contraseña invalidos");
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Error: Ingresa una contraseña valida", "Error", JOptionPane.ERROR_MESSAGE);
				continue;
			} catch (Exception e){
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				continue;
			}
			
			trabajador = new Usuario(nombre, contraseña);
			return "registro exitoso";
			
		}while(true);
	}

	public static String ingresoVehiculo() {
		
		int opcion = JOptionPane.showOptionDialog(null, "Seleccione el tipo de vehiculo", "Tipo de Vehiculo", 0,JOptionPane.QUESTION_MESSAGE, imagen, opciones,  "Bicicleta");
		creacionObjeto(opcion);
		return "Ingreso exitoso";
	}
	
	public static String registroMensualidades() {
		
		
		return "Registro exitoso";
		
		
		
	}
	public static void llenarArrays() {
		for (int i=0; i<espaciosBicicletas.length; i++) {
				espaciosBicicletas [i] = 0;
			}
		for (int i=0; i<espaciosMotos.length; i++) {
			espaciosMotos [i] = 0;
		}
		for (int i=0; i<espaciosCarros.length; i++) {
			espaciosCarros [i] = 0;
		}
		
		}
	
	
	/*public static void mostrarEspacios() {
		String espaciosMostrar = " ";
		for (int i=0; i<espacios.length; i++) {
			for (int j=0; j< espacios[i].length; j++) {
				espaciosMostrar += espacios[i][j];
				espaciosMostrar+= "  ";
			}
			espaciosMostrar += " \n";
		}
		JOptionPane.showMessageDialog(null, espaciosMostrar, "Espacios", 0, imagen);
	}*/
	
public static int menuAdministrativo() {
	int opcionMenu = Integer.parseInt((String) JOptionPane.showInputDialog(null, "1.Vehiculos ingresados \n 2.Contratos", "Menu de Opciones Administrativas", 0, imagen, null, null));

	return opcionMenu;
}
public static void  creacionObjeto (int opcion) {
	switch (opcion) {
	case 0: 
		codigo++;
		espaciosDisponiblesBicicletas();
		 hora= LocalTime.now();
		 String horaLlegada = hora.toString();
		Vehiculo bicicleta = new Vehiculo (opciones[0], AsignacionBici(), horaLlegada," ", codigo,50);
		vehiculos.add(bicicleta);
		
		break;
	case 1: 
		espaciosDisponiblesMotos();
		codigo++;
		hora= LocalTime.now();
		horaLlegada = hora.toString();
		String placa = (String) JOptionPane.showInputDialog(null, "Digite la placa", "Placa", 0, imagen, null, null);
		Vehiculo moto = new Vehiculo (opciones[1],placa, AsignacionMoto(), horaLlegada, " ", codigo, 100 );
		vehiculos.add(moto);
		break;
	case 2: 
		espaciosDisponiblesCarros();
		codigo++;
		hora= LocalTime.now();
		horaLlegada = hora.toString();
		placa = (String) JOptionPane.showInputDialog(null, "Digite la placa", "Placa", 0, imagen, null, null);
		Vehiculo carro = new Vehiculo (opciones[2], placa, AsignacionCarro(), horaLlegada, " ", codigo, 150);
		vehiculos.add(carro);
		break;
		
	}
}
public static void espaciosDisponiblesBicicletas() {
	String espaciosMostrar = " ";
	for (int i=0; i< espaciosBicicletas.length; i++) {
		espaciosMostrar += espaciosBicicletas[i];
		espaciosMostrar+= "  ";
	}
	JOptionPane.showMessageDialog(null, espaciosMostrar, "Espacios Bicicletas", 0, imagen);
}
public static void espaciosDisponiblesMotos() {
	String espaciosMostrar = " ";
	for (int i=0; i< espaciosMotos.length; i++) {
		espaciosMostrar += espaciosMotos[i];
		espaciosMostrar+= "  ";
	}
	JOptionPane.showMessageDialog(null, espaciosMostrar, "Espacios Motos", 0, imagen);
}

public static void espaciosDisponiblesCarros() {
	String espaciosMostrar = " ";
	for (int i=0; i< espaciosCarros.length; i++) {
		espaciosMostrar += espaciosCarros[i];
		espaciosMostrar+= "  ";
	}
	JOptionPane.showMessageDialog(null, espaciosMostrar, "Espacios Carros", 0, imagen);
}
public static String AsignacionCarro() {
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
public static String AsignacionBici() {
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
public static String AsignacionMoto() {
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
}