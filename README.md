# ProgramaciónJAVA
## Descripcion
Este es el ejercicio 14 de java sobre utilizacion de archivos binarios , en el que hay que almacenar información sobre una lista de productos con la utilizacion 
FileInputStream y FileOutputStream junto con ObjectInputStream y ObjectOutputStream para manejar los ficheros binarios. 

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


