package com.linkedin.learning.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Room")
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private Integer roomNumber;

    @NotNull
    private String price;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<ReservationEntity> reservationEntityList;

    public RoomEntity() {
        super();
    }

    public RoomEntity(@NotNull Integer roomNumber, @NotNull String price) {
        this.roomNumber = roomNumber;
        this.price = price;
    }

    public Long getId() {

        System.out.println(id + " GET ID from roomEntity");
        return id;
    }

    public void setId(Long id) {

        System.out.println(id + " SET ID from roomEntity");                                                                                                                                                System.out.println(id + " SET ID");
        this.id = id;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<ReservationEntity> getReservationEntityList() {
        return reservationEntityList;
    }

    public void setReservationEntityList(List<ReservationEntity> reservationEntityList) {
        this.reservationEntityList = reservationEntityList;
    }

    public void addReservationEntity(ReservationEntity reservationEntity) {
        if(reservationEntityList == null) {
            reservationEntityList = new ArrayList<>();
        }
        reservationEntityList.add(reservationEntity);
    }

}
