package cn.zdsoft.common;

import java.lang.Thread.State;
import java.util.List;

/**
 * 线程任务管理类，用于管理多个线程状态
 * @author 段江涛
 * @date 2018-06-27
 */
public class Tasks {
	/**
	 * 等待所有线程执行完成
	 * @param list 多个线程的集合
	 */
	public static void WaitAll(List<Thread> list){
		while(true){
			boolean hasOver=true;//是否已完成
			for (Thread thread : list) {
				if(thread.getState()!=State.TERMINATED){
					hasOver=false;//有一个未完成就表示没有完成
				}
			}
			if(hasOver){
				break;
			}
		}
	}
}
