package ru.qadojo.cte.findcats.model;

import lombok.Data;
import ru.qadojo.cte.domain.CatGender;

import javax.validation.constraints.NotEmpty;

@Data
public class FindCatsRequest {

    private String catBreed;
    private CatGender catGender;

    @NotEmpty
    private String userEmail;
}
