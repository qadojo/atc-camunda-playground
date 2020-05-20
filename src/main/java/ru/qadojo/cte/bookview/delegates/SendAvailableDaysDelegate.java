package ru.qadojo.cte.bookview.delegates;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.qadojo.cte.bookview.services.ViewBookingService;

@Slf4j
@Component("sendAvailableDays")
@RequiredArgsConstructor
public class SendAvailableDaysDelegate implements JavaDelegate {

    private final ViewBookingService viewBookingService;

    @Override
    public void execute(DelegateExecution execution) {
        log.info("Available days are sent");
    }
}
