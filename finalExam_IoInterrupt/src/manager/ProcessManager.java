package manager;

import constant.Constant;
import cpu.CentralProcessingUnit;
import cpu.ControlUnit;
import manager.ProcessManager.EInterrupt;
import memory.Memory;
import os.ProcessQueue;
import process.Process;


//프로세스 생성,제거, 자원관리
public class ProcessManager {
public enum EInterrupt{eNone, eIOStarted, eTimeOut, eIOFinished, eTerminated};
	private EInterrupt eInterrupt;

	private ProcessQueue readyQueue;
	private ProcessQueue ioQueue;
	private ProcessQueue tempQueue; // 순번 뒤집을때 임시 저장소
	
	//associate
	private MemoryManager memoryManager;
	private FileManager fileManager;
	private CentralProcessingUnit cpu;
	private Memory memory;
	
	//work variable
	private Process currentProcess;
	
	public ProcessManager() {
		this.eInterrupt = EInterrupt.eNone;
		this.readyQueue = new ProcessQueue();
		this.ioQueue = new ProcessQueue();
		this.tempQueue = new ProcessQueue();	
	}

	public void associate(MemoryManager memoryManager, FileManager fileManager ) {
		this.fileManager = fileManager;
		this.memoryManager = memoryManager;
		
	}

	public void connect(CentralProcessingUnit cpu, Memory memory) {
		this.cpu = cpu;
		this.memory = memory;
		this.cpu.connect(memory);
		
	}
	public void inputProcessQueue(Process process, Process process2, Process process3) {
		this.readyQueue.enqueue(process);
		process.setPcbId(Constant.one);
		process.setPcbName(Constant.process1);
		this.readyQueue.enqueue(process2);
		process2.setPcbId(Constant.two);
		process2.setPcbName(Constant.process2);
		this.readyQueue.enqueue(process3);
		process3.setPcbId(Constant.three);
		process3.setPcbName(Constant.process3);	
	}
	
	public void execute() {
		this.currentProcess = this.readyQueue.dequeue();
		this.memory.get(currentProcess.getCodeSegment());
		this.memory.DMA();
		System.out.println(Constant.readyQ + this.readyQueue.getQueue());
		System.out.println(Constant.waitQ + this.ioQueue.getQueue()+Constant.space);
		run();
	}

	//루핀 빠지는법 : 타입슬라이스 끝나던가, 프로그램 끝나던가, 중간에 인터럽트 들어오던가 (3가지)
	public void run() {
		while(this.eInterrupt == EInterrupt.eNone) {	
			Constant.STARTTIME = System.nanoTime();
			this.cpu.run(this.currentProcess);
			Constant.FINISHTIME = System.nanoTime();
			Constant.TIMESLICE = Constant.TIMESLICE - (Constant.FINISHTIME - Constant.STARTTIME);
			checkInterrupt();
		}
	}
	
	public void checkInterrupt() {
		if(Constant.eState == Constant.EState.halt) { //process종료시
			this.eInterrupt = EInterrupt.eTerminated;
		}
		else if(Constant.TIMESLICE < 0) { //timeslice 만료시
			resetTimer();
			this.eInterrupt = EInterrupt.eTimeOut;	
		}
		
		if(Constant.eIODeviceState == Constant.EIODeviceState.eStart) { //interrupt발생시
			Constant.eIODeviceState = Constant.EIODeviceState.eRunning;
			Constant.ISTARTTIME= System.nanoTime();
			this.eInterrupt = EInterrupt.eIOStarted;
		}else if(Constant.eIODeviceState == Constant.EIODeviceState.eTerminated) { //DMA에서 intterupt종료시
			Constant.eIODeviceState = Constant.EIODeviceState.eIdle;
			
			this.eInterrupt = EInterrupt.eIOFinished;
		}
		performInterrupt();
	}
	
	public void resetTimer() {
		Constant.TIMESLICE = Constant.fivemillion;
	}
	
	public void performInterrupt() {
		switch(this.eInterrupt) {
		case eTimeOut:
			storePcb();
			readyQuTOreadyQu();
			contextSwitch();
			break;
		case eIOStarted:
			this.memory.setInterruptPcbProcess(this.currentProcess.getPcbId());
			storePcb();
			this.ioQueue.enqueue(this.currentProcess); //waitqueue에 집어넣음
			this.readyQueue.remove(); // ready queueue에서 삭제
			contextSwitch();

			break;
		case eIOFinished:
			storePcb();
			readyQuTOreadyQu();
			waitQuTOreadyQu();
			if(Constant.eInterruptFlag == Constant.EInterruptFlag.print) {
				System.out.println(Constant.nowAC + this.ioQueue.dequeue().getPcb().getAc().getDate()+ Constant.zzz);
			}else if(Constant.eInterruptFlag == Constant.EInterruptFlag.avg) {
				System.out.println(Constant.ACavg + this.memory.getAvg()+Constant.zzz);
			}
			
			System.out.println(Constant.zzz+this.memory.getInterruptPcbProcess()+ Constant.processInterruptTerminated);
			
			this.readyQueue.enqueue(this.ioQueue.dequeue());
			this.ioQueue.remove();
			
			contextSwitch();
			break;
		case eTerminated:
			this.readyQueue.remove();
			contextSwitch();
			break;
		default:
			break;
			
		}
		
	}
	
	private void waitQuTOreadyQu() { ///wait qu에 1,2존재시 1이아니라 2가 먼저끝났을 때 해결 함수
		for(int i=Constant.zero; i<this.ioQueue.getQueue().size(); i++) {
			if(this.memory.getInterruptPcbProcess() != this.ioQueue.dequeue().getPcbId()) {
				this.tempQueue.enqueue(this.ioQueue.dequeue());
				this.ioQueue.remove();
				this.ioQueue.enqueue(this.tempQueue.dequeue());
				this.tempQueue.remove();
				}

		}
		
	}
	
	public void readyQuTOreadyQu() {
		this.tempQueue.enqueue(currentProcess);
		this.readyQueue.remove();
		
		this.readyQueue.enqueue(this.tempQueue.dequeue());
		this.tempQueue.remove();
	}


	private void storePcb() {
		//pcb에 돌리고있던 process의 주소들 전부 저장
		this.currentProcess.getPcb().getPc().setDate(this.cpu.getPc().getDate());
		this.currentProcess.getPcb().getSp().setDate(this.cpu.getSp().getDate());
		this.currentProcess.getPcb().getMar().setDate(this.cpu.getMar().getDate());
		this.currentProcess.getPcb().getMbr().setDate(this.cpu.getMbr().getDate());
		this.currentProcess.getPcb().getIr().setDate(this.cpu.getIr().getDate());
		this.currentProcess.getPcb().getAc().setDate(this.cpu.getAc().getDate());
		this.currentProcess.getPcb().getStatus().setDate(this.cpu.getStatus().getDate());
		
	}
	
	private void pcbTOcpu() {
		this.cpu.getPc().setDate(this.currentProcess.getPcb().getPc().getDate());
		this.cpu.getSp().setDate(this.currentProcess.getPcb().getSp().getDate());
		this.cpu.getMar().setDate(this.currentProcess.getPcb().getMar().getDate());
		this.cpu.getMbr().setDate(this.currentProcess.getPcb().getMbr().getDate());
		this.cpu.getIr().setDate(this.currentProcess.getPcb().getIr().getDate());
		this.cpu.getAc().setDate(this.currentProcess.getPcb().getAc().getDate());
		this.cpu.getStatus().setDate(this.currentProcess.getPcb().getStatus().getDate());
	}

	private void contextSwitch() {
		if(this.readyQueue.getQueue().size() != 0) {
			System.out.println(Constant.contextSwitch);
			this.currentProcess = this.readyQueue.dequeue();

			pcbTOcpu();

			this.eInterrupt = EInterrupt.eNone;
			execute();
		}else {// ready queue안에 아무것도 없는데 wait queue에 아직 process 존재시
			if(this.ioQueue.getQueue().size() !=Constant.zero) {
				this.readyQueue.enqueue(this.ioQueue.dequeue());
				this.ioQueue.remove();
				pcbTOcpu();
				this.eInterrupt = EInterrupt.eNone;
				
				execute();
			}
		}

	}

}
