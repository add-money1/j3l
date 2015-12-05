package j3l.util.log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Flushable;
import java.io.IOException;
import java.io.PrintWriter;

import j3l.util.ClosureState;
import j3l.util.check.ArgumentChecker;
import j3l.util.close.IClose;


/**
 * <p></p>
 * 
 * @since JDK 1.8
 * @version 2015.11.24_0
 * @author Johannes B. Latzel
 */
public final class Logger implements Flushable, IClose<IOException> {
	
	
	/**
	 * <p></p>
	 */
	private final static BufferedWriter STANDARD_WRITER = new BufferedWriter(new PrintWriter(System.out));
	
	
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
	 */
	private final Object writer_lock;
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 * @throws IOException 
	 */
	public Logger(File log_file) throws IOException {
		
		ArgumentChecker.checkForExistence(log_file, "log_file");
		
		writer_lock = new Object();
		closure_state = ClosureState.None;
		
		open();
		
		try {
			writer = new BufferedWriter(new FileWriter(log_file));
		}
		catch (Exception e) {
			writer = Logger.STANDARD_WRITER;
			log(e.toString());
			log(toString() + ": using the standard writer!");
		}
		
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public void log(String message) throws IOException {
		synchronized( writer_lock ) {
			if( isOpen() ) {
				writer.write(message);
				writer.write(System.lineSeparator());
			}
		}
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public boolean usesStandardWriter() {
		return writer == Logger.STANDARD_WRITER;
	}
	
	
	/* (non-Javadoc)
	 * @see java.io.Flushable#flush()
	 */
	@Override public void flush() throws IOException {
		synchronized( writer_lock ) {
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
		synchronized( writer_lock ) {
			if( !hasBeenOpened() ) {			
				closure_state = ClosureState.Open;
			}
		}
	}
	
	
	/* (non-Javadoc)
	 * @see j3l.util.interfaces.IClose#close()
	 */
	@Override public void close() throws IOException {
		
		synchronized( writer_lock ) {
			if( !isOpen() ) {			
				return;
			}
			else {
				closure_state = ClosureState.InClosure;
			}
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
