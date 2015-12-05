package j3l.util.collection.interfaces;

import java.util.List;
import java.util.stream.Stream;

import j3l.util.stream.IStream;
import j3l.util.stream.StreamMode;

/**
 * <p></p>
 * 
 * @since JDK 1.8
 * @version 2015.12.02_0
 * @author Johannes B. Latzel
 */
public interface IExtendedList<T> extends List<T>, IExtendedCollection<T>, IStream<T> {
	
	
	/*
	 * (non-Javadoc)
	 * @see j3l.util.collection.IStream#getStream(j3l.util.interfaces.StreamMode)
	 */
	default Stream<T> getStream(StreamMode stream_mode) {
		switch(stream_mode) {
			case Sequential:
				return stream();
			case Parallel:
				return parallelStream();
			default:
				throw new IllegalArgumentException("Can not process the stream_mode: \"" + stream_mode.toString() + "\"!");
		}
	}
	
}