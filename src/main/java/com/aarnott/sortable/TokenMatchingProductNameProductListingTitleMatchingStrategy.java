package com.aarnott.sortable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.aarnott.sortable.interfaces.IProductNameProductListingTitleMatchingStrategy;

/**
 * This strategy breaks the product name, model, and manufacturer into a set of tokens and the product listing title into a set of tokens
 * (using whitespace and special characters as delimiters). After tokenizing the Strings, the number of shared tokens is counted. If the
 * number of shared tokens meets or exceeds a minimum number of acceptable tokens, then the strategy considers this a match.
 *
 * @author aarnott
 *
 */
public class TokenMatchingProductNameProductListingTitleMatchingStrategy implements IProductNameProductListingTitleMatchingStrategy {

    private static double MINIMUM_MATCHING_TOKEN_RATIO = 0.9;

    private static String TOKEN_DELIMITER_REGEX = "[\\s_/]";
    private static int MINIMUM_TOKEN_LENGTH = 2;

    @Override
    public boolean matches(final Product product, final ProductListing productListing) {
        final Set<String> productTokens = getProductTokens(product);
        final Set<String> productListingTokens = getProductListingTokens(productListing);

        final Set<String> matchingTokens =
                productTokens.stream()
                    .filter(token -> productListingTokens.contains(token))
                    .collect(Collectors.toSet());

        final double matchingTokensSizeDouble = matchingTokens.size();
        final double smallestTokenSetSizeDouble = Math.min(productTokens.size(), productListingTokens.size());
        final double matchedTokenRatio = matchingTokensSizeDouble / smallestTokenSetSizeDouble;

        return matchedTokenRatio >= MINIMUM_MATCHING_TOKEN_RATIO;
    }

    private Set<String> getProductTokens(final Product product) {
        final String productName = product.getProductName();
        final String productModel = product.getModel();
        final String productManufacturer = product.getManufacturer();

        final Set<String> tokens = new HashSet<>();
        tokens.addAll(Arrays.asList(productName.split(TOKEN_DELIMITER_REGEX)));
        tokens.addAll(Arrays.asList(productModel.split(TOKEN_DELIMITER_REGEX)));
        tokens.addAll(Arrays.asList(productManufacturer.split(TOKEN_DELIMITER_REGEX)));

        return tokens.stream()
            .filter(token -> token.length() >= MINIMUM_TOKEN_LENGTH)
            .collect(Collectors.toSet());
    }

    private Set<String> getProductListingTokens(final ProductListing productListing) {
        final String productTitle = productListing.getTitle();

        final Set<String> tokens = new HashSet<>();
        tokens.addAll(Arrays.asList(productTitle.split(TOKEN_DELIMITER_REGEX)));

        return tokens.stream()
            .filter(token -> token.length() >= MINIMUM_TOKEN_LENGTH)
            .collect(Collectors.toSet());
    }

}
