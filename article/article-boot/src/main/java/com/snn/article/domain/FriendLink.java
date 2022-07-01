package com.snn.article.domain;

import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@ToString
public class FriendLink {
    private Long linkId;

    @NotBlank
    private String name;

    @NotBlank
    private String link;

    private Integer orderby;

    @NotNull
    private Byte isShow;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date startTime;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date endTime;
}