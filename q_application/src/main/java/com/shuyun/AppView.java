package com.shuyun;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.annotation.PostConstruct;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.qlk.main.ContentToolPanel;

public class AppView extends JFrame {
	private static final long serialVersionUID = 1L;
	private static AppView mainFrame;
	private JPanel contentPane;
	private JPanel contextPanel;
	private boolean isMove = true;
	public static AppView getInstance() {
		if (mainFrame == null) {
			mainFrame = new AppView();
		}

		return mainFrame;
	}
	@SuppressWarnings("restriction")
	public  AppView()   {
		setUndecorated(true);
		try {
			 setFocusableWindowState(true); 
			 UIManager.setLookAndFeel(com.sun.java.swing.plaf.windows.WindowsLookAndFeel.class.getName());
		} catch ( Exception e) {
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		setContentPane(contentPane);
		GridBagLayout gridPanel = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.ipadx = 60;
		contextPanel=ContentToolPanel.getInstance();
 		append(c, gridPanel,contextPanel);
		contentPane.setLayout(gridPanel);
		initWindowLocation();
	 
	}

	private void append(GridBagConstraints c, GridBagLayout g, Component comp) {
		add(comp);
		g.setConstraints(comp, c);
	}

	public void initWindowLocation() {
		Dimension screenSize = this.getToolkit().getScreenSize();
		int w = (int) (screenSize.width * 0.8);
		int h = (int) (screenSize.height * 0.8);
		setSize((int)w, (int) h);  
		setLocation((screenSize.width - w) / 2, (screenSize.height - h) / 2);
		setMinimumSize(new Dimension(800, 500));
	}

	public void unMaxSrceen() {
		isMove = true;
	}

	public void maxScreen() {
		isMove = false;
	}

	public boolean isMove() {
		return isMove;
	}
    @PostConstruct
    public void init() {
    }
}