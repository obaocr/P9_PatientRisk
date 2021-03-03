package com.ocr.p9PatientRisk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

// TODO gérer les réponses des SVC appelés Patient et Note en cas d'erreur ou not found ...

@EnableFeignClients("com.ocr.p9PatientRisk")
@SpringBootApplication
public class P9PatientRiskApplication {

    public static void main(String[] args) {
        SpringApplication.run(P9PatientRiskApplication.class, args);
    }

}
