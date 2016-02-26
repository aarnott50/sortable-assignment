package com.aarnott.sortable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.aarnott.sortable.interfaces.IGroupedProductListingAggregationStrategy;
import com.aarnott.sortable.interfaces.IProductNameProductListingTitleMatchingStrategy;


public class SimpleGroupedProductListingAggregationStrategy implements IGroupedProductListingAggregationStrategy {

    private final IProductNameProductListingTitleMatchingStrategy titleMatchingStrategy;

    public SimpleGroupedProductListingAggregationStrategy(final IProductNameProductListingTitleMatchingStrategy titleMatchingStrategy) {
        this.titleMatchingStrategy = titleMatchingStrategy;
    }

    @Override
    public List<GroupedProductListing> getAggregatedGroupProductListings(
            final List<Product> products,
            final List<ProductListing> productListings) {

        final Map<Product, GroupedProductListing> groupProductListingsByProduct =
                products.stream()
                    .collect(Collectors.toMap(
                            Function.identity(),
                            product -> new GroupedProductListing(product.getProductName())
                        )
                    );


        for (final ProductListing productListing : productListings) {
            final List<Product> potentialMatches = getPotentialMatches(products, productListing);

            if (potentialMatches.size() == 1) {
                final GroupedProductListing match = groupProductListingsByProduct.get(potentialMatches.get(0));
                match.addProductListing(productListing);
            } else if (potentialMatches.size() > 1) {
                outputMultipleMatches(productListing, potentialMatches);
            }
        }

        return new ArrayList<>(groupProductListingsByProduct.values());
    }

    private List<Product> getPotentialMatches(final List<Product> products, final ProductListing productListing) {
        return products.stream()
                    .filter(product -> titleMatchingStrategy.matches(product, productListing))
                    .collect(Collectors.toList());
    }

    private void outputMultipleMatches(final ProductListing productListing, final List<Product> potentialMatches) {
        //TODO: I'd replace this with actually logging in a real project
    }

}
