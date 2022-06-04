package com.kr.ecomarket.models;

import javax.persistence.*;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Lob
    @Column
    private String coverLink, itemName, itemDesc;
    private int price, categoryId;
//    private boolean isRead;

//    public String getShopLink() {
//        return shopLink;
//    }
//
//    public void setShopLink(String shopLink) {
//        this.shopLink = shopLink;
//    }

    private String shopLink;

    public Item() {
    }

    public Item(String itemName, String itemDesc, String coverLink, int price, int categoryId) {
        this.coverLink = coverLink;
        this.itemName = itemName;
        this.itemDesc = itemDesc;
        this.price = price;
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public int getСategoryId() {
        return categoryId;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setСategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCoverLink() {
        return coverLink;
    }

    public void setCoverLink(String coverLink) {
        this.coverLink = coverLink;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }
}
