package com.lz.dem;

import org.apache.coyote.ProtocolHandler;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Conf
 *
 * @author lizheng 日撸代码三千行，不识加班累，只缘bug狂。
 * @version 1.0
 * @date 2020/7/28 10:01
 */
@Configuration
public class Conf {

	@Bean
	@ConditionalOnProperty(prefix = "hello", value = {"name"})
	A a() {
		return new A("name");
	}

	@Bean
	WebServerFactoryCustomizer<TomcatServletWebServerFactory> myWebServerFactoryCustomizer() {
		return factory -> factory.addConnectorCustomizers(connector -> {
			ProtocolHandler protocolHandler = connector.getProtocolHandler();
			if (protocolHandler instanceof Http11NioProtocol) {
				Http11NioProtocol http11NioProtocol = ((Http11NioProtocol) protocolHandler);
				http11NioProtocol.setKeepAliveTimeout(20000);
				http11NioProtocol.setMaxKeepAliveRequests(100);
			}
		});
	}
}
