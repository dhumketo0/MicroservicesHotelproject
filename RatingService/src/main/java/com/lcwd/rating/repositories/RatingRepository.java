package com.lcwd.rating.repositories;

import com.lcwd.rating.entities.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RatingRepository extends MongoRepository<Rating,String> {
    //custom finder methods
    List<Rating> findByUserId(String user_id);
    List<Rating> findByHotelId(String hotel_id);

}
