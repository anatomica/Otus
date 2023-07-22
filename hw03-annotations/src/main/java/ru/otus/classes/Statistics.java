package ru.otus.classes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Statistics {

    private Integer success;

    private Integer failed;

    private Integer total;

}
