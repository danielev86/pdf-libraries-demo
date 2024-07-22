package com.redcatdev86.itextsevendemo.configuration;

import com.redcatdev86.itextsevendemo.bean.SimpleDataBean;
import com.redcatdev86.itextsevendemo.service.PdfExporter;
import com.redcatdev86.itextsevendemo.service.impl.HelloWorldExporterImpl;
import com.redcatdev86.itextsevendemo.service.impl.SimplePdfExporterImpl;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ItextConfig {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public PdfExporter<String> helloWorldExporter() {
        return new HelloWorldExporterImpl();
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public PdfExporter<SimpleDataBean> simplePdfExporter() {
        return new SimplePdfExporterImpl();
    }

}
