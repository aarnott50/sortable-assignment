package com.aarnott.sortable.interfaces;

import java.util.List;

import com.aarnott.sortable.GroupedProductListing;
import com.aarnott.sortable.Product;
import com.aarnott.sortable.ProductListing;


public interface IGroupedProductListingAggregationStrategy {

    List<GroupedProductListing> getAggregatedGroupProductListings(final List<Product> products, List<ProductListing> productListings);
}
