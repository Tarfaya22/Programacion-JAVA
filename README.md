# ProgramaciónJAVA
## Descripcion
### Este es el ejercicio 14 de java sobre utilizacion de archivos binarios , en el que hay que almacenar información sobre una lista de productos con la utilizacion FileInputStream y FileOutputStream junto con ObjectInputStream y ObjectOutputStream para manejar los ficheros binarios. 
---
## El ejercicio esta dividido en 3 clases:
### Main.class
```java
package Principal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main 
{

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		Productos productos = new Productos();
		int codigo, cantidad;
		Producto exist;
		try {
		productos.leerFicheroBinario("Mondongo.dat");
		}catch(FileNotFoundException e)
		{
			System.out.println("Nain!!!!");
		}
		int opcion;
		do {
			
			opcion = menu(sc);
			switch(opcion)
			{
			case 1:
				productos.add(new Producto(sc));
				productos.escribirFicheroBinario("Mondongo.dat");
				break;
			case 2:
				System.out.println(productos);
				break;
			case 3:
				System.out.println("Introduzca el codigo del producto para poder buscarlo: ");
				codigo = sc.nextInt();
				productos.buscarProducto(codigo);
				System.out.println(productos.buscarProducto(codigo));
				break;
			case 4:
				System.out.println("Introduzca el codigo del producto: ");
				codigo = sc.nextInt();
				System.out.println("Introduzca la nueva cantidad del producto: ");
				cantidad = sc.nextInt();
				sc.nextLine();
				productos.actualizarProducto(codigo, cantidad);
				productos.escribirFicheroBinario("Mondongo.dat");
				System.out.println("Guardando datos...");
				break;
			case 5: 
				System.out.println("Introduzca el codigo del producto:");
				codigo = sc.nextInt();
				productos.eliminarProducto(codigo);	
				productos.escribirFicheroBinario("Mondongo.dat");
				System.out.println("Guardando datos...");
			case 6:
				productos.escribirFicheroBinario("Mondongo.dat");
				System.out.println("Programa finalizado!");
				System.out.println("Guardando datos...");
			}
		}while(opcion!=6);
		
		
		

	}
	
	public static int menu(Scanner sc)
	{
		int opcion;
		do
		{
			System.out.println("1 - Añadir producto");
			System.out.println("2 - Listar productos");
			System.out.println("3 - Buscar producto");
			System.out.println("4 - Actualizar stok");
			System.out.println("5 - Eliminar producto");
			System.out.println("6 - Salir");
			System.out.println("Opcion:");
			try{opcion = sc.nextInt();
			}
			catch(InputMismatchException e)
			{
				opcion = 0;
			}
			sc.nextLine();
			if(opcion<1 || opcion>6)
				System.out.println("Error");
		}
		while(opcion<1 || opcion>6);
		return opcion;
	}

}
```
---
### Producto.class
```java
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
```
---
### Productos.class
```java
package Principal;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Productos 
{

	ArrayList<Producto> lista;
	
	
	public Productos()
	{
		lista = new ArrayList<>();
	}
	
	public boolean add(Producto p)
	{
		return lista.add(p);
	}
	
	public Producto get(int i)
	{
		return  lista.get(i);
	}
	
	public void escribirFicheroBinario(String nombreFichero) throws IOException
	{
		FileOutputStream fos = new FileOutputStream(nombreFichero);
		ObjectOutputStream salida = new ObjectOutputStream(fos);
		
		for(Producto p: lista)
		{
			salida.writeInt(p.getCodigo());
			salida.writeUTF(p.getNombre());
			salida.writeDouble(p.getPrecio());
			salida.writeInt(p.getCantidad());
		}
		
		salida.close();
		fos.close();
	}
	
	public void leerFicheroBinario(String nombreFichero) throws IOException
	{
		FileInputStream fis = new FileInputStream(nombreFichero);
		ObjectInputStream salida = new ObjectInputStream(fis);
		boolean fin = false;
		lista.clear();
		
		while(!fin)
		{
			try {
			lista.add(new Producto(salida.readInt(),salida.readUTF(),salida.readDouble(),salida.readInt()));
			}
			catch(EOFException e)
			{
				break;
			}
		}
		salida.close();
		fis.close();
	}
	
	public Producto buscarProducto(int codigo)
	{
		for(Producto i:lista)
		{
			if(codigo ==i.getCodigo());
					return i;
		}
		return null;
	}
	
	public void eliminarProducto(int codigo)
	{
		 Producto p; 
		
		p = buscarProducto(codigo);
		if(p!=null)
		{
			lista.remove(p);
		}
	}
	
	public void actualizarProducto(int codigo, int cantidad)
	{
		Producto p2;
		
		p2 = buscarProducto(codigo);
		
		if(p2!=null)
		{
			p2.setCantidad(cantidad);
		}
	}
	
	public String toString()
	{
		String resultado="";
		for(Producto p: lista)
			resultado+= p+"\n";
		return resultado;
	}
}

```


