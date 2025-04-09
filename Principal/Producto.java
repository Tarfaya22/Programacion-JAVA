package Principal;

import java.io.Serializable;
import java.util.Scanner;

public class Producto implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int codigo;
	private String nombre;
	private double precio;
	private int cantidad;
	Scanner sc = new Scanner(System.in);
	
	public Producto(int codigo, String nombre, double precio, int cantidad) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.precio = precio;
		this.cantidad = cantidad;
	}

	public Producto(Scanner sc)
	{
		System.out.println("Introduzca el codigo del producto: ");
		codigo = sc.nextInt();
		System.out.println("Introduzca el nombre del producto: ");
		nombre = sc.nextLine();
		sc.nextLine();
		System.out.println("Introduzca el precio del producto: ");
		precio = sc.nextDouble();
		System.out.println("Introduzca la cantidad del producto: ");
		cantidad = sc.nextInt();
	}

	public int getCodigo() {
		return codigo;
	}


	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	@Override
	public String toString() {
		return  codigo + " " + nombre + " " + precio + " " + cantidad;
	}
	
	
	
	
}
