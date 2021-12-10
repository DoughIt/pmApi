package com.pm.pmapi.dto;

import com.pm.pmapi.mbg.model.TabLesson;
import com.pm.pmapi.mbg.model.TabUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

/**
 * @program: pmApi
 * @description:
 * @author: Shen Zhengyu
 * @create: 2021-12-07 14:09
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookInfo {
    Long id;
    ArrayList<URLInfo> pictures;
    String bookname;
    String author;
    String publisher;
    String unit;
    String newDegree;
    String content;
    Double price;
    TabUser seller;
    TabLesson lesson;
}
