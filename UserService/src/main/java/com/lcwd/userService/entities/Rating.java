package com.lcwd.userService.entities;

import lombok.Data;

@Data
public class Rating {
private String ratingId;
private String userId;
private int rating;
private String hotelId;
private String feedback;

private Hotel hotel;
}
