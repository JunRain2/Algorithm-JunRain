package java_interview.ch09;

import java.util.LinkedList;
import java.util.Queue;

// 큐를 이용한 스택 구현
public class MyStack {
	Queue<Integer> queue;

	public MyStack() {
		queue = new LinkedList<>();
	}

	public void push(int x) {
		queue.add(x);
		for (int i = 1; i < queue.size(); i++) {
			queue.offer(queue.poll());
		}

	}

	public int pop() {
		return queue.poll();
	}

	public int top() {
		return queue.peek();
	}

	public boolean empty() {
		return queue.isEmpty();
	}
}
// https://leetcode.com/problems/implement-stack-using-queues/