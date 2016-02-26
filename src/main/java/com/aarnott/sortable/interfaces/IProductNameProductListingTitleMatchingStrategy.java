package com.aarnott.sortable.interfaces;

import com.aarnott.sortable.Product;
import com.aarnott.sortable.ProductListing;


public interface IProductNameProductListingTitleMatchingStrategy {
    boolean matches(Product product, ProductListing productListing);
}
