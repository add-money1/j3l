package j3l.util;

import j3l.util.check.ArgumentChecker;

/**
 * <p></p>
 * 
 * @since JDK 1.8
 * @version 2016.04.03_0
 * @author Johannes B. Latzel
 */
public final class RecursiveCollection<T> {
	
	
	/**
	 * <p></p>
	 */
	private RecursiveCollection<T> next_set;
	
	
	/**
	 * <p></p>
	 */
	private final Object[] object_array;
	
	
	/**
	 * <p></p>
	 */
	private final double growth_rate;
	
	
	/**
	 * <p></p>
	 */
	private int next_index;
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public RecursiveCollection() {
		this(10);
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public RecursiveCollection(int initial_capacity) {
		this(initial_capacity, 2d);
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public RecursiveCollection(int initial_capacity, double growth_rate) {
		object_array = new Object[ArgumentChecker.checkForBoundaries(initial_capacity, 1, Integer.MAX_VALUE, "initial_capacity")];
		this.growth_rate = ArgumentChecker.checkForBoundaries(growth_rate, 0, Double.MAX_VALUE, "growth_rate");
		if( growth_rate == 0 ) {
			throw new IllegalArgumentException("The growth_rate must not be equal to 0!");
		}
		next_set = null;
		next_index = 0;
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public boolean add(T t) {
		if( t == null ) {
			return false;
		}
		if( isFull() ) {
			if( next_set == null ) {
				next_set = new RecursiveCollection<>((int)(object_array.length * growth_rate), growth_rate);
			}
			return next_set.add(t);
		}
		else {
			object_array[next_index] = t;
			next_index++;
			return true;
		}
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public boolean remove(T t) {
		if( isEmpty() ) {
			return false;
		}
		for(int a=0;a<next_index;a++) {
			if( object_array[a].equals(t) ) {
				object_array[a] = removeLast();
				return true;
			}
		}
		return next_set.remove(t);
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	@SuppressWarnings("unchecked") public T removeFirst() {
		if( isEmpty() ) {
			throw new RuntimeException("There is no first element!");
		}
		T t;
		if( object_array.length == 1 ) {
			t = (T)object_array[0];
			object_array[0] = null;
			next_index--;
		}
		else {
			// cast is okay, because only instances of T can be added
			t = (T)object_array[0];
			object_array[0] = removeLast();
		}
		return t;
	}
		
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public T removeLast() {
		if( isFull() && next_set != null ) {
			return next_set.removeLast();
		}
		else {
			if( next_index > 0 ) {
				next_index--;
				// cast is okay, because only instances of T can be added
				@SuppressWarnings("unchecked") T t = (T)object_array[next_index];
				object_array[next_index] = null;
				return t;
			}
			else {
				throw new RuntimeException("There is no last element!");
			}
		}
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public boolean isEmpty() {
		return next_index == 0;
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public long getSize() {
		// 0L for cast to long (getSize() is long by definition, next_index is int)
		return next_index + (next_set != null ? next_set.getSize() : 0L);
	}
	
	
	/**
	 * <p>private, because this states only the fullness of this instance, but not the whole collection (since
	 * 	the collection can never run out of capacity as long there is enough RAM)</p>
	 *
	 * @param
	 * @return
	 */
	private boolean isFull() {
		return next_index == object_array.length;
	}
	
	
}