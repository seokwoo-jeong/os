package process;

import cpu.Register;

public class Process {
	//global constants
	public enum ERegister{ePC, eCS, eDs, eSS, eHS, eMAR, eMBR, eIR, eStatus, eAC}
	public enum EState{nnew, running, wait, ready, terminated};
	public enum EInterrupt{eIO, eTerminate};
	
	//components - pcb는 외부에 노출해야하고 code랑 stack은 아직까진 잘 모르겠음

	private int[] codeSegment;
	private Segment stackSegment;
	private PCB pcb;
	private int pcbId;
	private String pcbName;
	
	//working variables - 
	
	public String toString() {
		this.pcbName = getPcbName();
		return this.pcbName;
	}
	public int[] getCodeSegment() {
		return codeSegment;
	}
	public String getPcbName() {
		return pcbName;
	}
	public void setPcbName(String pcbName) {
		this.pcbName = pcbName;
	}
	public PCB getPcb() {
		return pcb;
	}
	public void setPcb(PCB pcb) {
		this.pcb = pcb;
	}
	public void setCodeSegment(int[] codeSegment) {
		this.codeSegment = codeSegment;
	}
	
	
	
	
	
	public int getPcbId() {
		return pcbId;
	}
	public void setPcbId(int pcbId) {
		this.pcbId = pcbId;
	}
	public Process(int stackSegmentSize, int[] codes) {
		this.pcb = new PCB(pcbId);
		this.codeSegment = codes;
		this.stackSegment = new Segment(stackSegmentSize);
		
		
	}
	
		
	private class Segment {
		private int[] memory;
		public Segment(int size) {
			this.memory = new int[size];
		}
		@SuppressWarnings("unused")
		public Segment(int[] memory) {
			this.memory = memory;
		}
		@SuppressWarnings("unused")
		public void store(int address, int data) {
			this.memory[address] = data;
		}
		@SuppressWarnings("unused")
		public int fetch(int address) {
			return this.memory[address];
		}
	}
	

	public class PCB {
		private int id;
		
		private Register pc, sp, mar, mbr, ir, ac, status;
		
		
		public int getId() {return id;}
		public void setId(int id) {this.id = id;}
		
		
		
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
		
		public PCB(int pcbId) {
			this.pc = new Register();
			this.sp = new Register();
			this.mar = new Register();
			this.mbr = new Register();
			this.ir = new Register();
			this.ac = new Register();
			this.status = new Register();
			this.id = pcbId;
			
		}

	}
	



	
}
