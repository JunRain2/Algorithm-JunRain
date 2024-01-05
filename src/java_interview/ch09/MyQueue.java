package java_interview.ch09;

import java.util.ArrayDeque;
import java.util.Deque;

public class MyQueue {
	Deque<Integer> input = new ArrayDeque<>();
	Deque<Integer> output = new ArrayDeque<>();

	public void push(int x) {
		input.push(x);
	}

	public int pop() {
		peek();
		return output.pop();
	}

	public int peek() {
		// output이 다 빌때 까지는 input의 값을 output에 옮길 필요가 없다.
		if (output.isEmpty()) {
			while (!input.isEmpty()) {
				output.push(input.pop());
			}
		}
		return output.peek();
	}

	public boolean empty() {
		return input.size() == 0 && output.size() == 0;
	}
}
