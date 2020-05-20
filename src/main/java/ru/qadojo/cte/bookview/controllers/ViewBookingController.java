package ru.qadojo.cte.bookview.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.qadojo.cte.bookview.model.ChooseBookingRequest;
import ru.qadojo.cte.bookview.services.ViewBookingService;

import javax.validation.Valid;

@RestController
@RequestMapping("booking")
@RequiredArgsConstructor
public class ViewBookingController {

    private final ViewBookingService viewBookingService;


    @PostMapping("cats-view")
    public void chooseBooking(@Valid @RequestBody ChooseBookingRequest request) {
        viewBookingService.chooseViewDate(request);
    }
}
