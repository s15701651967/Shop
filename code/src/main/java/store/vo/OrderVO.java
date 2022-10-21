package com.bistu.store.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class OrderVO implements Serializable {
    private Integer uid;
    private Integer pid;
    private Integer oid;
    private Integer businessId;
    private Long price;
    private Integer num;
    private Date orderTime;
    private Date payTime;

    private String recvName;
    private String title;
    private Long realPrice;
    private String image;
    private Integer pStatus;

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderVO)) return false;
        OrderVO orderVO = (OrderVO) o;
        return Objects.equals(getUid(), orderVO.getUid()) &&
                Objects.equals(getPid(), orderVO.getPid()) &&
                Objects.equals(getOid(), orderVO.getOid()) &&
                Objects.equals(getBusinessId(), orderVO.getBusinessId()) &&
                Objects.equals(getPrice(), orderVO.getPrice()) &&
                Objects.equals(getNum(), orderVO.getNum()) &&
                Objects.equals(getOrderTime(), orderVO.getOrderTime()) &&
                Objects.equals(payTime, orderVO.payTime) &&
                Objects.equals(getRecvName(), orderVO.getRecvName()) &&
                Objects.equals(getTitle(), orderVO.getTitle()) &&
                Objects.equals(getRealPrice(), orderVO.getRealPrice()) &&
                Objects.equals(getImage(), orderVO.getImage()) &&
                Objects.equals(getpStatus(), orderVO.getpStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUid(), getPid(), getOid(), getBusinessId(), getPrice(), getNum(), getOrderTime(), payTime, getRecvName(), getTitle(), getRealPrice(), getImage(), getpStatus());
    }

    @Override
    public String toString() {
        return "OrderVO{" +
                "uid=" + uid +
                ", pid=" + pid +
                ", oid=" + oid +
                ", businessId=" + businessId +
                ", price=" + price +
                ", num=" + num +
                ", orderTime=" + orderTime +
                ", payTime=" + payTime +
                ", recvName='" + recvName + '\'' +
                ", title='" + title + '\'' +
                ", realPrice=" + realPrice +
                ", image='" + image + '\'' +
                ", pStatus=" + pStatus +
                '}';
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
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

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getRecvName() {
        return recvName;
    }

    public void setRecvName(String recvName) {
        this.recvName = recvName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(Long realPrice) {
        this.realPrice = realPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getpStatus() {
        return pStatus;
    }

    public void setpStatus(Integer pStatus) {
        this.pStatus = pStatus;
    }

}
