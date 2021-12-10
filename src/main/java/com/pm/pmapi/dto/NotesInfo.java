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
 * @create: 2021-12-07 14:17
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotesInfo {
    Long id;
    ArrayList<URLInfo> pictures;
    String chapters;
    String paperSize;
    Boolean singlePrint;
    String newDegree;
    String unit;
    Double price;
    String content;
    TabUser seller;
    TabLesson lesson;
}
