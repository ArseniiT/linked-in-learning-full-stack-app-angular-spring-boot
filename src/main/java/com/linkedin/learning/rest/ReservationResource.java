package com.linkedin.learning.rest;

import com.linkedin.learning.converter.RoomEntityToReservationResponseConverter;
import com.linkedin.learning.converter.RoomEntityToReservationResponseFunction;
import com.linkedin.learning.entity.RoomEntity;
import com.linkedin.learning.model.request.ReservationRequest;
import com.linkedin.learning.model.response.ReservationResponse;
import com.linkedin.learning.repository.PageableRoomRepository;
import com.linkedin.learning.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(ResourseConstants.ROOM_RESERVATION_V1)
public class ReservationResource {


    @Autowired
    PageableRoomRepository pageableRoomRepository;
    @Autowired
    RoomRepository roomRepository;

    //----------------------------GET AVAILABLE ROOMS-------------------------------------
    @RequestMapping(path = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Page<ReservationResponse> getAvailableRooms(
            @RequestParam(value = "checkin")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate checkin,
            @RequestParam(value = "checkout")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate checkout, Pageable pageable ) {

        Page<RoomEntity> roomEntityList = pageableRoomRepository.findAll(pageable);

        //return new ResponseEntity<>(new ReservationResponse(), HttpStatus.OK);
        return roomEntityList.map(new RoomEntityToReservationResponseFunction()) ;
    }

    //----------------------------GET ROOM BY ID-------------------------------------
    @RequestMapping(path = "/{roomId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<RoomEntity> getRoomById(
            @PathVariable
            Long roomId) {

        RoomEntity roomEntity = roomRepository.getById(roomId);

        return new ResponseEntity<>(roomEntity, HttpStatus.OK);
    }

    //----------------------------CREATE RESERVATION-------------------------------------
    @RequestMapping(path = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
                    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ReservationResponse> createReservation(
            @RequestBody
                    ReservationRequest reservationRequest) {
        return new ResponseEntity<>(new ReservationResponse(), HttpStatus.CREATED);
    }

    //----------------------------UPDATE RESERVATION-------------------------------------
    @RequestMapping(path = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
                    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ReservationResponse> updateReservation(
            @RequestBody
                    ReservationRequest reservationRequest) {
        return new ResponseEntity<>(new ReservationResponse(), HttpStatus.OK);
    }

    //----------------------------DELETE RESERVATION-------------------------------------
    @RequestMapping(path = "/{reservationId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteReservation(
            @PathVariable
            long reservationId) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
