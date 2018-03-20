package com.university.supdialogflowproxy.Data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
public class Article {

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private List<String> tag;

    @Getter
    @Setter
    private String heading;

    @Getter
    @Setter
    private String body;

}