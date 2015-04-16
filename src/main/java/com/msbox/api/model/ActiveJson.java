package com.msbox.api.model;

import java.io.Serializable;

/**
 * Author: guost
 * Date: 2015-04-08 11:12
 */
public class ActiveJson implements Serializable{
    private String active;
    private String title;
    private double money;

    public String getActive() {
        return active;
    }

    public void setActive(String activeId) {
        this.active = activeId;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "ActiveJson{" +
                "active='" + active + '\'' +
                ", title='" + title + '\'' +
                ", money=" + money +
                '}';
    }
}
