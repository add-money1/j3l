package j3l.util.collection.interfaces;

import java.util.Collection;
import java.util.stream.Stream;

import j3l.util.collection.interfaces.add.IExtendedAdd;
import j3l.util.collection.interfaces.remove.IExtendedRemove;
import j3l.util.stream.IStream;
import j3l.util.stream.StreamFactory;
import j3l.util.stream.StreamMode;

/**
 * <p></p>
 * 
 * @since JDK 1.8
 * @version 2015.12.03_0
 * @author Johannes B. Latzel
 */
public interface IExtendedCollection<T> extends Collection<T>, IExtendedAdd<T>, IExtendedRemove<T>, IStream<T> {
	
	
	/*
	 * (non-Javadoc)
	 * @see j3l.util.collection.IExtendedAdd#addAll(java.util.stream.Stream)
	 */
	@Override default void addAll(Stream<? extends T> stream) {
		 stream.forEach(element -> add(element));
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see j3l.util.collection.IExtendedAdd#addAll(java.lang.Object[])
	 */
	@Override default boolean addAll(T[] array) {
		 boolean has_changed = false;
		 for( T element : array ) {
			 if( !has_changed ) {
				 has_changed = add(element);
			 }
			 else {
				 add(element);
			 }
		 }
		 return has_changed;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see j3l.util.collection.IExtendedRemove#removeAll(java.util.stream.Stream)
	 */
	@Override default void removeAll(Stream<? extends T> stream) {
		stream.forEach(element -> remove(element));
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see j3l.util.collection.IExtendedRemove#removeAll(java.lang.Object[])
	 */
	@Override default boolean removeAll(T[] array) {
		boolean has_changed = false;
		for (T element : array) {
			if (!has_changed) {
				has_changed = remove(element);
			} else {
				remove(element);
			}
		}
		return has_changed;
	}
	
	
	/* (non-Javadoc)
	 * @see j3l.util.stream.IStream#getStream(j3l.util.interfaces.StreamMode)
	 */
	@Override default public Stream<T> getStream(StreamMode stream_mode) {
		return StreamFactory.getStream(this, stream_mode);
	}
	
}
