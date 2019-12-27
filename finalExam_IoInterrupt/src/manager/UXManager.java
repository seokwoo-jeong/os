package manager;





import constant.Constant;
import cpu.CentralProcessingUnit;
import memory.Memory;
import os.Loader;
import process.Process;

public class UXManager {
	private ProcessManager processManager;
	private Loader loader;
	private Memory memory;
	private CentralProcessingUnit cpu;
	
	public UXManager() {
		this.loader = new Loader();
		this.processManager = new ProcessManager();
		
	}

	public void associate(ProcessManager processManager) {
		this.processManager = processManager;	
	}
	
	public void connect(CentralProcessingUnit cpu, Memory memory) {
		this.cpu = cpu;
		this.memory = memory;
		this.processManager.connect(this.cpu, this.memory);
		
	}
	
	public void run() {
		String fileName =Constant.exe1;
		Process process = this.loader.load(fileName);
		
		String fileName2 = Constant.exe2;
		Process process2 = this.loader.load(fileName2);
		
		String fileName3 = Constant.exe3;
		Process process3 = this.loader.load(fileName3);
		
		
		
		this.processManager.inputProcessQueue(process, process2, process3);
		this.processManager.execute();
	}



	
}
