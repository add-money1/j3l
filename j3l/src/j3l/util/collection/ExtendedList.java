package j3l.util.collection;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

import j3l.util.collection.interfaces.IExtendedList;

/**
 * <p></p>
 * 
 * @since JDK 1.8
 * @version 2015.12.02_0
 * @author Johannes B. Latzel
 */
public class ExtendedList<T> extends ExtendedCollection<T> implements IExtendedList<T> {
	
	
	/**
	 * <p></p>
	 */
	private final List<T> list;
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public ExtendedList(List<T> list) {
		super(list);
		this.list = list;
	}
	
	
	/* (non-Javadoc)
	 * @see java.util.List#addAll(int, java.util.Collection)
	 */
	@Override public boolean addAll(int index, Collection<? extends T> c) {
		return list.addAll(index, c);
	}
	
	
	/* (non-Javadoc)
	 * @see java.util.List#get(int)
	 */
	@Override public T get(int index) {
		return list.get(index);
	}
	
	
	/* (non-Javadoc)
	 * @see java.util.List#set(int, java.lang.Object)
	 */
	@Override public T set(int index, T element) {
		return list.set(index, element);
	}
	
	
	/* (non-Javadoc)
	 * @see java.util.List#add(int, java.lang.Object)
	 */
	@Override public void add(int index, T element) {
		list.add(index, element);
	}
	
	
	/* (non-Javadoc)
	 * @see java.util.List#remove(int)
	 */
	@Override public T remove(int index) {
		return list.remove(index);
	}
	
	
	/* (non-Javadoc)
	 * @see java.util.List#indexOf(java.lang.Object)
	 */
	@Override public int indexOf(Object o) {
		return list.indexOf(o);
	}
	
	
	/* (non-Javadoc)
	 * @see java.util.List#lastIndexOf(java.lang.Object)
	 */
	@Override public int lastIndexOf(Object o) {
		return list.lastIndexOf(o);
	}
	
	
	/* (non-Javadoc)
	 * @see java.util.List#listIterator()
	 */
	@Override public ListIterator<T> listIterator() {
		return list.listIterator();
	}
	
	
	/* (non-Javadoc)
	 * @see java.util.List#listIterator(int)
	 */
	@Override public ListIterator<T> listIterator(int index) {
		return list.listIterator(index);
	}
	
	
	/* (non-Javadoc)
	 * @see java.util.List#subList(int, int)
	 */
	@Override public List<T> subList(int fromIndex, int toIndex) {
		return list.subList(fromIndex, toIndex);
	}

}
