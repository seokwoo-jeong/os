package os;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import constant.Constant;
import process.Process;

public class Loader {
	private Process process;
	//association
	
	public Loader() {
		this.process = new Process(Constant.zero, null);
	}

	public Process load(String fileName) {
		int stackSegmentSize = Constant.zero;
		int[] codes = null;
		try {


			File file = new File(fileName);
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if(line.substring(Constant.zero,Constant.two).equals(Constant.slice)) {

				}else if(line.substring(Constant.zero,Constant.five).equals(Constant.code)) {
					codes = this.parseCode(scanner);
					
					
				}else if(line.substring(Constant.zero,Constant.six).equals(Constant.stack)) {
					stackSegmentSize = this.parseStack(scanner);

				}else if(line.isEmpty()) {

				}
			}
			this.process = new Process(stackSegmentSize, codes);
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return process;	
	}

	private int parseStack(Scanner scanner) {
		int stackSize = Constant.zero;
		Vector<String> letterVector = new Vector<>();
		String numbers = Constant.numbers;
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();

			if(line.substring(Constant.zero,Constant.two).equals(Constant.slice)) {
				if(line.substring(Constant.zero,Constant.four).equals(Constant.slice+Constant.slice)) { 
					
					break;
				}
			}else if(line.isEmpty()){

			}else {

				for(int i = Constant.zero; i<line.length(); i++) {
					if(numbers.contains(line.substring(i,i+Constant.one))){
						String letter = line.substring(i, i+Constant.one);
						letterVector.add(letter);
					}
				}
				stackSize = Integer.parseInt(letterVector.get(Constant.zero) + letterVector.get(Constant.one));
			}
		}
		return stackSize;
	}

	private int[] parseCode(Scanner scanner) {
		int count = Constant.zero;
		int[] tempCode = new int[Constant.oneHun];
		int instruction = Constant.zero;

		
		while(scanner.hasNext()) {
			String line = scanner.next();		
			if(line.equals(Constant.STA)){
				instruction = Constant.two;
			}else if(line.equals(Constant.LDI)){
				instruction = Constant.three;
			}else if(line.equals(Constant.ADDI)){
				instruction = Constant.four;
				
			}else if(line.equals(Constant.LDA)){
				instruction = Constant.five;
			}else if(line.equals(Constant.STI)){
				instruction = Constant.six;
			}else if(line.equals(Constant.CMP)){
				instruction = Constant.seven;
			}else if(line.equals(Constant.JGZ)){
				instruction = Constant.eight;
			}else if(line.equals(Constant.HALT)){
				instruction = Constant.nine;

			}else if(line.equals(Constant.PRT)) {
				instruction = Constant.ten;
			}else if(line.equals(Constant.SUM)) {
				instruction = Constant.eleven;
			}else if(line.equals(Constant.AVG)) {
				instruction = Constant.twelve;
			}
			int opcodeAddress = instruction<<Constant.sixteen;
			if(!line.equals(Constant.HALT) && !line.equals(Constant.PRT) && !line.equals(Constant.AVG)) {
				int address = scanner.nextInt();
				String tempOpcodeAddress = Integer.toString(opcodeAddress);
				tempOpcodeAddress += address;
				opcodeAddress = Integer.parseInt(tempOpcodeAddress);
			}else {
				opcodeAddress = instruction <<Constant.sixteen;
			}

			
			tempCode[count] = opcodeAddress;	 
			count++;
			

		}
		int[] codes = new int[count];
		codes = tempCode;
		return codes;
	}

}