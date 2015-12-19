package j3l.util.collection;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import j3l.util.check.ArgumentChecker;
import j3l.util.collection.interfaces.IClear;
import j3l.util.collection.interfaces.add.IAdd;
import j3l.util.collection.interfaces.remove.IRemove;
import j3l.util.interfaces.ComparisonType;
import j3l.util.stream.IStream;
import j3l.util.stream.StreamFactory;
import j3l.util.stream.StreamFilter;
import j3l.util.stream.StreamMode;


/**
 * <p></p>
 * 
 * @since JDK 1.8
 * @version 2015.12.16_0
 * @author Johannes B. Latzel
 */
public final class SortedList<R extends Comparable<R>, T> implements IAdd<T>, IRemove<T>, IClear, IStream<T> {
	
	
	/**
	 * <p></p>
	 */
	private final List<T> list;
	
	
	/**
	 * <p></p>
	 */
	private final Function<T, R> attribute_function;
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public SortedList(ListType base_list_type, Function<T, R> attribute_function) {
		this.attribute_function = ArgumentChecker.checkForNull(attribute_function, "attribute_function");
		list = ListFactory.<T>createList(base_list_type);
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public T get(int index) {
		return list.get(index);
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public T getLast() {
		if( !isEmpty() ) {
			return list.get( list.size() - 1 );
		}
		else {
			return null;
		}
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public T getFirst() {
		if( !isEmpty() ) {
			return list.get(0);
		}
		else {
			return null;
		}
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public T getFirst(ComparisonType comparison_type, R value) {
		return getPossibleElements(comparison_type, value).findFirst().orElse(null);
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public T getAny(ComparisonType comparison_type, R value) {
		return getPossibleElements(comparison_type, value).findAny().orElse(null);
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	private Stream<T> getPossibleElements(ComparisonType comparison_type, R value) {
		ArgumentChecker.checkForNull(comparison_type, "comparison_type");
		ArgumentChecker.checkForNull(value, "value");
		return list.parallelStream().filter(StreamFilter::filterNull).filter(element -> {
			switch(comparison_type) {
				case EqualTo:
					return attribute_function.apply(element).compareTo(value) == 0;
				case GreaterThan:
					return attribute_function.apply(element).compareTo(value) > 0;
				case SmallerThan:
					return attribute_function.apply(element).compareTo(value) < 0;
				case GreaterThanOrEqualTo:
					return attribute_function.apply(element).compareTo(value) >= 0;
				case SmallerThanOrEqualTo:
					return attribute_function.apply(element).compareTo(value) <= 0;
				case UnequalTo:
					return attribute_function.apply(element).compareTo(value) != 0;
				default:
					return false;
			}
		});
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public int size() {
		return list.size();
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public boolean contains(T element) {
		return list.contains(element);
	}
	
	
	/* (non-Javadoc)
	 * @see j3l.util.collection.interfaces.add.IBasicAdd#add(java.lang.Object)
	 */
	@Override public boolean add(T element) {
		
		if( element == null ) {
			return false;
		}
		
		if( list.size() == 0 ) {
			return list.add(element);
		}
		else if( list.size() == 1 ) {
			if( attribute_function.apply(element).compareTo(attribute_function.apply(list.get(0))) > 0 ) {
				return list.add(element);
			}
			else {
				list.add(1, element);
				return true;
			}
		}
		
		
		int index = -1;
		int min = 0;
		int max;
		int previous_index;
		T current_element;
		
		max = list.size();
		
		do {
			
			previous_index = index;
			index = min + ( (max - min) / 2 );
			
			if( previous_index >= 0 && previous_index == index ) {
				index = min + 1;
				break;
			}
			
			current_element = list.get(index);
			
			if( attribute_function.apply(current_element).compareTo(attribute_function.apply(element)) == 0 ) {
				break;
			} 
			else if( attribute_function.apply(current_element).compareTo(attribute_function.apply(element)) > 0 ) {
				min = index;
			}
			else {
				max = index;
			}
			
		}
		while( min != max );
		
		list.add(index, element);
		return true;
		
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public T removeFirst(ComparisonType comparison_type, R value) {
		T element = getPossibleElements(comparison_type, value).findFirst().orElse(null);
		if( element != null ) {
			list.remove(element);
		}
		return element;
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public T removeAny(ComparisonType comparison_type, R value) {
		T element = getPossibleElements(comparison_type, value).findAny().orElse(null);
		if( element != null ) {
			list.remove(element);
		}
		return element;
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public T removeLast() {
		if( !isEmpty() ) {
			return list.remove( list.size() - 1 );
		}
		else {
			return null;
		}
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public T removeFirst() {
		if( !isEmpty() ) {
			return list.remove(0);
		}
		else {
			return null;
		}
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public T remove(int index) {
		return list.remove(index);
	}
	
	
	/* (non-Javadoc)
	 * @see j3l.util.collection.interfaces.remove.IBasicRemove#remove(java.lang.Object)
	 */
	@Override public boolean remove(T element) {
		return list.remove(element);
	}
	
	
	/* (non-Javadoc)
	 * @see j3l.util.collection.interfaces.IClear#clear()
	 */
	@Override public void clear() {
		list.clear();
	}
	
	
	/* (non-Javadoc)
	 * @see j3l.util.stream.IStream#getStream(j3l.util.stream.StreamMode)
	 */
	@Override public Stream<T> getStream(StreamMode stream_mode) {
		return StreamFactory.getStream(new LinkedList<>(list), stream_mode);
	}
	
}
