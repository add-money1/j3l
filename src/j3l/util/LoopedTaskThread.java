package j3l.util;

import java.time.Instant;

import j3l.util.check.ArgumentChecker;

/**
 * <p></p>
 * 
 * @since JDK 1.8
 * @version 2016.05.21_0
 * @author Johannes B. Latzel
 */
public final class LoopedTaskThread extends Thread {
	
	
	/**
	 * <p></p>
	 */
	private final long loop_time_span;
	
	
	/**
	 * <p></p>
	 */
	private final Runnable looped_task;
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public LoopedTaskThread(Runnable looped_task, String name, long loop_time_span) {
		super(name);
		this.looped_task = ArgumentChecker.checkForNull(looped_task, "looped_task");
		this.loop_time_span = ArgumentChecker.checkForBoundaries(loop_time_span, 0, Long.MAX_VALUE, "loop_time_span");
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	@Override public final void run() {
		long start_time;
		long remaining_time;
		while( isAlive() && !isInterrupted() ) {
			start_time = Instant.now().toEpochMilli();
			try {
				looped_task.run();
			}
			catch( Exception e ) {
				e.printStackTrace();
				break;
			}
			remaining_time = loop_time_span - Instant.now().toEpochMilli() + start_time;
			if( remaining_time > 0 ) {
				try {
					sleep(remaining_time);
				}
				catch( InterruptedException e ) {
					e.printStackTrace();
					break;
				}
			}
		}
	}

}
