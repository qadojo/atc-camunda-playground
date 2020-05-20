package ru.qadojo.cte.bookview.mail;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.qadojo.cte.domain.CatRequest;

@Component
public class MailApiClient {

    private final RestTemplate restTemplate = new RestTemplate();


    public void sendMailWithDateOptions(CatRequest catRequest) {
        restTemplate.postForObject("http://some-mail-server", catRequest, Object.class);
    }
}
