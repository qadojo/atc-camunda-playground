package ru.qadojo.cte.findcats.services;

import org.camunda.bpm.engine.RuntimeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.qadojo.cte.storage.CatRequestsRepository;
import ru.qadojo.cte.storage.CatsRepository;

import static org.mockito.Mockito.verify;
import static ru.qadojo.cte.domain.CatGender.FEMALE;
import static ru.qadojo.cte.domain.CatGender.MALE;

@RunWith(MockitoJUnitRunner.class)
public class FindCatsServiceTest {

    @Mock
    RuntimeService runtimeService;

    @Mock
    CatsRepository catsRepository;

    @Mock
    CatRequestsRepository catRequestsRepository;

    FindCatsService findCatsService;


    @Before
    public void setupFindCatsServiceUnderTest() {
        findCatsService = new FindCatsService(
            runtimeService,
            catsRepository,
            catRequestsRepository
        );
    }


    @Test
    public void shouldFilterByBreedAndGender() {
        // when
        findCatsService.findMatchingCats("someBreed", FEMALE);

        // then
        verify(catsRepository).findAllByBreedAndGender("someBreed", FEMALE);
    }

    @Test
    public void shouldFilterByBreed() {
        // when
        findCatsService.findMatchingCats("anotherBreed", null);

        // then
        verify(catsRepository).findAllByBreed("anotherBreed");
    }

    @Test
    public void shouldFilterByGender() {
        // when
        findCatsService.findMatchingCats(null, MALE);

        // then
        verify(catsRepository).findAllByGender(MALE);
    }
}
