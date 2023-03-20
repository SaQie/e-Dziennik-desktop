package pl.edziennik.client.rest.dto;

import lombok.Data;

import java.util.List;

@Data
public class Page<INPUT> {

    private Integer pagesCount;
    private Long itemsTotalCount;
    private Integer actualPage;
    private Integer itemsOnPage;

    private INPUT entities;


    public static <INPUT> Page<INPUT> empty(){
        return new Page<>();
    }

 }
