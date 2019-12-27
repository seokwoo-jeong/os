
package cpu;

import memory.Memory;

public class CentralProcessingUnit {
	//components
	private ControlUnit controlUnit;
	private ArithmaticLogicUnit arithmaticLogicUnit;

	//association
	private Memory memory;
	private Register pc, sp, mar, mbr, ir, ac, status;
	
	public CentralProcessingUnit() {
		//components
		this.controlUnit = new ControlUnit();
		this.arithmaticLogicUnit = new ArithmaticLogicUnit();
		
		this.pc = new Register();
		this.sp = new Register();
		this.mar = new Register();
		this.mbr = new Register();
		this.ir = new Register();
		this.ac = new Register();
		this.status = new Register();

		this.controlUnit.connect(this.pc, this.ir, this.sp, this.mar, this.status, this.ac, this.mbr);
		this.arithmaticLogicUnit.connect(this.ac, this.mbr, this.status);
		
		this.controlUnit.connect(this.arithmaticLogicUnit);
	}
	
	public void connect(Memory memory) {
		this.memory = memory;
		this.controlUnit.connect(this.memory);
		this.memory.connect(this.mar, this.mbr, this.pc);
	}
	
	public void run(process.Process process) {	
		this.controlUnit.run(process);
	}
	
	public Register getPc() {return pc;}
	public void setPc(Register pc) {this.pc = pc;}

	public Register getSp() {return sp;}
	public void setSp(Register sp) {this.sp = sp;}

	public Register getMar() {return mar;}
	public void setMar(Register mar) {this.mar = mar;}

	public Register getMbr() {return mbr;}
	public void setMbr(Register mbr) {this.mbr = mbr;}

	public Register getIr() {return ir;}
	public void setIr(Register ir) {this.ir = ir;}
	
	public Register getAc() {return ac;}
	public void setAc(Register ac) {this.ac = ac;}

	public Register getStatus() {return status;}
	public void setStatus(Register status) {this.status = status;}

	
}