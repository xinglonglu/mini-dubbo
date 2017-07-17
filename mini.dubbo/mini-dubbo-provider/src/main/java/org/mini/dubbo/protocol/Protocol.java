package org.mini.dubbo.protocol;

public interface Protocol {
	void export(String interfaceName, Class<?> impl) throws InterruptedException;
}
