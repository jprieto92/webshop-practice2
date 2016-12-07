package es.uc3m.tiw.calculadora.dominios;

public class Operandos {

	private int operando1;
	private int operando2;
	
	
	public int getOperando1() {
		return operando1;
	}
	public void setOperando1(int operando1) {
		this.operando1 = operando1;
	}
	public int getOperando2() {
		return operando2;
	}
	public void setOperando2(int operando2) {
		this.operando2 = operando2;
	}
	@Override
	public String toString() {
		return "Operandos [operando1=" + operando1 + ", operando2=" + operando2 + "]";
	}
	
	
	
}
