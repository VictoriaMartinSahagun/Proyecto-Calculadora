package LogicaCalculadoraSimple;

public class Resta implements PluginInterface{
	private int param1,param2;
	@Override
	public String getPluginName() {
		return "Resta";
	}

	@Override
	public void setParameters(int param1, int param2) {
		this.param1=param1;
		this.param2=param2;
	}

	@Override
	public int getResult() {
		return (param1-param2);
	}

}
