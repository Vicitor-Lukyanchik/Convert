package com.barsu.convert.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Convert {

    private String idea;
    private String market;
    private String resources;
    private String results;
}
