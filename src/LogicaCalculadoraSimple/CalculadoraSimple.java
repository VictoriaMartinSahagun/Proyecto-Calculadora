package LogicaCalculadoraSimple;
import java.io.File;
import java.util.ArrayList;

public class CalculadoraSimple {
	File dir;
	ArrayList<PluginInterface> plugins;
	ArrayList<String> nombrePlugins;
	
	public CalculadoraSimple() {
		dir= new File("/bin/plugins");
		plugins = new ArrayList<PluginInterface>();
		nombrePlugins = new ArrayList<String>();
	}
	
	public void getPlugins() {
		ClassLoader cl = new CargaPlugins(dir);
		String[] files = dir.list();
		if(files!=null) {
			System.out.println("al fin entre");
			for (int i=0; i<files.length; i++) {
				try {
					// only consider files ending in ".class"
					if (! files[i].endsWith(".class"))
						continue;
					Class c = cl.loadClass(files[i].substring(0, files[i].indexOf(".")));
					Class[] intf = c.getInterfaces();
					for (int j=0; j<intf.length; j++) {
						System.out.println();
						if (intf[j].getName().contentEquals("LogicaCalculadoraSimple.PluginInterface")) {
							PluginInterface pf = (PluginInterface) c.getDeclaredConstructor().newInstance();
							plugins.add(pf);
							nombrePlugins.add(pf.getPluginName());
							continue;
						}
					}
				} catch (Exception ex) {
					System.err.println("File " + files[i] + " does not contain a valid PluginFunction class.");
				}
			}
		}
	}
	
	public ArrayList<String> getPluginsName(){
		return nombrePlugins;
	}
	
	public int runPlugin(int p1, int p2, String nombre) {
		int cantPlugs= plugins.size();
		int res=0;
		boolean encontre=false;
		for(int i=0; i<cantPlugs && !encontre;i++) {
			PluginInterface pf = plugins.get(i);
			if(pf.getPluginName().equals(nombre)) {
				encontre=true;
				pf.setParameters(p1, p2);
				res= pf.getResult();
			}
		}
		return res;
	}
}
