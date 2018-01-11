package com.shuyun.datacenter.listener;

import com.shuyun.datacenter.WorkPlan;

public interface Subject {
	 /**
     * 增加订阅者
     * @param observer
     */
    public void attach(TaskListener listener);
    /**
     * 删除订阅者
     * @param observer
     */
    public void detach(TaskListener observer);
    /**
     * 通知订阅者更新消息
     */
    public void notifyStart(WorkPlan plan);
    public void notifyEnd(WorkPlan plan);
}
