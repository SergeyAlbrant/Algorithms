package Deques;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private Item[] data;
	private int size;
	private int capacity;

	public RandomizedQueue() { // construct an empty randomized queue
		data = (Item[]) new Object[1];
		size = 0;
		capacity = 1;
	}

	public boolean isEmpty() { // is the randomized queue empty?
		return size == 0;
	}

	public int size() { // return the number of items on the randomized queue
		return size;
	}

	public void enqueue(Item item) { // add the item
		if (item == null) {
			throw new IllegalArgumentException();
		}

		if (size  == capacity) {
			resize(2 * capacity);
		}

		data[size++] = item;
	}

	private void resize(int newCapacity) {
		Item[] aux = (Item[]) new Object[newCapacity];
		for (int i = 0; i < size; i++) {
			aux[i] = data[i];
		}
		data = aux;
		capacity = newCapacity;
	}

	public Item dequeue() { // remove and return a random item
		if (size == 0) {
			throw new NoSuchElementException();
		}

		int randomIndex = StdRandom.uniform(0, size);

		Item item = data[randomIndex];

		data[randomIndex] = data[--size];

		if (size == capacity / 4) {
			resize(capacity / 2);
		}

		return item;

	}

	public Item sample() { // return a random item (but do not remove it)

		if (size() == 0) {
            throw new java.util.NoSuchElementException();
        }

		int randomIndex = StdRandom.uniform(0, size);

		return data[randomIndex];

	}

	public Iterator<Item> iterator() { // return an independent iterator over
										// items in random order

		return new RQIterator();
	}

	private class RQIterator implements Iterator<Item> {

		private int i = size;
		private int[] aux;
		
		public RQIterator() {

			
            aux = new int[size];
            for (int i = 0; i < size; i++) {
                aux[i] = i;
            }
            StdRandom.shuffle(aux); 
			
		}
		
		@Override
		public boolean hasNext() {
			return i > 0; 
		}

		@Override
		public Item next() {
			if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
			return data[aux[--i]];
		}
		
		public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }

	}

	public static void main(String[] args) { // unit testing (optional)

		RandomizedQueue<String> rq = new RandomizedQueue<String>();

        System.out.println("Is empty? " + rq.isEmpty());

        rq.enqueue("A");
        rq.enqueue("B");
        rq.enqueue("C");
        System.out.println("Size: " + rq.size());

        System.out.println("Removed item: " + rq.dequeue());



        System.out.println("New size " + rq.size());

        System.out.println("Sample: " + rq.sample());
        System.out.println("Sample: " + rq.sample());
        System.out.println("Sample: " + rq.sample());

        System.out.println("Iterator: ");
        for (String s : rq) {
            System.out.println(s);
        }

        System.out.println("Iterator: ");
        for (String s : rq) {
            System.out.println(s);
        }
		
	}
}