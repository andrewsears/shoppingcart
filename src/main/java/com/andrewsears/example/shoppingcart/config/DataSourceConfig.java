/*
 * Andrew Sears
 * Copyright 2014
 * Created on Jul 16, 2014
 */

package com.andrewsears.example.shoppingcart.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.andrewsears.example.shoppingcart.ApplicationConstants;

/**
 * TODO Description
 * 
 * @author Andrew Sears <a href="mailto=andrew@andrewsears.com">andrew@andrewsears.com</a>
 * @see http://stackoverflow.com/questions/20156827/setup-h2-in-server-mode-using-java-based-configuration
 */
@Configuration
@EnableJpaRepositories(basePackages={ApplicationConstants.APPLICATION_SETUP_BASE_PACKAGE})
@EnableTransactionManagement
//@PersistenceContext(type=PersistenceContextType.EXTENDED)
public class DataSourceConfig {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(DataSourceConfig.class);
	
	@Value("classpath:datasource/h2/schema.sql")
	private Resource scriptSchema;
	
	@Value("classpath:datasource/example-data.sql")
	private Resource scriptData;
	
	@Value("classpath:datasource/h2/drop.sql")
	private Resource scriptDrop;

	@Bean
	@Autowired
	public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
		LOGGER.debug("Creating Data Source: Initializer");
		final DataSourceInitializer initializer = new DataSourceInitializer();
		initializer.setDataSource(dataSource);
		initializer.setDatabasePopulator(databasePopulator());
		initializer.setDatabaseCleaner(databaseCleaner());
		return initializer;
	}
	
	private DatabasePopulator databasePopulator() {
		LOGGER.debug("Creating Data Source: Initializer: Populator");
		final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(scriptSchema);
		populator.addScript(scriptData);
		return populator;
	}
	
	private DatabasePopulator databaseCleaner() {
		LOGGER.debug("Creating Data Source: Initializer: Cleaner");
		final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		//XXX populator.addScript(scriptDrop);
		return populator;
	}
	
	@Bean(destroyMethod="shutdown")
	public DataSource dataSource() {
		LOGGER.debug("Creating Data Source: Data Source");
		/*
		//String jdbcUrl = "jdbc:h2:%s/target/db/sample;AUTO_SERVER=TRUE";
		String jdbcUrl = "jdbc:h2:target/db/sample;AUTO_SERVER=TRUE";
		JdbcDataSource dataSource = new JdbcDataSource();
		dataSource.setURL(jdbcUrl);
		dataSource.setUser("sa");
		dataSource.setPassword("");
		return dataSource;
		*/
		
		// http://wimdeblauwe.wordpress.com/2013/12/16/creating-a-rest-web-application-in-4-classes/
		return new EmbeddedDatabaseBuilder()
				.setType( EmbeddedDatabaseType.H2 )
				.setName("shoppingcart")
				.build();
	}
	
	@Bean
	//public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LOGGER.debug("Creating Data Source: Entity Manager Factory");
		
		// Properties
		Properties properties = new Properties();
		properties.put("hibernate.hbm2ddl.auto", "create-drop");
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");
		
		LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
		lef.setDataSource( dataSource() );
		lef.setJpaVendorAdapter( jpaVendorAdapter() );
		lef.setPackagesToScan( ApplicationConstants.APPLICATION_SETUP_BASE_PACKAGE );
		lef.setJpaProperties(properties);
		return lef;
	}
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		LOGGER.debug("Creating Data Source: JPA Vendor Adapter");
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		//hibernateJpaVendorAdapter.setShowSql(true);
		//hibernateJpaVendorAdapter.setGenerateDdl(true);
		hibernateJpaVendorAdapter.setDatabase( Database.H2 );
		return hibernateJpaVendorAdapter;
	}
	
	@Bean
	//public PlatformTransactionManager transactionManager() {
	public JpaTransactionManager transactionManager() {
		LOGGER.debug("Creating Data Source: Transaction Manager");
		return new JpaTransactionManager();
	}
	
}
