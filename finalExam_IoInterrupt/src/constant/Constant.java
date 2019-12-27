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
	public static final String nowAC ="--------------------����ac��: ";
	public static final String zzz = "-------------------";
	public static final String ACavg = "-------------------ac���: ";
	public static final String processInterruptTerminated = "�� process Interrupt����--------------";

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
	public static final String mar = "mar��: ";
	
	//mbr
	public static final String mbr = "mbr��: ";
	
	//run
	public static final String run = "�� process";
	
	//avg
	public static final String avg = "**AVG�߻�**";
	public static  final String avgData = "ac / 20";
	public static final  String avgInterrupt = "***************print(avg) Interrupt�߻�******************";
	
	//prt
	public static final String prtInterrupt = "***************PRT Interrupt�߻�******************";
	
	//sta
	public static final String sta = "**STA�߻�**";
	
	//ldi
	public static final String dataStore = "�� ����";	
	public static final String left = "[";
	public static final String right = "]������  --> ";
	
	//lda
	public static final String lda = "**LDA�߻�**";
	public static final String addressStoreToAc = "���� �ּ� �� ==> ac�� ����";
	
	//sum
	public static final String sum = "**SUM�߻�**";
	public static final String ac = "ac + ";
	
	//addi
	public static final String addi = "**ADDI�߻�**";
	public static final String acData ="ac�� ��: ";
	
	//sti
	public static final String sti = "**STI�߻�**";
	public static final String acStore = "ac�� ����";
	
	//cmp
	public static final String cmp = "**CMP�߻�**";
	public static final String leftBig = " > "; 
	public static final String yet = "���� ";
	public static final String moreSmall = "���� �۴�.";
	public static final String equal = " = ";
	public static final String same = "�� ���� ���� ����.";
	
	//jgz
	public static final String jgz = "**JGZ�߻�**";
	
	//hlt
	public static final String halt = "**HALT�߻�**";
	public static final String processEnd = "--------------process ����---------------";
	
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
