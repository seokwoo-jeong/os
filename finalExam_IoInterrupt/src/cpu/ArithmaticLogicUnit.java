package cpu;

import constant.Constant;

public class ArithmaticLogicUnit {
	private Register ac, mbr, status;

	private int sum=Constant.zero;
	private double avg;
	public Register getAc() {return ac;}
	public void setAc(Register ac) {this.ac = ac;}

	public Register getMbr() {return mbr;}
	public void setMbr(Register mbr) {this.mbr = mbr;}

	public Register getStatus() {return status;}
	public void setStatus(Register status) {this.status = status;}

	public void connect(Register ac, Register mbr, Register status) {
		this.ac = ac;
		this.mbr = mbr;
		this.status = status;
		
	}
	
	public double getAvg() {
		return avg;
	}
	public void setAvg(double avg) {
		this.avg = avg;
	}

	public int getSum() {
		return sum-Constant.one;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public void add(int opcode) {
		this.ac.setDate(this.ac.getDate() + opcode);

	}

	public void sum() {
		if(sum != Constant.twentyOne) {
		this.ac.setDate(this.ac.getDate()+sum);
		sum++;
		}
	}
	public int cmp() {
		if(sum == Constant.twentyOne) {
			sum=Constant.one;
			return Constant.two;
		}
		return Constant.zero;
		
	}
	public void avg() {
		avg = this.ac.getDate()/(double)Constant.twenty;
		
		
		
	}
	
	
	
	

}
