package Deques;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

	private Node first, last;
	private int size;

	private class Node {
		Item value;
		Node next;
		Node prev;
	} 

	public Deque() { // construct an empty deque
		size = 0;
		first = null;
		last = null;
	}

	public boolean isEmpty() { // is the deque empty?
		return first == null && last == null;
	}

	public int size() { // return the number of items on the deque

		return size;
	}

	public void addFirst(Item item) { // add the item to the front

		if (item == null) {
			throw new IllegalArgumentException();
		}
		
		Node prevFirst = first;
		
		first = new Node();
		first.value = item;
		size++;
		
		if (prevFirst!=null) {
			first.next = prevFirst;
			prevFirst.prev = first;
		}
		else {
			last = first;
		}
		

	}

	public void addLast(Item item) { // add the item to the end

		if (item == null) {
			throw new IllegalArgumentException();
		}
		
		Node prevLast = last;
		
		last = new Node();
		last.value = item;
		size++;
		
		if (prevLast!=null) {
			last.prev = prevLast;
			prevLast.next = last;
		}
		else {
			first = last;
		}
	}

	public Item removeFirst() { // remove and return the item from the front

		if (isEmpty()) {
			throw new NoSuchElementException("Deque is empty!");
		}
		
		Node removedFirst = first;
		
		if (size==1) {
			last = first = null;
		}
		
		else {
			first = first.next;
			first.prev = null;
		}
		
		size--;
		return removedFirst.value;
		
	}

	public Item removeLast() { // remove and return the item from the end

		if (isEmpty()) {
			throw new NoSuchElementException("Deque is empty!");
		}
		//System.out.println(last);
		Node removedLast = last;
		

		if (size==1) {
			last = first = null;
		}
		
		else {
			last = last.prev;
			last.next = null;
		}
		
		size--;
		
		return removedLast.value;
		
	}

	@Override
	public Iterator<Item> iterator() {
		return new DequeIterator(); // return an iterator over items in order
										// from front to end

	}

	private class DequeIterator implements Iterator<Item>{

		
		Node current = first;
		
		@Override
		public boolean hasNext() {
			return current!=null;
		}

		@Override
		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			
			Item curr = current.value;
			current = current.next;
			return curr;
		}
		
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}
	
	public static void main(String[] args) { // unit testing (optional)

		Deque<String> deque = new Deque<>();
		deque.addFirst("B");
		deque.removeFirst();
		deque.addFirst("Zu");
		deque.addLast("K");
		
		for (String item : deque) {
			System.out.println(item);
			
		}
	}
}








