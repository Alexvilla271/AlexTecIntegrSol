package com.javaspringclub;

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
public class WebServiceConfig extends WsConfigurerAdapter {

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext appContext){
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(appContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }

    // localhost:8080/ws/usuarios.wsdl
    @Bean(name = "usuarios")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema schema){
        DefaultWsdl11Definition wsdl = new DefaultWsdl11Definition();
        wsdl.setPortTypeName("UsuariosPort");
        wsdl.setLocationUri("/ws");
        wsdl.setTargetNamespace(UsuarioEndpoint.NAMESPACE_URI);
        wsdl.setSchema(schema);
        return wsdl;
    }

    @Bean
    public XsdSchema usuariosSchema(){
        return new SimpleXsdSchema(new ClassPathResource("/xsd/usuarios.xsd"));
    }

}
