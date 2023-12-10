package com.lcwd.rating.services;

import com.lcwd.rating.entities.Rating;
import com.lcwd.rating.repositories.RatingRepository;

import java.util.List;

public interface RatingService {

    Rating create(Rating rating);
    List<Rating> getRatings();
    List<Rating> getRatingByUserid(String user_id);
    List<Rating> getRatingByHotelId(String hotel_id);

}
