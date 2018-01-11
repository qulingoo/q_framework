package com.shuyun.core.resource;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;

import com.shuyun.AppView;
import com.shuyun.config.Configuration;

public class WindowController {
	public String index() {
		return Configuration.getConfigure().get("index").toString();
	}
	public void close() {
		System.exit(0);
	}
	public void maximize() {
		AppView f = AppView.getInstance();
		if(f.isMove()) {
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		f.maxScreen();
		}else {
			f.setExtendedState(JFrame.NORMAL);
			f.unMaxSrceen();
		}
		
	}
	public void minimize() {
		AppView f = AppView.getInstance();
		f.setExtendedState(JFrame.ICONIFIED);
		
	}
	public void move(int x, int y) {
		AppView f = AppView.getInstance();
		if (f.isMove()) {
			Point location = f.getLocation();
			int windowX = location.x + (int) x;
			int windowY = location.y + (int) y;
//			if (windowX < 0) {
//				windowX = 0;
//			}
//			if (windowY < 0) {
//				windowY = 0;
//			}
			Dimension screenSize = f.getToolkit().getScreenSize();
			screenSize.getWidth();
//			if (windowX + f.getSize().getWidth() > screenSize.getWidth()) {
//				windowX=(int)(screenSize.getWidth()- f.getSize().getWidth());
//			}
//			if (windowY + f.getSize().getHeight() > screenSize.getHeight()-30) {
//				windowY=(int)(screenSize.getHeight()-f.getSize().getHeight()-30);
//			}
			f.setLocation(windowX, windowY);
		}

	}
}
