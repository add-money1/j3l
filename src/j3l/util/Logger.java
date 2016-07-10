package j3l.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Flushable;
import java.io.IOException;


/**
 * <p></p>
 * 
 * @since JDK 1.8
 * @version 2016.07.10_0
 * @author Johannes B. Latzel
 */
public final class Logger implements Flushable, IClose<IOException> {
	
	
	/**
	 * <p></p>
	 */
	private BufferedWriter writer;
	
	
	/**
	 * <p></p>
	 */
	private ClosureState closure_state;
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 * @throws IOException 
	 */
	public Logger(File log_file) throws IOException {
		ArgumentChecker.checkForExistence(log_file, "log_file");
		closure_state = ClosureState.None;
		open();
		try {
			writer = new BufferedWriter(new FileWriter(log_file));
		}
		catch( IOException e ) {
			throw new IOException("Coudl not create the writer!", e);
		}
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public void log(String message) throws IOException {
		synchronized( writer ) {
			if( isOpen() ) {
				writer.write(message);
				writer.write(System.lineSeparator());
			}
		}
	}
	
	
	/* (non-Javadoc)
	 * @see java.io.Flushable#flush()
	 */
	@Override public void flush() throws IOException {
		synchronized( writer ) {
			if( !isClosed() ) {
				writer.flush();
			}
		}
	}
	
	
	/* (non-Javadoc)
	 * @see j3l.util.interfaces.IStateClosure#getClosureState()
	 */
	@Override public ClosureState getClosureState() {
		return closure_state;
	}
	
	
	/* (non-Javadoc)
	 * @see j3l.util.interfaces.IClose#open()
	 */
	@Override public void open() {
		synchronized( writer ) {
			if( !hasBeenOpened() ) {			
				closure_state = ClosureState.Open;
			}
		}
	}
	
	
	/* (non-Javadoc)
	 * @see j3l.util.interfaces.IClose#close()
	 */
	@Override public void close() throws IOException {
		synchronized( writer ) {
			if( !isOpen() ) {			
				return;
			}
			closure_state = ClosureState.InClosure;
		}
		flush();
		closure_state = ClosureState.Closed;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#finalize()
	 */
	@Override public void finalize() {
		try {
			close();
		}
		catch( IOException e ) {
			e.printStackTrace();
		}
	}

}
