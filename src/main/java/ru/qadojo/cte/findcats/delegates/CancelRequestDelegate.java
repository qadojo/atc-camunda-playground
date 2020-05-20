package ru.qadojo.cte.findcats.delegates;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.qadojo.cte.domain.CatRequest;
import ru.qadojo.cte.findcats.services.FindCatsService;

@Slf4j
@Component("cancelRequest")
@RequiredArgsConstructor
public class CancelRequestDelegate implements JavaDelegate {

    private final FindCatsService findCatsService;

    @Override
    public void execute(DelegateExecution execution) {
        final Long catRequestId = Long.valueOf(
            execution.getVariable("catRequestId").toString()
        );

        findCatsService.cancelCatRequest(
            CatRequest.builder()
                .id(catRequestId)
                .build()
        );

        log.info("request is canceled");
    }
}
