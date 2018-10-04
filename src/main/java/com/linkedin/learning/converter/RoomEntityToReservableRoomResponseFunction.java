package com.linkedin.learning.converter;

import com.linkedin.learning.entity.RoomEntity;
import com.linkedin.learning.model.Links;
import com.linkedin.learning.model.Self;
import com.linkedin.learning.model.response.ReservableRoomResponse;
import com.linkedin.learning.rest.ResourseConstants;

import java.util.function.Function;

public class RoomEntityToReservableRoomResponseFunction implements Function<RoomEntity, ReservableRoomResponse> {

    @Override
    public ReservableRoomResponse apply(RoomEntity roomEntity) {
        ReservableRoomResponse reservableRoomResponse = new ReservableRoomResponse();
        reservableRoomResponse.setRoomNumber(roomEntity.getRoomNumber());
        reservableRoomResponse.setPrice(Integer.valueOf(roomEntity.getPrice()));

        Links links = new Links();
        Self self = new Self();
        self.setRef(ResourseConstants.ROOM_RESERVATION_V1 + "/" + roomEntity.getId());
        links.setSelf(self);

        reservableRoomResponse.setLink(links);

        return reservableRoomResponse;
    }
}
