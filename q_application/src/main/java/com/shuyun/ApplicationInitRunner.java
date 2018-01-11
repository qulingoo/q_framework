package com.shuyun;
 


import javax.swing.SwingUtilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.qlk.main.HelloFrame;

@Component
public class ApplicationInitRunner implements CommandLineRunner {
	@Autowired
	HelloFrame appView;
	@Override
	public void run(String... arg0) throws Exception {
		SwingUtilities.invokeLater(() -> {
//			appView.init();
		});
	}
	
}
