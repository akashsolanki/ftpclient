/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.security.samples.config;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.samples.LoadingScreenDemo;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@ComponentScan("org.springframework.security.samples")
public class RootConfig {
	@Autowired
	DataSource dataSource;

	@Value("${property.db.managerpanel}")
	private boolean showManagerPanel;

	@Value("${property.root.url}")
	private String url;

	@Bean
	public JdbcTemplate getJdbcTemplate(){
	  return new JdbcTemplate(dataSource);
	}
	
	//default username : sa, password : ''
	@PostConstruct
	public void getDbManager(){
		LoadingScreenDemo loadingDemo = LoadingScreenDemo.getInstance();
		loadingDemo.closeLoader();
		openBrowser();
		if(showManagerPanel){
	   DatabaseManagerSwing.main(
		new String[] { "--url", "jdbc:hsqldb:file:local_database", "--user", "sa", "--password", ""});
		}
	}

	private void openBrowser() {
		// TODO Auto-generated method stub
		    if(Desktop.isDesktopSupported()){
	            Desktop desktop = Desktop.getDesktop();
	            try {
	                desktop.browse(new URI(url));
	            } catch (IOException | URISyntaxException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	        }else{
	            Runtime runtime = Runtime.getRuntime();
	            try {
	                runtime.exec("xdg-open " + url);
	            } catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	        }
	    }
}