package LogicaCalculadoraSimple;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

public class CargaPlugins extends ClassLoader {
	File directorio;
	public CargaPlugins(File dir) {
		directorio = dir;
	}
	public Class CargarClase (String nombre) throws ClassNotFoundException { 
		return loadClass(nombre, true); 
	}
	public Class CargarClases (String nombreClase, boolean cargada) throws ClassNotFoundException {
		try {
	    	Class c = findLoadedClass(nombreClase);
	    	if (c == null) {
	    		try { 
	    			c = findSystemClass(nombreClase); 
	    		}catch (Exception ex) {}
	        }
	        if (c != null) {
	        	String nombreArchivo = nombreClase.replace('.',File.separatorChar)+".class";
	        	File f = new File(directorio, nombreArchivo);
	        	int length = (int) f.length();
	        	byte[] classbytes = new byte[length];
	        	DataInputStream in = new DataInputStream(new FileInputStream(f));
	        	in.readFully(classbytes);
	        	in.close();
	        	c = defineClass(nombreClase, classbytes, 0, length);
	        }
	        if (cargada) resolveClass(c);
	        return c;
	    }catch (Exception ex) { 
	    	throw new ClassNotFoundException(ex.toString());
	    }
	}
}
