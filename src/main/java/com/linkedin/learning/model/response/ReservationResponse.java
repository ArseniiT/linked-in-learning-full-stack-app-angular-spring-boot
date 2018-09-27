package com.linkedin.learning.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.linkedin.learning.model.Links;

public class ReservationResponse {

    private Long id;
    private Integer roomNumber;
    private Integer price;
    private Links link;

    public Long getId() {
        return id;
    }

    public ReservationResponse() {
    }

    public ReservationResponse(Integer roomNumber, Integer price) {
        this.roomNumber = roomNumber;
        this.price = price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Links getLink() {
        return link;
    }

    public void setLink(Links link) {
        this.link = link;
    }
}
