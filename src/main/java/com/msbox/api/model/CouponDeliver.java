package com.msbox.api.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * Description: CouponDeliver
 * Author: guost
 * Date: 2015-04-01 18:41
 */
public class CouponDeliver implements Serializable{
    private Integer id;
    private Integer coupon_id;
    private Integer number;
    private Long creation_time;
    private Integer bu_id;
    private Integer uid;
    private Double balance;
    private String mobile;
    private Integer employee_id;

    public Integer getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(Integer coupon_id) {
        this.coupon_id = coupon_id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Long getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(Long creation_time) {
        this.creation_time = creation_time;
    }

    public Integer getBu_id() {
        return bu_id;
    }

    public void setBu_id(Integer bu_id) {
        this.bu_id = bu_id;
    }
}
