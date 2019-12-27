package cpu;



import memory.Memory;
import constant.Constant;
public class ControlUnit {
	//attributes속성
	
	enum EInstruction {HLT, LDA, LDV, STA, STV, ADD, AND, JMP, BZ, NOT, SHR, PRT};
	
	private Register pc, sp, mar, ir, status, mbr, ac;
	private int opcode;
	private int address;
	
	//association
	private Memory memory;
	private ArithmaticLogicUnit arithmaticLogicUnit;

	public ControlUnit() {
		Constant.eState = Constant.EState.halt;

	}
	public void connect(Memory memory) {
		this.memory = memory;
		
	}

	public void connect(Register pc, Register ir, Register sp, Register mar, Register status,Register ac, Register mbr) {
		this.pc = pc;
		this.ir = ir;
		this.sp = sp;
		this.mar = mar;
		this.ac = ac;
		this.mbr = mbr;
		this.status = status;
	}
	public void connect(ArithmaticLogicUnit arithmaticLogicUnit) {
		this.arithmaticLogicUnit = arithmaticLogicUnit;
		
	}

	

	public void run(process.Process process) {
		System.out.println(process.getPcbId()+Constant.run);
		Constant.eState = Constant.EState.running;	
			this.fetch();
			this.decode();
	}
	
	private void fetch() {
		this.memory.fetch();
		this.ir.setDate(this.mbr.getDate());

	}
	
	private void decode() {
		String addressOpcode = Integer.toString(this.ir.getDate());
		String tempAddress = addressOpcode.substring(Constant.zero,Constant.six);
		String tempOpcode = addressOpcode.substring(Constant.six,addressOpcode.length());
		
		this.address = Integer.parseInt(tempAddress);	
		if(!(tempOpcode.equals(Constant.nul))) {
			this.opcode = Integer.parseInt(tempOpcode);
		}
		
		if(this.address == Constant.two<<Constant.sixteen) { //sta	
			STA();
		}else if(this.address == Constant.three<<Constant.sixteen) {//ldi
			LDI();
		}else if(this.address == Constant.four<<Constant.sixteen) {//addi
			ADDI();
		}else if(this.address == Constant.five<<Constant.sixteen) {//sti
			LDA();
		}else if(this.address == Constant.six<<Constant.sixteen) {//halt
			STI();
		}else if(this.address == Constant.seven<<Constant.sixteen) {//cmp
			CMP();
		}else if(this.address == Constant.eight<<Constant.sixteen) {//jgz
			JGZ();
		}else if(this.address == Constant.nine<<Constant.sixteen) {//halt
			HALT();
		}else if(this.address == Constant.ten<<Constant.sixteen) {//prt
			PRT();
		}else if(this.address == Constant.eleven<<Constant.sixteen) {//sum
			SUM();
		}else if(this.address == Constant.twelve<<Constant.sixteen) {//sum
			AVG();
		}
	}
	
	public void AVG() {
		System.out.println(Constant.avg);
		System.out.println(Constant.avgData + Constant.space);
		System.out.println(Constant.avgInterrupt);
		this.arithmaticLogicUnit.avg();
		Constant.eInterruptFlag = Constant.EInterruptFlag.avg;
		Constant.eIODeviceState = Constant.EIODeviceState.eStart; 
		this.memory.setAvg(this.arithmaticLogicUnit.getAvg());
		this.pc.setDate(this.pc.getDate()+Constant.one);
		System.out.println(Constant.space);
	}
	private void PRT() {
		System.out.println(Constant.prtInterrupt);
		this.pc.setDate(this.pc.getDate()+Constant.one);
		Constant.eInterruptFlag = Constant.EInterruptFlag.print;
		Constant.eIODeviceState = Constant.EIODeviceState.eStart; 
		System.out.println(Constant.space);
	}
	private void STA() {
		System.out.println(Constant.sta);
		this.mar.setDate(this.opcode);
		System.out.println(Constant.mar + this.mar.getDate());
		this.pc.setDate(this.pc.getDate()+Constant.one);
		System.out.println(Constant.space);
	}

	private void LDI() {
		System.out.println("**LDI발생**");
		this.mbr.setDate(this.opcode);
		System.out.println(Constant.mbr + this.mbr.getDate());
		this.memory.store();
		System.out.println(Constant.left + this.mar.getDate() + Constant.right + this.mbr.getDate() +Constant.dataStore);
		this.pc.setDate(this.pc.getDate()+Constant.one);
		System.out.println(Constant.space);
	}
	
	private void LDA() {
		System.out.println(Constant.lda);
		this.mar.setDate(this.opcode);
		this.ac.setDate(this.memory.getStore()[this.mar.getDate()]);
		System.out.println(this.mar.getDate() + Constant.addressStoreToAc);
		this.pc.setDate(this.pc.getDate()+Constant.one);
		System.out.println(Constant.space);
	}
	
	private void SUM() {
		System.out.println(Constant.sum);
		this.arithmaticLogicUnit.sum();
		System.out.println(Constant.ac + this.arithmaticLogicUnit.getSum());
		//System.out.println(this.ac.getDate());
		this.pc.setDate(this.pc.getDate()+Constant.one);
		this.pc.setDate(this.pc.getDate()+this.arithmaticLogicUnit.cmp());
		System.out.println(Constant.space);
	
	}
	

	private void ADDI() {
		System.out.println(Constant.addi);
		this.arithmaticLogicUnit.add(this.opcode);	
		System.out.println(Constant.acData + this.ac.getDate());
		this.pc.setDate(this.pc.getDate()+Constant.one);	
		System.out.println(Constant.space);
	}
	
	private void STI() {
		System.out.println(Constant.sti);
		this.mbr.setDate(this.ac.getDate());
		this.memory.store();
		System.out.println(Constant.left + this.mar.getDate() + Constant.right + Constant.acStore);
		this.pc.setDate(this.pc.getDate() + Constant.one);
		System.out.println(Constant.space);
	}
	
	private void CMP() {
		System.out.println(Constant.cmp);
		
		if(this.memory.getStore()[this.opcode]> this.memory.getStore()[this.mar.getDate()]) {
			// opcode 0번지 10 이랑 mar 8 번지 값 ac랑 비교
			System.out.println(this.memory.getStore()[this.opcode] + Constant.leftBig + this.memory.getStore()[this.mar.getDate()]);
			System.out.println(Constant.yet + this.memory.getStore()[this.opcode] + Constant.moreSmall);
			this.pc.setDate(this.pc.getDate()+Constant.one);
		}else {
			System.out.println(this.memory.getStore()[this.opcode] + Constant.equal+ this.memory.getStore()[this.mar.getDate()]);
			System.out.println(this.memory.getStore()[this.opcode] + Constant.same);
			this.pc.setDate(this.pc.getDate()+Constant.two);
			
		}
		System.out.println(Constant.space);
	}
	
	private void JGZ() {
		System.out.println(Constant.jgz);
		this.pc.setDate(this.opcode);
		System.out.println(Constant.space);
	}
	private void HALT() {
		System.out.println(Constant.halt);
		System.out.println(Constant.processEnd);
		System.out.println(Constant.space);
		Constant.eState = Constant.EState.halt;	
	}

}
