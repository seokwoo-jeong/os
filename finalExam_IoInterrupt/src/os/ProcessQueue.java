package os;

import java.util.LinkedList;
import java.util.Queue;

import process.Process;

public class ProcessQueue {
	private Queue<Process> queue;

	public ProcessQueue() {
		this.queue = new LinkedList<Process>();
	}
	public void enqueue(Process process) {
		this.queue.offer(process);
		
	}

	public Queue<Process> getQueue() {
		return queue;
	}
	public void setQueue(Queue<Process> queue) {
		this.queue = queue;
	}
	public Process dequeue() {
		// TODO Auto-generated method stub
		return this.queue.peek();
	}
	
	public Process remove() {
		return this.queue.poll();
	}
	
	
	


}
