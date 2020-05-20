package ru.qadojo.cte.bookview.delegates;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Slf4j
@Component("bookDateForView")
public class BookDateForViewDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        log.info("The Date for View is booked ");
    }
}
