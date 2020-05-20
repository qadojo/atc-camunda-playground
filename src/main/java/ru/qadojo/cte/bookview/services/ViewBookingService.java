package ru.qadojo.cte.bookview.services;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.Execution;
import org.springframework.stereotype.Service;
import ru.qadojo.cte.bookview.model.ChooseBookingRequest;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ViewBookingService {

    public static final String BOOK_VIEW_PROCESS ="chooseDateForViewProcess";

    private static final String CLIENT_SELECTED_DATA_MSG = "clientSelectedDate";

    private final RuntimeService runtimeService;


    public void chooseViewDate(ChooseBookingRequest bookingRequest) {
        final Execution waitingExecution = runtimeService.createExecutionQuery()
            .messageEventSubscriptionName(CLIENT_SELECTED_DATA_MSG)
            .variableValueEquals("catRequestId", bookingRequest.getCatRequestId())
            .singleResult();

        final Map<String, Object> processVars = new HashMap<>();
        processVars.put("viewDate", bookingRequest.getDate());

        runtimeService.messageEventReceived(
            CLIENT_SELECTED_DATA_MSG,
            waitingExecution.getId(),
            processVars
        );
    }
}
