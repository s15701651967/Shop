package com.bistu.store.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class PayVO implements Serializable {
    private Integer uid;
    private Integer pid;
    private Integer oid;
    private Date payTime;
    private Integer status;
    private Integer num;
    private Integer priority;
    private String title;
    private Integer pStatus;
    private Integer businessId;
    private Integer price;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "PayVO{" +
                "uid=" + uid +
                ", pid=" + pid +
                ", oid=" + oid +
                ", payTime=" + payTime +
                ", status=" + status +
                ", num=" + num +
                ", priority=" + priority +
                ", title='" + title + '\'' +
                ", pStatus=" + pStatus +
                ", businessId=" + businessId +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PayVO)) return false;
        PayVO payVO = (PayVO) o;
        return Objects.equals(getUid(), payVO.getUid()) &&
                Objects.equals(getPid(), payVO.getPid()) &&
                Objects.equals(getOid(), payVO.getOid()) &&
                Objects.equals(getPayTime(), payVO.getPayTime()) &&
                Objects.equals(getStatus(), payVO.getStatus()) &&
                Objects.equals(getNum(), payVO.getNum()) &&
                Objects.equals(getPriority(), payVO.getPriority()) &&
                Objects.equals(getTitle(), payVO.getTitle()) &&
                Objects.equals(getpStatus(), payVO.getpStatus()) &&
                Objects.equals(getBusinessId(), payVO.getBusinessId()) &&
                Objects.equals(price, payVO.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUid(), getPid(), getOid(), getPayTime(), getStatus(), getNum(), getPriority(), getTitle(), getpStatus(), getBusinessId(), price);
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

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getpStatus() {
        return pStatus;
    }

    public void setpStatus(Integer pStatus) {
        this.pStatus = pStatus;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

}
