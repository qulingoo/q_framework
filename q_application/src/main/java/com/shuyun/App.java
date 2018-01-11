package com.shuyun;

import javax.sql.DataSource;
import javax.swing.SwingUtilities;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.qlk.main.HelloFrame;

@EnableTransactionManagement
@SpringBootApplication
@MapperScan("com.shuyun.**.dao")
public class App {

	private static HelloFrame view;

	@Bean
	public HelloFrame appView() {
		return view;
	}

	@Bean
	public PlatformTransactionManager txManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	public static void main(String[] args) throws Exception {

		SwingUtilities.invokeLater(() -> {
			try {
				view = new HelloFrame();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		SpringApplication app = new SpringApplication(App.class);
		app.run(args);
	}

}