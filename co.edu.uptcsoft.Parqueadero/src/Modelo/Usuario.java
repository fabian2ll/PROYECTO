package Modelo;

public class Usuario implements Comparable <Usuario> {
	
	//atributos
private String usuario;
private int contraseña;






//constructores

public Usuario() {
	super();
}
public Usuario(String usuario, int contraseña) {
	super();
	this.usuario = usuario;
	
	this.contraseña = contraseña;
}


//toString

@Override
public String toString() {
	return "Usuario [usuario=" + usuario + ", contraseña=" + contraseña
			+ "]";
}


//getters and setters
public String getUsuario() {
	return usuario;
}
public void setUsuario(String usuario) {
	this.usuario = usuario;
}

public int getContraseña() {
	return contraseña;
}
public void setContraseña(int contraseña) {
	this.contraseña = contraseña;
}


//compareTo
@Override
public int compareTo(Usuario o) {

	return this.getUsuario().compareTo(o.getUsuario());
}





}
