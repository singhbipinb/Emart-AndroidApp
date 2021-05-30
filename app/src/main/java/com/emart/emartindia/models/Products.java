package com.emart.emartindia.models;

import com.google.gson.annotations.SerializedName;

/*
 * @author Bipin Singh
 */

public class Products {

    @SerializedName("_id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("image")
    private String Image;

    @SerializedName("brand")
    private String Brand;

    @SerializedName("category")
    private String Category;

    @SerializedName("numReviews")
    private int numReviews;

    @SerializedName("price")
    private String Price;

    @SerializedName("rating")
    private double Rating;

    @SerializedName("countInStock")
    private String countInStock;
    @SerializedName("description")
    private String Description;
    @SerializedName("reviews")
    private Reviews[] reviews;

    public Products() {

    }

    public Products(String id, String name, String image, String brand, String category, int numReviews, String price, double rating, String countInStock, String description, Reviews reviews[]) {
        this.id = id;
        this.name = name;
        Image = image;
        Brand = brand;
        Category = category;
        this.numReviews = numReviews;
        Price = price;
        Rating = rating;
        this.countInStock = countInStock;
        Description = description;
        this.reviews = reviews;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public int getNumReviews() {
        return numReviews;
    }

    public void setNumReviews(int numReviews) {
        this.numReviews = numReviews;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public double getRating() {
        return Rating;
    }

    public void setRating(double rating) {
        Rating = rating;
    }

    public String getCountInStock() {
        return countInStock;
    }

    public void setCountInStock(String countInStock) {
        this.countInStock = countInStock;
    }

    public Reviews[] getReviews() {
        return reviews;
    }

    public void setReviews(Reviews[] reviews) {
        this.reviews = reviews;
    }
}
