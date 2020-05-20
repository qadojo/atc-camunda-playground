package ru.qadojo.cte.bookview.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class ChooseBookingRequest {

    @NotEmpty
    private String catRequestId;

    @NotNull
    private Date date;
}
