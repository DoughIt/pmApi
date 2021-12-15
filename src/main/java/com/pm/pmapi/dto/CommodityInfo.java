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

    private String imageUrl;

    private String content;

    private Double price;

    private Boolean singlePrint;

    private String singlePrintChinese;

    private Boolean isFavorite;

    private Integer dealMethod;

    private Long commodityId;

    private String chapters;

    private String paperSize;

    private String newDegree;

    private String unit;

    private Date createTime;

    private Date modifyTime;

    @Override
    public String toString() {
        return "CommodityInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lessonId='" + lessonId + '\'' +
                ", teacherId=" + teacherId +
                ", sellerId=" + sellerId +
                ", type=" + type +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", coverPercentage='" + coverPercentage + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", content='" + content + '\'' +
                ", price=" + price +
                ", singlePrint=" + singlePrint +
                ", singlePrintChinese='" + singlePrintChinese + '\'' +
                ", isFavorite=" + isFavorite +
                ", dealMethod=" + dealMethod +
                ", commodityId=" + commodityId +
                ", chapters=" + chapters +
                ", paperSize='" + paperSize + '\'' +
                ", newDegree='" + newDegree + '\'' +
                ", unit='" + unit + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }
}
