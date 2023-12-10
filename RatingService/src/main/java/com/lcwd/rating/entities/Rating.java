package com.lcwd.rating.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("use_ratings")
public class Rating {
    @Id
    private String ratingId;
    private String userId;
    private int rating;
    private String hotelId;
    private String feedback;

    private Hotel hotel;
}
