package ru.qadojo.cte.findcats.delegates;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.qadojo.cte.findcats.services.FindCatsService;
import ru.qadojo.cte.domain.Cat;
import ru.qadojo.cte.domain.CatGender;

import java.util.List;

import static ru.qadojo.cte.utils.StringUtils.nullOrString;

@Slf4j
@RequiredArgsConstructor
@Component("findMatchingCatsDelegate")
public class FindMatchingCatsDelegate implements JavaDelegate {

    private final FindCatsService findCatsService;


    @Override
    public void execute(DelegateExecution execution) {
        final List<Cat> matchingCats = findCatsService.findMatchingCats(
            nullOrString(execution.getVariable("catBreed")),
            CatGender.from(execution.getVariable("catGender"))
        );

        execution.setVariable("matchingCats", matchingCats);

        log.info("found matching cats: {}", matchingCats);
    }

}
