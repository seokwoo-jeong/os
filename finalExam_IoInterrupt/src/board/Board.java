package board;
import java.io.FileNotFoundException;

import cpu.CentralProcessingUnit;
import memory.Memory;
import os.OperatingSystem;

public class Board {
	private CentralProcessingUnit cpu;
	private Memory memory;
	private OperatingSystem operatingSystem;
	
	
	public Board() throws FileNotFoundException {
		this.operatingSystem = new OperatingSystem();
		this.cpu = new CentralProcessingUnit();
		this.memory = new Memory();
		
	
		this.cpu.connect(this.memory);
		this.operatingSystem.connect(this.cpu, this.memory);
		
	}


	public void run() {
		this.operatingSystem.run();
		
	}
	
	
	





}
