package ru.qadojo.cte.findcats.clients;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("cats.producer")
public class CatsProducerConfig {
    String host;
    Integer port;
}
