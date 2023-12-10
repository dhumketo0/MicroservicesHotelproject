package com.lcwd.rating.controllers;

import com.lcwd.rating.entities.Rating;
import com.lcwd.rating.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingControllers {
    //create rating
    @Autowired
    private RatingService ratingService;
    @PostMapping
    public ResponseEntity<Rating> create(@RequestBody Rating rating){
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.create(rating));
    }

    @GetMapping
    public  ResponseEntity<List<Rating>> getAllRatings()
    {
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatings());
    }
    @GetMapping("/users/{user_id}")
    public  ResponseEntity<List<Rating>> getRatingsByUserID(@PathVariable String user_id){
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatingByUserid(user_id));
    }
    @GetMapping("/hotels/{hotel_id}")
    public  ResponseEntity<List<Rating>> getRatingsByHotelID(@PathVariable String hotel_id){
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatingByUserid(hotel_id));
    }

}
