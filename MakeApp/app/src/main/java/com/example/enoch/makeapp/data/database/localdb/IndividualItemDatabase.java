package com.example.enoch.makeapp.data.database.localdb;

import io.realm.RealmObject;

/**
 * Created by mainza1992 on 29/10/2017.
 */

public class IndividualItemDatabase extends RealmObject{

    private int id;

    private String brand;

    private String name;

    private String price;

    private String imageLink;

    private String productLink;

    private String websiteLink;

    private String description;

    private double rating;

    private String category;

    private String productType;

  //  private List<Object> tagList = null;

    private String createdAt;

    private String updatedAt;

    private String productApiUrl;

    public IndividualItemDatabase() {
    }

    public IndividualItemDatabase(Integer id, String brand, String name, String price, String imageLink,
                                  String productLink, String websiteLink, String description, double rating,
                                  String category, String productType, String createdAt,
                                  String updatedAt, String productApiUrl) {
        this.id = id;
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.imageLink = imageLink;
        this.productLink = productLink;
        this.websiteLink = websiteLink;
        this.description = description;
        this.rating = rating;
        this.category = category;
        this.productType = productType;
        //this.tagList = tagList;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.productApiUrl = productApiUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getProductLink() {
        return productLink;
    }

    public void setProductLink(String productLink) {
        this.productLink = productLink;
    }

    public String getWebsiteLink() {
        return websiteLink;
    }

    public void setWebsiteLink(String websiteLink) {
        this.websiteLink = websiteLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

//    public List<Object> getTagList() {
//        return tagList;
//    }
//
//    public void setTagList(List<Object> tagList) {
//        this.tagList = tagList;
//    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getProductApiUrl() {
        return productApiUrl;
    }

    public void setProductApiUrl(String productApiUrl) {
        this.productApiUrl = productApiUrl;
    }
}
