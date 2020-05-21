package ru.qadojo.cte;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.qadojo.cte.findcats.clients.CatsProducerConfig;

@SpringBootApplication
@EnableProcessApplication
@EnableConfigurationProperties(CatsProducerConfig.class)
public class CamundaTestingExamplesApplication {

    public static void main(String[] args) {
        SpringApplication.run(CamundaTestingExamplesApplication.class, args);
    }
}
