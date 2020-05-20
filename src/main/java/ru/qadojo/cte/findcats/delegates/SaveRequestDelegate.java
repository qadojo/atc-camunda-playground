package ru.qadojo.cte.findcats.delegates;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.qadojo.cte.domain.CatGender;
import ru.qadojo.cte.domain.CatRequest;
import ru.qadojo.cte.findcats.services.FindCatsService;

import static ru.qadojo.cte.domain.CatRequest.State.CREATED;
import static ru.qadojo.cte.utils.StringUtils.nullOrString;

@Slf4j
@RequiredArgsConstructor
@Component("saveRequest")
public class SaveRequestDelegate implements JavaDelegate {

    private final FindCatsService findCatsService;


    @Override
    public void execute(DelegateExecution execution) {
        final CatRequest savedCatRequest = findCatsService.saveCatRequest(
            CatRequest.builder()
                .breed(nullOrString(execution.getVariable("catBreed")))
                .gender(CatGender.from(execution.getVariable("catGender")))
                .userEmail(nullOrString(execution.getVariable("userEmail")))
                .state(CREATED)
                .build()
        );

        execution.setVariable("catRequestId", savedCatRequest.getId());

        log.info("request is saved");
    }
}
