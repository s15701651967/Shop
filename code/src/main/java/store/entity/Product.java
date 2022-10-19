package com.bistu.store.entity;

import com.bistu.store.controller.BaseController;

import java.io.Serializable;
import java.util.Objects;

public class Product extends BaseEntity implements Serializable {
    private Integer id;
    private Integer categoryId;
    private String itemType;
    private String title;
    private String sellPoint;
    private Long price;
    private Integer num;
    private String image;
    private Integer status;
    private Integer priority;
    private Integer nowSellNum;
    private Integer historySellNum;
    private Integer sellPersons;
    private Integer judgeMark;
    private String sellMethod;
    private Integer businessId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getNowSellNum() {
        return nowSellNum;
    }

    public void setNowSellNum(Integer nowSellNum) {
        this.nowSellNum = nowSellNum;
    }

    public Integer getHistorySellNum() {
        return historySellNum;
    }

    public void setHistorySellNum(Integer historySellNum) {
        this.historySellNum = historySellNum;
    }

    public Integer getSellPersons() {
        return sellPersons;
    }

    public void setSellPersons(Integer sellPersons) {
        this.sellPersons = sellPersons;
    }

    public Integer getJudgeMark() {
        return judgeMark;
    }

    public void setJudgeMark(Integer judgeMark) {
        this.judgeMark = judgeMark;
    }

    public String getSellMethod() {
        return sellMethod;
    }

    public void setSellMethod(String sellMethod) {
        this.sellMethod = sellMethod;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        if (!super.equals(o)) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(categoryId, product.categoryId) &&
                Objects.equals(itemType, product.itemType) &&
                Objects.equals(title, product.title) &&
                Objects.equals(sellPoint, product.sellPoint) &&
                Objects.equals(price, product.price) &&
                Objects.equals(num, product.num) &&
                Objects.equals(image, product.image) &&
                Objects.equals(status, product.status) &&
                Objects.equals(priority, product.priority) &&
                Objects.equals(nowSellNum, product.nowSellNum) &&
                Objects.equals(historySellNum, product.historySellNum) &&
                Objects.equals(sellPersons, product.sellPersons) &&
                Objects.equals(judgeMark, product.judgeMark) &&
                Objects.equals(sellMethod, product.sellMethod) &&
                Objects.equals(businessId, product.businessId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, categoryId, itemType, title, sellPoint, price, num, image, status, priority, nowSellNum, historySellNum, sellPersons, judgeMark, sellMethod, businessId);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", itemType='" + itemType + '\'' +
                ", title='" + title + '\'' +
                ", sellPoint='" + sellPoint + '\'' +
                ", price=" + price +
                ", num=" + num +
                ", image='" + image + '\'' +
                ", status=" + status +
                ", priority=" + priority +
                ", nowSellNum=" + nowSellNum +
                ", historySellNum=" + historySellNum +
                ", sellPersons=" + sellPersons +
                ", judgeMark=" + judgeMark +
                ", sellMethod='" + sellMethod + '\'' +
                ", businessId=" + businessId +
                '}';
    }
}
