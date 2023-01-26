package org.project.entity.dto;

import java.sql.Timestamp;

public class OrderedBookDTO {
    private String title;
    private String author;
    private Timestamp orderDate;
    private Timestamp returnDate;
    private String subscription;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public Timestamp getReturnDate() {
        return returnDate;
    }
    public void setReturnDate(Timestamp returnDate) {
        this.returnDate = returnDate;
    }

    public String getSubscription() {
        return subscription;
    }
    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }

    @Override
    public String toString() {
        return "OrderedBookDTO{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", orderDate=" + orderDate +
                ", returnDate=" + returnDate +
                ", subscription='" + subscription + '\'' +
                '}';
    }
}