package java_interview.ch10;

import java.util.LinkedList;

// front는 큐에서 가장 처음에 추가된 숫자, 즉 링크드 리스트에서 맨 뒤
// rear는 큐에서 가장 마지막에 추가된 숫자, 즉 링크드 리스트에서 맨 처음
public class MyCircularDeque {
	private LinkedList<Integer> deque;
	private int size;

	public MyCircularDeque(int k) {
		this.size = k;
		this.deque = new LinkedList<>();
	}

	public boolean insertFront(int value) {
		// Early return
		// else문 없이 return 문 만으로 분기를 종료시킴
		if (isFull()) {
			return false;
		}
		this.deque.addLast(value);
		return true;
	}

	public boolean insertLast(int value) {
		if (isFull()) {
			return false;
		}
		this.deque.addFirst(value);
		return true;
	}

	public boolean deleteFront() {
		if (isEmpty()) {
			return false;
		}
		this.deque.removeLast();
		return true;
	}

	public boolean deleteLast() {
		if (isEmpty()) {
			return false;
		}
		this.deque.removeFirst();
		return true;
	}

	public int getFront() {
		return isEmpty() ? -1 : this.deque.getLast();
	}

	public int getRear() {
		return isEmpty() ? -1 : this.deque.getFirst();
	}

	public boolean isEmpty() {
		return this.deque.size() == 0;
	}

	public boolean isFull() {
		return size == this.deque.size();
	}
}
// https://leetcode.com/problems/design-circular-deque/