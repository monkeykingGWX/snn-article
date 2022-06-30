package com.snn.article.domain;


import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Data
@ToString
public class WebInfo {
    private Long webId;

    @NotBlank()
    private String title;

    @NotBlank()
    private String keyword;

    @NotBlank()
    private String description;

    @NotBlank()
    private String bottom;

}