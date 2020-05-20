package ru.qadojo.cte.findcats.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.qadojo.cte.findcats.model.FindCatsRequest;
import ru.qadojo.cte.findcats.services.FindCatsService;

import javax.validation.Valid;


@RestController
@RequestMapping("cats")
@RequiredArgsConstructor
public class FindCatsController {

    private final FindCatsService findCatsService;

    @PostMapping("/requests")
    public void chooseCats(@Valid @RequestBody FindCatsRequest request) {
        findCatsService.startCatChoice(request);
    }
}
