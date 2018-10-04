package com.linkedin.learning.converter;

import com.linkedin.learning.entity.ReservationEntity;
import com.linkedin.learning.model.response.ReservationResponse;

import java.util.function.Function;

public class ReservationEntityToReservationResponseFunction implements Function<ReservationEntity, ReservationResponse> {
    @Override
    public ReservationResponse apply(ReservationEntity reservationEntity) {

        ReservationResponse reservationResponse = new ReservationResponse();
        reservationResponse.setCheckin(reservationEntity.getCheckin());
        reservationResponse.setCheckout(reservationEntity.getCheckout());

        if (reservationEntity.getId() != null) {
            reservationResponse.setId(reservationEntity.getId());
        }

        return reservationResponse;
    }
}
