package com.ocr.p9PatientRisk.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignExceptionConfig {

    @Bean
    public CustomErrorDecoder myCustomErrorDecoder(){
        return new CustomErrorDecoder();
    }
}

