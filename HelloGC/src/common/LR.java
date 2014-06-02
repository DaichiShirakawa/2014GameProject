package common;


public enum LR{
	LEFT(-1),
	RIGHT(1);
	private int signum;
	private LR(int signum) {
		this.signum = signum;
	}
	public int signum() {
		return signum;
	}
}