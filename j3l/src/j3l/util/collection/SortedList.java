package j3l.util.collection;

import java.util.List;
import java.util.function.Function;

import j3l.util.check.ArgumentChecker;
import j3l.util.collection.interfaces.Clearable;
import j3l.util.collection.interfaces.add.IAdd;
import j3l.util.collection.interfaces.remove.IRemove;


/**
 * <p></p>
 * 
 * @since JDK 1.8
 * @version 2015.12.14_0
 * @author Johannes B. Latzel
 */
public final class SortedList<R extends Comparable<R>, T> implements IAdd<T>, IRemove<T>, Clearable {
	
	
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
	
	
	/* (non-Javadoc)
	 * @see j3l.util.collection.interfaces.add.IBasicAdd#add(java.lang.Object)
	 */
	@Override public boolean add(T element) {
		
		if( element == null ) {
			return false;
		}
		
		synchronized( list ) {
			if( list.size() == 0 ) {
				return list.add(element);
			}
			else if( list.size() == 1 ) {
				if( attribute_function.apply(element).compareTo(attribute_function.apply(list.get(0))) > 0 ) {
					return list.add(element);
				}
				else {
					list.add(0, element);
					return true;
				}
			}
		}
		
		
		int index = -1;
		int min = 0;
		int max;
		int previous_index;
		T current_element;
		
		synchronized( list ) {
			
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
		
	}
	
	
	/* (non-Javadoc)
	 * @see j3l.util.collection.interfaces.remove.IBasicRemove#remove(java.lang.Object)
	 */
	@Override public boolean remove(T element) {
		synchronized( list ) {
			return list.remove(element);
		}
	}
	
	
	/* (non-Javadoc)
	 * @see j3l.util.collection.interfaces.Clearable#clear()
	 */
	@Override public void clear() {
		synchronized( list ) {
			list.clear();
		}
	}
	
}
