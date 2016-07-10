package j3l.util.stream;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;

import j3l.util.ArgumentChecker;


/**
 * <p></p>
 * 
 * @since JDK 1.8
 * @version 2015.12.05_0
 * @author Johannes B. Latzel
 */
public final class StreamFactory {
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static <T> Stream<T> getStream(Collection<T> collection, StreamMode stream_mode) {
		switch( ArgumentChecker.checkForNull(stream_mode, "stream_mode") ) {
			case Sequential:
				return ArgumentChecker.checkForNull(collection, "collection").stream();
			case Parallel:
				return ArgumentChecker.checkForNull(collection, "collection").parallelStream();
			default:
				throw new IllegalArgumentException("Can not process StreamMode \"" + stream_mode.toString() + "\"!");
		}
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static <T> Stream<T> getStream(T[] array, StreamMode stream_mode) {
		Stream<T> stream = Arrays.stream(ArgumentChecker.checkForNull(array, "array"));
		switch( ArgumentChecker.checkForNull(stream_mode, "stream_mode") ) {
			case Sequential:
				return stream;
			case Parallel:
				return stream.parallel();
			default:
				throw new IllegalArgumentException("Can not process StreamMode \"" + stream_mode.toString() + "\"!");
		}
	}
	
}
