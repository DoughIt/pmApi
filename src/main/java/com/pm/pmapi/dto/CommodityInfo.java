package com.pm.pmapi.dto;

import com.pm.pmapi.mbg.model.TabLesson;
import com.pm.pmapi.mbg.model.TabUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: pmApi
 * @description:
 * @author: Shen Zhengyu
 * @create: 2021-12-07 16:20
 **/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommodityInfo{
    private Long id;

    private String name;

    private String lessonId;

    private Long teacherId;

    private Long sellerId;

    private Integer type;

    private String author;

    private String publisher;

    private String coverPercentage;

    private String imageId;

    private String content;

    private Double price;

    private Boolean singlePrint;

    private String singlePrintChinese;

    private Long commodityId;

    private Integer chapters;

    private String paperSize;

    private String newDegree;

    private String unit;

    private Date createTime;

    private Date modifyTime;

    private byte[] picture;


    SimpleUserInfo seller;
    TabLesson lesson;
}
