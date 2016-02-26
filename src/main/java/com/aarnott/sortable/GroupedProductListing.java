package com.aarnott.sortable;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;


public class GroupedProductListing {

    @SerializedName("product_name")
    private String productName;

    @SerializedName("listings")
    private List<ProductListing> productListings;

    /* package-private for Gson serialization */
    GroupedProductListing() {}

    public GroupedProductListing(final String productName) {
        this.productName = productName;
        productListings = new ArrayList<>();
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(final String productName) {
        this.productName = productName;
    }

    public void addProductListing(final ProductListing productListing) {
        productListings.add(productListing);
    }

    public List<ProductListing> getProductListings() {
        return productListings;
    }

    public void setProductListings(final List<ProductListing> productListings) {
        this.productListings = productListings;
    }

    @Override
    public String toString() {
        return "GroupedProductListings [productName=" + productName + ", productListings=" + productListings + "]";
    }

}
