package ru.qadojo.cte.findcats.clients;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.qadojo.cte.domain.Cat;
import ru.qadojo.cte.domain.CatGender;

import java.util.List;

import static java.lang.String.format;
import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public class CatsProducerClient {

    private final CatsProducerConfig catsProducerConfig;
    private final RestTemplateBuilder restTemplateBuilder;


    public List<Cat> fetchCats(String breed, CatGender gender) {
        final RestTemplate client = restTemplateBuilder
            .rootUri(
                format("%s:%s",
                    catsProducerConfig.getHost(),
                    catsProducerConfig.getPort()
                )
            )
            .build();

        final CatsResponse catsResponse = client.getForObject(
            "/cats?breed={breed}&gender={gender}",
            CatsResponse.class,
            breed,
            gender.toString()
        );

        return ofNullable(catsResponse)
            .map(CatsResponse::getCats)
            .map(cats -> cats.stream()
                .map(cat -> Cat.builder()
                    .id(cat.getId())
                    .name(cat.getName())
                    .build()
                )
                .collect(toList())
            )
            .orElse(emptyList());
    }

    @Data
    public static class CatsResponse {
        private List<CatsResponseCat> cats;
    }

    @Data
    public static class CatsResponseCat {
        private Long id;
        private String name;
    }
}
