package com.qlk.main;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.UnsupportedEncodingException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private static MainFrame mainFrame;
	private JPanel contentPane;
	private JPanel contextPanel;
	private boolean isMove = true;
	public static MainFrame getInstance() {
		if (mainFrame == null) {
			mainFrame = new MainFrame();
		}

		return mainFrame;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					new HelloFrame();
					MainFrame.getInstance().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws UnsupportedEncodingException
	 */
	private MainFrame() {
		setUndecorated(true);
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
}
