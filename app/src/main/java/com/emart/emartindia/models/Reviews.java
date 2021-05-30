package com.emart.emartindia.models;

import com.google.gson.annotations.SerializedName;

/*
 * @author Bipin Singh
 */

public class Reviews {

    @SerializedName("name")
    String Reviewer;

    @SerializedName("rating")
    int Rating;

    @SerializedName("comment")
    String Comment;

    @SerializedName("user")
    String ReviewerID;

    public Reviews(String reviewer, int rating, String comment, String reviewerID) {
        Reviewer = reviewer;
        Rating = rating;
        Comment = comment;
        ReviewerID = reviewerID;
    }

    public String getReviewer() {
        return Reviewer;
    }

    public void setReviewer(String reviewer) {
        Reviewer = reviewer;
    }

    public int getRating() {
        return Rating;
    }

    public void setRating(int rating) {
        Rating = rating;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public String getReviewerID() {
        return ReviewerID;
    }

    public void setReviewerID(String reviewerID) {
        ReviewerID = reviewerID;
    }
}
