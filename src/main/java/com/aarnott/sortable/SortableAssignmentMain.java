package com.aarnott.sortable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.aarnott.sortable.interfaces.IGroupedProductListingAggregationStrategy;
import com.aarnott.sortable.util.IsoZonedDateTimeConverter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class SortableAssignmentMain {

    private final Gson gson;
    private final Path productsFile;
    private final Path listingsFile;

    private List<Product> products;
    private List<ProductListing> productListings;

    public SortableAssignmentMain(
            final GsonBuilder gsonBuilder,
            final Path productsFile,
            final Path listingsFile) {

        gson = gsonBuilder.create();
        this.productsFile = productsFile;
        this.listingsFile = listingsFile;
    }

    public void initialize() {
        products = getProductsFromProductsFile();
        productListings = getProductListingsFromListingsFile();
    }

    public List<GroupedProductListing> aggregateProductListings(final IGroupedProductListingAggregationStrategy aggregationStrategy) {

//        final List<GroupedProductListing> aggregatedProductListings =
//                productsMappedByName.values()
//                    .stream()
//                    .filter(product -> product.getProductName().equals(anObject))
//                    .map(product -> new GroupedProductListing(product.getProductName()))
//                    .distinct()
//                    .collect(Collectors.toList());

//        final List<GroupedProductListing> aggregatedProductListings =
//                productsMappedByName.values()
//                    .stream()
//                    .filter(product -> (
//                            productListings.stream()
//                                .filter(productListing -> productListing.getTitle().equals(product.getProductName())
//                                .count())) > 1)
//                    .map(product -> new GroupedProductListing(product.getProductName()))
//                    .distinct()
//                    .collect(Collectors.toList());
//
//
//        return aggregatedProductListings;

        return aggregationStrategy.getAggregatedGroupProductListings(products, productListings);
    }

    private List<Product> getProductsFromProductsFile() {
        try (Stream<String> productsFileStream = Files.lines(productsFile)) {
            final List<Product> parsedProducts = productsFileStream
                .map(productJsonText -> gson.fromJson(productJsonText, Product.class))
                .collect(Collectors.toList());

            return parsedProducts;
        } catch (final IOException e) {
            throw new RuntimeException("Unable to read from products file", e);
        }
    }

    private List<ProductListing> getProductListingsFromListingsFile() {
        try (Stream<String> listingsFileStream = Files.lines(listingsFile)) {
            final List<ProductListing> parsedProductListings = listingsFileStream
                .map(productListingJsonText -> gson.fromJson(productListingJsonText, ProductListing.class))
                .collect(Collectors.toList());

            return parsedProductListings;
        } catch (final IOException e) {
            throw new RuntimeException("Unable to read from listings file", e);
        }
    }

    public static void main(final String[] args) {
        if(args == null || args.length != 2) {
            System.err.println("The first argument must be the products file and the second must be the listings file!");
            return;
        }

        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ZonedDateTime.class, new IsoZonedDateTimeConverter());

        final String productsFileName = args[0];
        final String listingsFileName = args[1];

        final Path productsFile = Paths.get(productsFileName);
        final Path listingsFile = Paths.get(listingsFileName);

        final SortableAssignmentMain sortableAssignmentMain = new SortableAssignmentMain(gsonBuilder, productsFile, listingsFile);
        sortableAssignmentMain.initialize();

        final List<GroupedProductListing> aggregatedProductListings = sortableAssignmentMain.aggregateProductListings(
                new SimpleGroupedProductListingAggregationStrategy(new TokenMatchingProductNameProductListingTitleMatchingStrategy()));


        final Gson gsonOutput = new Gson();

        for (final GroupedProductListing groupedProductListing : aggregatedProductListings) {
            System.out.println(gsonOutput.toJson(groupedProductListing));
        }

    }

}