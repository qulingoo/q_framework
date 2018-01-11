package com.qlk.main;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import com.shuyun.AppView;

public class HelloFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * 
	 * @throws InterruptedException
	 */
	public HelloFrame() throws InterruptedException {

	}

	public void init() {
		setUndecorated(true);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = this.getToolkit().getScreenSize();
		int w = (int) (screenSize.width * 0.5);
		int h = (int) (screenSize.height * 0.5);
		setSize((int) w, (int) h);
		setLocation((screenSize.width - w) / 2, (screenSize.height - h) / 2);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		final JFrame that = this;
		final JProgressBar progressBar = new JProgressBar();
	 
		progressBar.setBounds(0, h - 14, w, 14);
		contentPane.add(progressBar);

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					for(int i=0;i<=80;i++) {
						addValue(progressBar, 1);
						Thread.sleep(5);
					}
					 ContentToolPanel.getInstance();
					addValue(progressBar, 20);

					AppView frame = AppView.getInstance();
					that.dispose();
					frame.setVisible(true);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();

	}

	public void addValue(final JProgressBar progressBar, final int value) {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < value; i++) {
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					progressBar.setValue(progressBar.getValue() + 1);
				}

			}
		});
		thread.start();

	}
}
