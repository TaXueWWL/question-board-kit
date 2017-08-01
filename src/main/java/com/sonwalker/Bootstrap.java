package com.sonwalker;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

@SpringBootApplication
public class Bootstrap {
	
	public static void main(String[] args) {
		SpringApplication.run(Bootstrap.class, args);
	}
	
	/**
	 * 自定义错误页面
	 * @return
	 */
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {

	    return new EmbeddedServletContainerCustomizer() {
	        public void customize(ConfigurableEmbeddedServletContainer container) {

	            ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401.html");
	            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
	            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");

	            container.addErrorPages(error401Page, error404Page, error500Page);
	        }

	    };
	}
	
	class MyTomcatConnectorCustomizer implements TomcatConnectorCustomizer {
		public void customize(Connector connector) {
			Http11NioProtocol protocol = (Http11NioProtocol) connector
					.getProtocolHandler();
			// 设置最大连接数
			protocol.setMaxConnections(2000);
			// 设置最大线程数
			protocol.setMaxThreads(2000);
			protocol.setConnectionTimeout(30000);
		}
	}
}
