package j3l.util;

public final class StopWatch {
	
	private long time = 0;
	private Runnable run_forrest;
	
	public StopWatch(Runnable run) {
		run_forrest = run;
	}
	
	public void meassure(int x) {
		long start = System.currentTimeMillis();
		for(int a=0;a<x;a++) {
			run_forrest.run();
		}
		time = System.currentTimeMillis() - start;
	}
	
	public void meassure() {
		long start = System.currentTimeMillis();
		run_forrest.run();
		time = System.currentTimeMillis() - start;
	}
	
	public long getMilliSeconds() {
		return time;
	}
	
	public double getSecondsD() {
		return time/1000.0;
	}
	
	public float getSeconds() {
		return time/1000f;
	}
	
}
