package es.uc3m.tiw.domains;

import java.io.Serializable;

public class Operands implements Serializable{

	private static final long serialVersionUID = 1L;
	private int operand1;
	private int operand2;
	
	
	public int getOperand1() {
		return operand1;
	}
	public void setOperand1(int operand1) {
		this.operand1 = operand1;
	}
	public int getOperand2() {
		return operand2;
	}
	public void setOperand2(int operand2) {
		this.operand2 = operand2;
	}
	@Override
	public String toString() {
		return "Operands [operand1=" + operand1 + ", operand=" + operand2 + "]";
	}
	public Operands() {
		super();
	}
	public Operands(int operand1, int operand2) {
		super();
		this.operand1 = operand1;
		this.operand2 = operand2;
	}
	
	
	
}
