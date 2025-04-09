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
