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
 * @create: 2021-12-07 14:18
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PPTInfo {
    Long id;
    ArrayList<URLInfo> pictures;
    String chapters;
    String paperSize;
    String newDegree;
    Boolean singlePrint;
    String unit;
    Double price;
    String content;
    TabUser seller;
    TabLesson lesson;
}
