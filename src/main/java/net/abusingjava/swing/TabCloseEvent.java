package net.abusingjava.swing;

public class TabCloseEvent {

	boolean $canceled = false;
	
	public void cancel() {
		$canceled = true;
	}
	
	public boolean isCanceled() {
		return $canceled;
	}
	
}
