package ru.qadojo.cte.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.qadojo.cte.domain.Cat;

import static java.util.Arrays.asList;
import static ru.qadojo.cte.domain.CatGender.FEMALE;
import static ru.qadojo.cte.domain.CatGender.MALE;

@Component
@RequiredArgsConstructor
public class DemoDataInitializer {

    private final CatsRepository catsRepository;

    @EventListener
    public void onApplicationEvent(
        @SuppressWarnings("unused") ContextRefreshedEvent event
    ) {
        populateCats();
    }

    private void populateCats() {
        catsRepository.saveAll(asList(
            Cat.builder().name("Барсик").breed("дворовый").gender(MALE).build(),
            Cat.builder().name("Зератул").breed("дворовый").gender(MALE).build(),
            Cat.builder().name("Лора").breed("бенгал").gender(FEMALE).build(),
            Cat.builder().name("Шарик").breed("пес").gender(MALE).build()
        ));
    }
}
