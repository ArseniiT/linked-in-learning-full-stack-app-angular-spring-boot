package com.linkedin.learning.converter;

import com.linkedin.learning.entity.ReservationEntity;
import com.linkedin.learning.model.request.ReservationRequest;

import java.util.function.Function;

public class ReservationRequestToReservationEntityFunction implements Function<ReservationRequest, ReservationEntity> {
    @Override
    public ReservationEntity apply(ReservationRequest reservationRequest) {

        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setCheckin(reservationRequest.getCheckin());
        reservationEntity.setCheckout(reservationRequest.getCheckout());

        if(reservationRequest.getId() != null) {
            reservationEntity.setId(reservationRequest.getId());
        }

        return reservationEntity;
    }
}
