package com.pm.pmapi.mbg.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description mbg自动生成tab_commodity表实体类
 *
 * @date 2021-12-20 09:27
 */
public class TabCommodity implements Serializable {
    /**
     * 自增主键
     *
     * @mbg.generated
     */
    private Long id;

    private String name;

    private Long lessonId;

    private Long teacherId;

    private Long sellerId;

    private Integer type;

    private String author;

    private String publisher;

    private String coverPercentage;

    private String content;

    private Double price;

    private Boolean singlePrint;

    private Integer dealMethod;

    private Long commodityId;

    private Integer chapters;

    private String paperSize;

    private String newDegree;

    private String unit;

    private Date createTime;

    private Date modifyTime;

    private String imageUrl;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCoverPercentage() {
        return coverPercentage;
    }

    public void setCoverPercentage(String coverPercentage) {
        this.coverPercentage = coverPercentage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getSinglePrint() {
        return singlePrint;
    }

    public void setSinglePrint(Boolean singlePrint) {
        this.singlePrint = singlePrint;
    }

    public Integer getDealMethod() {
        return dealMethod;
    }

    public void setDealMethod(Integer dealMethod) {
        this.dealMethod = dealMethod;
    }

    public Long getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Long commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getChapters() {
        return chapters;
    }

    public void setChapters(Integer chapters) {
        this.chapters = chapters;
    }

    public String getPaperSize() {
        return paperSize;
    }

    public void setPaperSize(String paperSize) {
        this.paperSize = paperSize;
    }

    public String getNewDegree() {
        return newDegree;
    }

    public void setNewDegree(String newDegree) {
        this.newDegree = newDegree;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", lessonId=").append(lessonId);
        sb.append(", teacherId=").append(teacherId);
        sb.append(", sellerId=").append(sellerId);
        sb.append(", type=").append(type);
        sb.append(", author=").append(author);
        sb.append(", publisher=").append(publisher);
        sb.append(", coverPercentage=").append(coverPercentage);
        sb.append(", content=").append(content);
        sb.append(", price=").append(price);
        sb.append(", singlePrint=").append(singlePrint);
        sb.append(", dealMethod=").append(dealMethod);
        sb.append(", commodityId=").append(commodityId);
        sb.append(", chapters=").append(chapters);
        sb.append(", paperSize=").append(paperSize);
        sb.append(", newDegree=").append(newDegree);
        sb.append(", unit=").append(unit);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", imageUrl=").append(imageUrl);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}