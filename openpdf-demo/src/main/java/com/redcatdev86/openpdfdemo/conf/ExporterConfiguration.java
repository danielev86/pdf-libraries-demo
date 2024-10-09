package com.redcatdev86.openpdfdemo.conf;

import com.redcatdev86.openpdfdemo.service.impl.SampleTablePdfExporterImpl;
import com.redcatdev86.openpdfdemo.service.impl.SimpleHeaderFooterPdfPageExporter;
import com.redcatdev86.openpdfdemo.service.impl.SimpleHeaderFooterWithPageExporter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Configuration
public class ExporterConfiguration {

    @Bean
    @Scope(value = SCOPE_PROTOTYPE)
    public SimpleHeaderFooterPdfPageExporter simpleHeaderFooterPdfPageExporter(){
        return new SimpleHeaderFooterPdfPageExporter();
    }

    @Bean
    @Scope(value = SCOPE_PROTOTYPE)
    public SimpleHeaderFooterWithPageExporter simpleHeaderFooterWithPageExporter(){
        return new SimpleHeaderFooterWithPageExporter();
    }

    @Bean
    @Scope(value = SCOPE_PROTOTYPE)
    public SampleTablePdfExporterImpl sampleTablePdfExporter(){
        return new SampleTablePdfExporterImpl();
    }
}
