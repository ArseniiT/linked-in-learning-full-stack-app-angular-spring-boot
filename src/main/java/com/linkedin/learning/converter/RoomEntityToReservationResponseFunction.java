package com.linkedin.learning.converter;

import com.linkedin.learning.entity.RoomEntity;
import com.linkedin.learning.model.Links;
import com.linkedin.learning.model.Self;
import com.linkedin.learning.model.response.ReservationResponse;
import com.linkedin.learning.rest.ResourseConstants;

import java.util.function.Function;

public class RoomEntityToReservationResponseFunction implements Function<RoomEntity, ReservationResponse> {

    @Override
    public ReservationResponse apply(RoomEntity roomEntity) {
        ReservationResponse reservationResponse = new ReservationResponse();
        reservationResponse.setRoomNumber(roomEntity.getRoomNumber());
        reservationResponse.setPrice(Integer.valueOf(roomEntity.getPrice()));

        Links links = new Links();
        Self self = new Self();
        self.setRef(ResourseConstants.ROOM_RESERVATION_V1 + "/" + roomEntity.getId());
        links.setSelf(self);

        reservationResponse.setLink(links);

        return reservationResponse;
    }
}
