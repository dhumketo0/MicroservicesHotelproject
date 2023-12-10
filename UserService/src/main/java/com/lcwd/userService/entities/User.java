package com.lcwd.userService.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name="micro_users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column(name="ID")
    private String user_id;
    @Column(name="Name", length = 20)
    private String name;
    @Column(name="Email")
    private String email;
    @Column(name = "About")
    private String about;
    @Transient
    private List<Rating> ratings;
}
