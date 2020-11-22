package LogicaCalculadoraSimple;

public class Division implements PluginInterface{
	private int param1,param2;
	@Override
	public String getPluginName() {
		return "Division";
	}

	@Override
	public void setParameters(int param1, int param2) {
		this.param1=param1;
		this.param2=param2;
	}

	@Override
	public int getResult(){
		if(param2!=0) {
			return (param1/param2);
		}else {
			throw new ArithmeticException();
		}
	}

}
