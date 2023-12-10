package com.lcwd.userService.Services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lcwd.userService.Services.UserService;
import com.lcwd.userService.entities.Hotel;
import com.lcwd.userService.entities.Rating;
import com.lcwd.userService.entities.User;
import com.lcwd.userService.exceptions.ResourceNotFoundException;
import com.lcwd.userService.external.services.HotelService;
import com.lcwd.userService.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HotelService hotelService;
    private Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public User saveUser(User user) {
        String randomUserid= UUID.randomUUID().toString();
        user.setUser_id(randomUserid);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users=userRepository.findAll();
        return users.stream()
                .map(user -> getUSer(user.getUser_id()))
                .collect(Collectors.toList());
    }

    @Override
    public User getUSer(String UserId) {
        User user=userRepository.findById(UserId).orElseThrow(()-> new ResourceNotFoundException("User with the given ID is not available"))    ;
        //fetch Rating from Ratings service
        String url="http://RATING-SERVICE/ratings/users/"+user.getUser_id();
        Rating[] ratingsOfUsers= restTemplate.getForObject(url, Rating[].class);
        List<Rating> ratings= Arrays.stream(ratingsOfUsers).toList();
        logger.info("{}",ratings);
        List<Rating> ratingList= ratings.stream().map(rating->{
            System.out.println(rating.getHotelId());
            //ResponseEntity<Hotel> forEntity=restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
            Hotel hotel=hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());
        user.setRatings(ratingList);
        return user;
    }
}
