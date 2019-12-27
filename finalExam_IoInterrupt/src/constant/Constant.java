package constant;

public class Constant {
	
	public static EIODeviceState eIODeviceState;
	public static enum EIODeviceState{eIdle, eRunning, eTerminated, eError, eStart};
	
	
	public static enum EState {halt, running};
	public static EState eState;
	
	
	public static enum EInterruptFlag{print, read, load, none, avg};
	public static EInterruptFlag eInterruptFlag;
//////////////////////////////////////////////////////////////////////////////////////	
	//time
	public static long TIMESLICE = 500000; 
	public static long STARTTIME;
	public static long FINISHTIME;
	
	//interruptTime
	public static long ITIMESLICE = 300000; 
	public static long ISTARTTIME;
	public static long IFINISHTIME;
	
	// space
	public static final String space= "\n";
	
//////////////////////////////////////////////////////////////////////////////////////
	
	//processManager
	
	//processName
	public static final String process1 = "process1";
	public static final String process2 = "process2";
	public static final String process3 = "process3";
	//queue
	public static final String readyQ = "ready queue: ";
	public static final String waitQ = "wait queue: ";
	
	//IOFINISHED
	public static final String nowAC ="--------------------현재ac값: ";
	public static final String zzz = "-------------------";
	public static final String ACavg = "-------------------ac평균: ";
	public static final String processInterruptTerminated = "번 process Interrupt종료--------------";

	//contextSwitch
	public static final String contextSwitch = "!!!!!!!!!!!!!context Switch!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n";
	
//////////////////////////////////////////////////////////////////////////////////////
	//number from alu
	public static final int zero = 0;
	public static final int one = 1;
	public static final int two = 2;
	public static final int three = 3;
	public static final int four = 4;
	public static final int five = 5;
	public static final int six = 6;
	public static final int seven = 7;
	public static final int eight = 8;
	public static final int nine = 9;
	public static final int ten = 10;
	public static final int eleven = 11;
	public static final int twelve = 12;
	public static final int sixteen = 16;
	public static final int twentyOne = 21;
	public static final int twenty = 20;
	public static final int oneHun = 100;
	public static final int fivemillion = 500000;
	
	public static final String nul = "";
//////////////////////////////////////////////////////////////////////////////////////
	
	//println from controlunit	
	//mar
	public static final String mar = "mar값: ";
	
	//mbr
	public static final String mbr = "mbr값: ";
	
	//run
	public static final String run = "번 process";
	
	//avg
	public static final String avg = "**AVG발생**";
	public static  final String avgData = "ac / 20";
	public static final  String avgInterrupt = "***************print(avg) Interrupt발생******************";
	
	//prt
	public static final String prtInterrupt = "***************PRT Interrupt발생******************";
	
	//sta
	public static final String sta = "**STA발생**";
	
	//ldi
	public static final String dataStore = "값 저장";	
	public static final String left = "[";
	public static final String right = "]번지에  --> ";
	
	//lda
	public static final String lda = "**LDA발생**";
	public static final String addressStoreToAc = "번지 주소 값 ==> ac에 저장";
	
	//sum
	public static final String sum = "**SUM발생**";
	public static final String ac = "ac + ";
	
	//addi
	public static final String addi = "**ADDI발생**";
	public static final String acData ="ac의 값: ";
	
	//sti
	public static final String sti = "**STI발생**";
	public static final String acStore = "ac값 저장";
	
	//cmp
	public static final String cmp = "**CMP발생**";
	public static final String leftBig = " > "; 
	public static final String yet = "아직 ";
	public static final String moreSmall = "보다 작다.";
	public static final String equal = " = ";
	public static final String same = "와 값이 같아 졌다.";
	
	//jgz
	public static final String jgz = "**JGZ발생**";
	
	//hlt
	public static final String halt = "**HALT발생**";
	public static final String processEnd = "--------------process 종료---------------";
	
//////////////////////////////////////////////////////////////////////////////////////
	//uxManager
	public static final String exe1 ="exe/p1.ppp";
	public static final String exe2 = "exe/p2.ppp";
	public static final String exe3 = "exe/p3.ppp";
//////////////////////////////////////////////////////////////////////////////////////
	//loader
	public static final String slice ="//";
	public static final String code = ".code";
	public static final String stack = ".stack";
	public static final String numbers = "0123456789";
	
	public static final String STA = "sta";
	public static final String LDI = "ldi";
	public static final String ADDI = "addi";
	public static final String LDA = "lda";
	public static final String STI = "sti";
	public static final String CMP = "cmp";
	public static final String JGZ = "jgz";
	public static final String HALT = "halt";
	public static final String PRT = "prt";
	public static final String SUM = "sum";
	public static final String AVG = "avg";
	
}
