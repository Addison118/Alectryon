package org.alectryon.utils;

public interface Output {
	public void trace(String msg);
	public void debug(String msg);
	public void info(String msg);
	public void warning(String msg);
	public void error(String msg);
	public void fatal(String msg);
}
