package ru.qadojo.cte.findcats.contracts;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.context.junit4.SpringRunner;
import ru.qadojo.cte.domain.Cat;
import ru.qadojo.cte.findcats.clients.CatsProducerClient;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode.REMOTE;
import static ru.qadojo.cte.domain.CatGender.MALE;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureStubRunner(
    ids = {"ru.qadojo:cats-producer:+"},
    stubsMode = REMOTE
)
public class CatsProducerContract {

    @Autowired
    CatsProducerClient catsProducerClient;

    @Test
    public void shouldFetchCats() {
        List<Cat> cats = catsProducerClient.fetchCats("bengal", MALE);
        assertThat(cats).isNotEmpty();
    }
}
