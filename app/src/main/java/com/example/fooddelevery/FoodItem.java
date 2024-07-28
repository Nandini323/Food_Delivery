package com.example.fooddelevery;

public class FoodItem {
    private String name;
    private String imgurl;

    private String description;
    private int price;
    private int deliveryTime;
    private double distance;
    private double rating;
    private int ratingsCount;
    private boolean isBestSeller;
    private boolean isPromo;

    public FoodItem(String name,String imgurl, String description, int price, int deliveryTime, double distance, double rating, int ratingsCount, boolean isBestSeller, boolean isPromo) {
        this.name = name;
        this.imgurl = imgurl;
        this.description = description;
        this.price = price;
        this.deliveryTime = deliveryTime;
        this.distance = distance;
        this.rating = rating;
        this.ratingsCount = ratingsCount;
        this.isBestSeller = isBestSeller;
        this.isPromo = isPromo;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getPrice() { return price; }
    public int getDeliveryTime() { return deliveryTime; }
    public double getDistance() { return distance; }
    public double getRating() { return rating; }
    public int getRatingsCount() { return ratingsCount; }
    public boolean isBestSeller() { return isBestSeller; }
    public boolean isPromo() { return isPromo; }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
}
