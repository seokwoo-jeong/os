package os;

import cpu.CentralProcessingUnit;
import manager.FileManager;
import manager.MemoryManager;
import manager.UXManager;
import memory.Memory;

public class OperatingSystem {
	private UXManager uxManager;

	
	private MemoryManager memoryManager;
	private FileManager fileManager;
	
	
	//assciate
	private CentralProcessingUnit cpu;
	private Memory memory;

	public OperatingSystem() {
		this.uxManager = new UXManager();
		
		
		this.memoryManager = new MemoryManager();
		this.fileManager = new FileManager();
		
	}
	
	public void connect(CentralProcessingUnit cpu, Memory memory) {
		this.cpu = cpu;
		this.memory = memory;
		this.uxManager.connect(this.cpu, this.memory);
		
	}
	
	public void associate() {
		
		//this.processManager.associate(this.memoryManager, this.fileManager);
		
	}

	
	public void run() {
		//���콺 ����Ŭ���� �߻����� �߻�
		this.uxManager.run();

	}



	
}
