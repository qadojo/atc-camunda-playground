package ru.qadojo.cte.findcats.delegates;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.extension.mockito.ProcessExpressions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.qadojo.cte.domain.Cat;
import ru.qadojo.cte.storage.CatsRepository;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;
import static org.mockito.Mockito.when;
import static ru.qadojo.cte.bookview.services.ViewBookingService.BOOK_VIEW_PROCESS;
import static ru.qadojo.cte.findcats.services.FindCatsService.FIND_CATS_PROCESS;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SaveRequestDelegateTest {

    @MockBean
    CatsRepository catsRepository;

    @Autowired
    FindMatchingCatsDelegate findMatchingCatsDelegate;


    @Test
    public void shouldSaveEmptySearchResult() {
        // given
        when(catsRepository.findAllByBreed("someBreed")).thenReturn(emptyList());

        // when
        ProcessInstance processInstance = runtimeService()
            .createProcessInstanceByKey(FIND_CATS_PROCESS)
            .setVariable("catBreed", "someBreed")
            .execute();

        // then
        assertThat(processInstance).hasPassed("findCatsActivity");

        // and
        assertThat(processInstance)
            .variables()
            .containsEntry("matchingCats", emptyList());
    }

    @Test
    public void shouldSaveNonEmptySearchResult() {
        // given
        final List<Cat> someCats = asList(
            Cat.builder().name("foo cat").build(),
            Cat.builder().name("bar cat").build()
        );

        when(catsRepository.findAllByBreed("anotherBreed")).thenReturn(someCats);

        // and
        ProcessExpressions.registerCallActivityMock(BOOK_VIEW_PROCESS)
            .deploy(repositoryService());

        // when
        ProcessInstance processInstance = runtimeService()
            .createProcessInstanceByKey(FIND_CATS_PROCESS)
            .setVariable("catBreed", "anotherBreed")
            .execute();

        // then
        assertThat(processInstance).hasPassed("findCatsActivity");
        assertThat(processInstance).isEnded();

        // and
        assertThat(processInstance)
            .variables()
            .containsEntry("matchingCats", someCats);
    }
}
