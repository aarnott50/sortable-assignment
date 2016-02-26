package com.aarnott.sortable;

import java.time.ZonedDateTime;

import com.google.gson.annotations.SerializedName;


public class Product {

    @SerializedName("product_name")
    private String productName;

    private String manufacturer;

    private String model;

    private String family;

    @SerializedName("announced-date")
    private ZonedDateTime announcedDate;


    public String getProductName() {
        return productName;
    }

    public void setProductName(final String productName) {
        this.productName = productName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(final String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(final String model) {
        this.model = model;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(final String family) {
        this.family = family;
    }

    public ZonedDateTime getAnnouncedDate() {
        return announcedDate;
    }

    public void setAnnouncedDate(final ZonedDateTime announcedDate) {
        this.announcedDate = announcedDate;
    }

    @Override
    public String toString() {
        return "Product [productName=" + productName + ", manufacturer=" + manufacturer + ", model=" + model + ", family=" + family + ", announcedDate=" + announcedDate + "]";
    }

}
