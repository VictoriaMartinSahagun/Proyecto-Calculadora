package LogicaCalculadoraSimple;

public class Potencia implements PluginInterface{
	private int param1,param2;
	@Override
	public String getPluginName() {
		return "Potencia";
	}

	@Override
	public void setParameters(int param1, int param2) {
		this.param1=param1;
		this.param2=param2;
	}

	@Override
	public int getResult() {
		return (int) Math.pow(param1, param2);
	}

}
