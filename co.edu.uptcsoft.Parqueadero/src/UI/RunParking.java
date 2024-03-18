package UI;

import java.time.LocalTime;
import java.util.LinkedHashSet;

import javax.swing.ImageIcon;

import javax.swing.JOptionPane;

import Logica.Sistema;
import Modelo.Contrato;
import Modelo.Usuario;
import Modelo.Vehiculo;
//contra repositorio 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
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
	public static int costoMinutoCarroMoto;
	public static int costoMinutoBici;
	public static LinkedHashSet <Vehiculo> vehiculosTotales = new LinkedHashSet<Vehiculo>();
	public static LinkedHashSet <Vehiculo> vehiculosPresentes = new LinkedHashSet<Vehiculo>();
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
		

		//tarifas 
		costoMinutoCarroMoto= asignacionTarifasCarrosMotos();
		costoMinutoBici= asignacionTarifaBici();
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
	
		char op;
		
		//eleccion del menu de opciones 
		do {
			op=menu().charAt(0);
		switch (op) {
		case 'E': 
			JOptionPane.showMessageDialog(null, ingresoVehiculo(), "Verificacion", 0, imagen);
		    break;
		case 'I':		
			registroMensualidades();
			
			break;
		
		case 'V':
			if(jefe==trabajador){
				menuAdministrativo();
			}
			else{
				JOptionPane.showMessageDialog(null, "Debes ser administrador");
				cambiarUsuario();
			}
			break;
		case 'S': 
			switch (opcionesBusqueda()) {
			case 1:
				int codigoBuscado =Integer.parseInt((String) JOptionPane.showInputDialog(null, "Escriba su codigo", "Busqueda Codigo", 0, new ImageIcon("C:\\Users\\USER\\eclipse-workspace\\co.edu.uptcsoft.Parqueadero\\src\\Logica\\icono.png"), null, null));
				JOptionPane.showMessageDialog(null, "PRECIO TOTAL: "+ sistema.retirarVehiculoCodigo(vehiculosPresentes, contratos, codigoBuscado, costoMinutoBici), "Factura", 0, new ImageIcon("file:///C:/Users/USER/git/proyecto/co.edu.uptcsoft.Parqueadero/src/Logica/icono.png"));
				sistema.actualizarArray(espaciosBicicletas, espaciosMotos, espaciosCarros);
				break;
			case 2: 
				String placaBuscada = (String) JOptionPane.showInputDialog(null, "Escriba su placa", "Busqueda Placa", 0, new ImageIcon("C:\\Users\\USER\\eclipse-workspace\\co.edu.uptcsoft.Parqueadero\\src\\Logica\\icono.png"), null, null);
				JOptionPane.showMessageDialog(null, "PRECIO TOTAL: "+ sistema.retirarVehiculoPlaca(vehiculosPresentes, contratos, placaBuscada, costoMinutoCarroMoto), "Factura", 0, new ImageIcon("file:///C:/Users/USER/git/proyecto/co.edu.uptcsoft.Parqueadero/src/Logica/icono.png"));
				sistema.actualizarArray(espaciosBicicletas, espaciosMotos, espaciosCarros);
			break;
		}
		}
		}while (op<5 | op>0); 

	}

	public static int opcionesBusqueda () {
		
		return Integer.parseInt((String) JOptionPane.showInputDialog(null, "Escoja la forma en la que desea buscar su vehiculo \n 1.Codigo \n 2.Placa", "Retirar vehiculo", 0, new ImageIcon("C:\\Users\\USER\\eclipse-workspace\\co.edu.uptcsoft.Parqueadero\\src\\Logica\\icono.png"), null, null));

	}
	public static void cambiarUsuario(){
		String nombre = null;
		int contraseña = 0;
		do{
			try {
				nombre = (String) JOptionPane.showInputDialog(null, "Usuario Administrador", "Cambio de usuario", 0, imagen, null, null);
				contraseña = Integer.parseInt((String) JOptionPane.showInputDialog(null, "Escriba su contraseña", "Cambio de usuario", 0, imagen, null, null));
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
			if(nombre.equals(jefe.getUsuario()) && contraseña == jefe.getContraseña()){
				trabajador=jefe;
				break;
			}
			
			//return "registro exitoso";	
		}while(true);
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

	public static String menu() {
		String[] opciones = {"Entrada de vehiculo", "Inscripción de mensualidades","Ver registros", "Salida vehiculo"};
		String opcionMenu =(String) JOptionPane.showInputDialog(null, "Elije una opción", "Menu de Opciones", 0, imagen, opciones, opciones[0]);
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
		if(creacionObjeto(opcion)){
			return "Ingreso exitoso";
		}
		else{
			return "Error: No se puede ingresar";
		}
	}
	
	public static String registroMensualidades() {	
		String placa= null, tipoVehiculo=null;
		JOptionPane.showMessageDialog(null, "Para inscribirse a las mensualidades necesitamos los siguientes datos", "Observacion", 0, imagen);
		String nombre = null, identificacion = null;
		try{
			nombre = (String) JOptionPane.showInputDialog(null, "Escriba el nombre del usuario a registrar", "Registro mensualidades", 0, imagen, null, null);
			identificacion = (String) JOptionPane.showInputDialog(null, "Escriba su numero de documento de identidad", "Registro mensualidades", 0, imagen, null, null);
			if (nombre == null || nombre.isEmpty() || identificacion.isEmpty()) {
				throw new Exception("Error: Usuario o identificacion invalidos");
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Error: Ingresa una contraseña valida", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}

		int tipo = JOptionPane.showOptionDialog(null, "Seleccione el tipo de vehiculo", "Registro mensualidades", 0,JOptionPane.QUESTION_MESSAGE, imagen, opciones,  "Bicicleta");
		switch (tipo) {
		case 0:
			 tipoVehiculo = opciones[0];
			break;
		case 1:
			tipoVehiculo = opciones[1];
			 placa = (String) JOptionPane.showInputDialog(null, "Escriba la placa del vehiculo", "Registro mensualidades", 0, imagen, null, null);
			break;
		case 2:
			tipoVehiculo = opciones[2];
			placa = (String) JOptionPane.showInputDialog(null, "Escriba la placa del vehiculo", "Registro mensualidades", 0, imagen, null, null);
			break;
		}
		String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre","Octubre", "Noviembre", "Diciembre"};
		String mesInicio = (String) JOptionPane.showInputDialog(null, "Escriba el mes de inciio del contrato", "Registro mensualidades", 0, imagen, meses, meses[0]);
		int costoMensual = 0;
		try{
		costoMensual = Integer.parseInt((String) JOptionPane.showInputDialog(null, "Escriba el costo que asume el dueño del vehiculo", "Registro mensualidades", 0, imagen, null, null));
		} catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Error: Ingresa un valor valido", "Error", JOptionPane.ERROR_MESSAGE);
		}

		String firma = null;
		try{
			firma = (String) JOptionPane.showInputDialog(null, "Escriba su firma", "Registro mensualidades", 0, imagen, null, null);
			if (!firma.matches("[a-zA-Z ]*")) {
			throw new Exception("Error: La firma debe contener solo letras");
			}
		} catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		contratos.add(new Contrato(placa, identificacion, nombre, tipoVehiculo, costoMensual, firma, mesInicio));
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
	
	public static void menuAdministrativo() {
		String[] opciones = {"Vehiculos", "Contratos"};
		String opcionMenu = (String) JOptionPane.showInputDialog(null, "Elija una opción", "Menu de Opciones Administrativas", 0, imagen, opciones, opciones[0]);
	
		switch (opcionMenu) {
			case "Vehiculos":
				String vehiculosInfo = "Lista de Vehiculos:\n";
				for (Vehiculo vehiculo : vehiculosTotales) {
					vehiculosInfo += "Tipo: " + vehiculo.getTipo() + ", Placa: " + vehiculo.getPlaca() + ", Ubicacion: " + vehiculo.getUbicacion() + "\n";
				}
				JOptionPane.showMessageDialog(null, vehiculosInfo, "Vehiculos", JOptionPane.INFORMATION_MESSAGE);
				break;
			case "Contratos":
				String contratosInfo = "Lista de Contratos:\n";
				for (Contrato contrato : contratos) {
					contratosInfo += "Vehiculo: " + contrato.getPlaca() + ", Usuario: " + contrato.getTitular() + ", Fecha de Inicio: " ; //+ contrato.getFechaInicio() + ", Fecha de Fin: " + contrato.getFechaFin() + "\n";
				}
				JOptionPane.showMessageDialog(null, contratosInfo, "Contratos", JOptionPane.INFORMATION_MESSAGE);
				break;
		}
	}

public static boolean creacionObjeto (int opcion) {
	boolean confirmacion = false; 
	switch (opcion) {
	case 0: 
		codigo++;
		espaciosDisponiblesBicicletas();
		hora= LocalTime.now();
		if(!sistema.verificarEspacioDisponible(espaciosBicicletas)){
			JOptionPane.showMessageDialog(null, "No hay espacios disponibles");
			break;
		}
		JOptionPane.showMessageDialog(null, "Codigo: " + codigo, "Entrega Codigo", 0, imagen);
		Vehiculo bicicleta = new Vehiculo (opciones[0], sistema.AsignacionBici(espaciosBicicletas), hora,hora, codigo,0);
		vehiculosPresentes.add(bicicleta);
		vehiculosTotales.add(bicicleta);
		confirmacion = true;
		
		break;
	case 1: 
		espaciosDisponiblesMotos();
		codigo++;
		hora= LocalTime.now();
		if(!sistema.verificarEspacioDisponible(espaciosMotos)){
			JOptionPane.showMessageDialog(null, "No hay espacios disponibles");
			break;
		}
		String placa = (String) JOptionPane.showInputDialog(null, "Digite la placa", "Placa", 0, imagen, null, null);
		Vehiculo moto = new Vehiculo (opciones[1],placa, sistema.AsignacionMoto(espaciosMotos), hora, hora, codigo, 0 );
		vehiculosPresentes.add(moto);
		vehiculosTotales.add(moto);
		confirmacion = true;
		break;
	case 2: 
		espaciosDisponiblesCarros();
		codigo++;
		hora= LocalTime.now();
		if(!sistema.verificarEspacioDisponible(espaciosCarros)){
			JOptionPane.showMessageDialog(null, "No hay espacios disponibles");
			break;
		}
		
		placa = (String) JOptionPane.showInputDialog(null, "Digite la placa", "Placa", 0, imagen, null, null);
		Vehiculo carro = new Vehiculo (opciones[2], placa, sistema.AsignacionCarro(espaciosCarros), hora, hora, codigo, 150);
		vehiculosPresentes.add(carro);
		vehiculosTotales.add(carro);
		confirmacion=true;
		break;
		
	}
	return confirmacion;
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
public static int asignacionTarifasCarrosMotos() {
	int precio =0 ;
	do{
		try {
			precio = Integer.parseInt((String) JOptionPane.showInputDialog(null, "Escriba el precio para CARROS y MOTOS " , "Asignacion tarifas", 0, imagen, null, null));
			if (precio < 0) {
				//2observacion=true;
				throw new Exception("Error: Ingresa un numero positivo");
			}
		} catch (NumberFormatException e) {
			//observacion=true;
			JOptionPane.showMessageDialog(null, "Error: Ingresa un numero valido", "Error", JOptionPane.ERROR_MESSAGE);
			continue;
		} catch (Exception e){
			//observacion=true;
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			continue;
		}
		return precio;
	}while( true );
}
public static int asignacionTarifaBici() {
	int precio=0;
	do{
		try {
		precio = Integer.parseInt((String) JOptionPane.showInputDialog(null, "Escriba el precio para BICICLETAS " , "Asignacion tarifas", 0, imagen, null, null));
			if (precio < 0) {
				throw new Exception("Error: Ingresa un numero positivo");
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Error: Ingresa un numero valido", "Error", JOptionPane.ERROR_MESSAGE);
			continue;
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			continue;
		}
		return precio;
	}while(true);
}
}