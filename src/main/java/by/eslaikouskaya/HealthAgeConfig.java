package by.eslaikouskaya;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan("by.eslaikouskaya")
@EnableTransactionManagement
@PropertySource("classpath:database.properties")
@PropertySource("classpath:hibernate.properties")
public class HealthAgeConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private Environment env;

	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver =
				new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("home");
	}


	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();

		dataSource.setUrl(env.getProperty("connection.url"));
		dataSource.setUsername(env.getProperty("connection.username"));
		dataSource.setPassword(env.getProperty("connection.password"));
		dataSource.setDriverClassName(env.getProperty("connection.driver_class"));
		dataSource.setMaxTotal(Integer.parseInt(env.getProperty("connection.max_total")));

		return dataSource;
	}

	@Bean
	public LocalSessionFactoryBean localSessionFactoryBean(DataSource dataSource) {
		LocalSessionFactoryBean localSessionFactoryBean
				= new LocalSessionFactoryBean();

		localSessionFactoryBean.setDataSource(dataSource);
		localSessionFactoryBean.setHibernateProperties(hibernateProperties());
		localSessionFactoryBean.setAnnotatedPackages("by.eslaikouskaya.pojo");
		localSessionFactoryBean.setPackagesToScan("by.eslaikouskaya.pojo");

		return localSessionFactoryBean;
	}

	private Properties hibernateProperties() {
		Properties properties = new Properties();

		properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		return properties;
	}

	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager
				= new HibernateTransactionManager();

		transactionManager.setSessionFactory(sessionFactory);

		return transactionManager;
	}

}
