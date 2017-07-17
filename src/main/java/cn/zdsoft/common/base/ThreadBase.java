package cn.zdsoft.common.base;

/**
 * 线程基类，提供常用的实现方法等
 * @author 段江涛
 * @date 2017-07-17
 */
public class ThreadBase extends Thread {
	/**
	 * 是否正在运行
	 */
	protected boolean running;

	public void Start() {
		running = true;
	}

	public void Stop() {
		running = false;
	}

	/**
	 * 程序暂停多少秒
	 * @param seconds 需要等待的时间(单位:秒)
	 * @throws InterruptedException 
	 */
	protected void Wait(int seconds) throws InterruptedException {
		int i = 0;
		while (i < seconds && running) {
			Thread.sleep(1000);
			i++;
		}
	}
}
