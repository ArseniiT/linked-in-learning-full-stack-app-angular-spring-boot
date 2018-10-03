package com.linkedin.learning.converter;

import com.linkedin.learning.entity.RoomEntity;
import com.linkedin.learning.model.Links;
import com.linkedin.learning.model.Self;
import com.linkedin.learning.model.response.ReservationResponse;
import com.linkedin.learning.rest.ResourseConstants;
import org.springframework.core.convert.converter.Converter;

public class RoomEntityToReservationResponseConverter implements Converter<RoomEntity, ReservationResponse> {
    @Override
    public ReservationResponse convert(RoomEntity source) {

        ReservationResponse reservationResponse = new ReservationResponse();
        reservationResponse.setRoomNumber(source.getRoomNumber());
        reservationResponse.setPrice(Integer.valueOf(source.getPrice()));

        Links links = new Links();
        Self self = new Self();
        self.setRef(ResourseConstants.ROOM_RESERVATION_V1 + "/" + source.getId());

        reservationResponse.setLink(links);

        return reservationResponse;
    }
}
