package com.example.servicioweb;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class ServicioWebConfig extends WsConfigurerAdapter{
    @Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(servlet, "/ws/*");
	}

    @Bean(name = "ciudades")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema ciudadesSchema) {
		DefaultWsdl11Definition wsdl = new DefaultWsdl11Definition();
		wsdl.setPortTypeName("CiudadesPort");
		wsdl.setLocationUri("/ws");
		wsdl.setTargetNamespace("http://spring.io/guides/gs-producing-web-service");
		wsdl.setSchema(ciudadesSchema);
		return wsdl;
	}

	@Bean
	public XsdSchema ciudadesSchema() {
		return new SimpleXsdSchema(new ClassPathResource("ciudades.xsd"));
	}
}
