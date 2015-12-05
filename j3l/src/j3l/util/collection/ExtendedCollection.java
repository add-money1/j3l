package j3l.util.collection;

import java.util.Collection;
import java.util.Iterator;

import j3l.util.check.ArgumentChecker;
import j3l.util.collection.interfaces.IExtendedCollection;


/**
 * <p></p>
 * 
 * @since JDK 1.8
 * @version 2015.12.03_0
 * @author Johannes B. Latzel
 */
public class ExtendedCollection<T> implements IExtendedCollection<T> {
	
	
	/**
	 * <p></p>
	 */
	private final Collection<T> collection;
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public ExtendedCollection(Collection<T> collection) {
		ArgumentChecker.checkForNull(collection, "collection");
		this.collection = collection;
	}
	
	
	/* (non-Javadoc)
	 * @see java.util.Collection#size()
	 */
	@Override public int size() {
		return collection.size();
	}
	
	
	/* (non-Javadoc)
	 * @see java.util.Collection#isEmpty()
	 */
	@Override public boolean isEmpty() {
		return collection.isEmpty();
	}
	
	
	/* (non-Javadoc)
	 * @see java.util.Collection#contains(java.lang.Object)
	 */
	@Override public boolean contains(Object o) {
		return collection.contains(o);
	}
	
	
	/* (non-Javadoc)
	 * @see java.util.Collection#iterator()
	 */
	@Override public Iterator<T> iterator() {
		return collection.iterator();
	}
	
	
	/* (non-Javadoc)
	 * @see java.util.Collection#toArray()
	 */
	@Override public Object[] toArray() {
		return collection.toArray();
	}
	
	
	/* (non-Javadoc)
	 * @see java.util.Collection#toArray(java.lang.Object[])
	 */
	@SuppressWarnings("hiding") 
	@Override public <T> T[] toArray(T[] a) {
		return collection.toArray(a);
	}
	
	
	/* (non-Javadoc)
	 * @see java.util.Collection#add(java.lang.Object)
	 */
	@Override public boolean add(T e) {
		return collection.add(e);
	}
	
	
	/* (non-Javadoc)
	 * @see java.util.Collection#remove(java.lang.Object)
	 */
	@Override public boolean remove(Object o) {
		return collection.remove(o);
	}
	
	
	/* (non-Javadoc)
	 * @see java.util.Collection#containsAll(java.util.Collection)
	 */
	@Override public boolean containsAll(Collection<?> c) {
		return collection.containsAll(c);
	}
	
	
	/* (non-Javadoc)
	 * @see java.util.Collection#addAll(java.util.Collection)
	 */
	@Override public boolean addAll(Collection<? extends T> c) {
		return collection.addAll(c);
	}
	
	
	/* (non-Javadoc)
	 * @see java.util.Collection#removeAll(java.util.Collection)
	 */
	@Override public boolean removeAll(Collection<?> c) {
		return collection.removeAll(c);
	}
	
	
	/* (non-Javadoc)
	 * @see java.util.Collection#retainAll(java.util.Collection)
	 */
	@Override public boolean retainAll(Collection<?> c) {
		return collection.retainAll(c);
	}
	
	
	/* (non-Javadoc)
	 * @see java.util.Collection#clear()
	 */
	@Override public void clear() {
		collection.clear();
	}
	
}
