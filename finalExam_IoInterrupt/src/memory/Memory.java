
package memory;




import constant.Constant;
import cpu.Register;

public class Memory {
	
	private double avg;
	private int buffer[];
	private int store[];
	private String interrupt;
	private Register mar, mbr, pc;
	private int pcbID;

	public String getInterrupt() {
		return interrupt;
	}

	public void setInterrupt(String interrupt) {
		this.interrupt = interrupt;
	}

	public void connect(Register mar, Register mbr, Register pc) {
		this.mar = mar;
		this.mbr = mbr;
		this.pc = pc;

	}
	

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

	public Memory() {
		this.buffer = new int[Constant.oneHun];
		this.store = new int[Constant.oneHun];
		
	}

	public void fetch() {
		this.mbr.setDate(this.buffer[this.pc.getDate()]);
	}

	public void store() {
		this.store[this.mar.getDate()] = this.mbr.getDate();


	}

	public int[] getStore() {
		return store;
	}



	public void get(int[] codes) {
		this.buffer = codes;

	}



	public void DMA() {
		if(Constant.eIODeviceState == Constant.EIODeviceState.eRunning){
			if(System.nanoTime()> Constant.ISTARTTIME + Constant.ITIMESLICE) {
				Constant.eIODeviceState = Constant.EIODeviceState.eTerminated;
			}

		}

	}

	public void setInterruptPcbProcess(int pcbId) {
		this.pcbID = pcbId;
		
	}

	public int getInterruptPcbProcess() {
		return this.pcbID;
	}



}
