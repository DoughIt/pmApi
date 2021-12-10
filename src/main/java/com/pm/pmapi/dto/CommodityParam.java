package com.pm.pmapi.dto;

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
 * @create: 2021-12-07 16:21
 **/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommodityParam{
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

    private Integer dealMethod;

    private Long commodityId;

    private Integer chapters;

    private String paperSize;

    private String newDegree;

    private String unit;

    private byte[] picture;
}