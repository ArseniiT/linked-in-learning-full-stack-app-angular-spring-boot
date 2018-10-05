package com.linkedin.learning.repository;

import com.linkedin.learning.entity.RoomEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends CrudRepository<RoomEntity, Long> {

    //List<RoomEntity> getById(Long id);
    @Query
    //Optional<RoomEntity> findById(Long id);
    RoomEntity findById(Long id);
//    RoomEntity getById(Long id);

}
