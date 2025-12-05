package com.project.soundlink.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private Integer age;
    private String avatarUrl = "";
    private Boolean verified = false;

    public User(String username, String email, String password, Integer age) {
        this.name = username;
        this.email = email;
        this.password = password;
        this.age = age;
    }

    public User(String username, String email, String password, Integer age, String avatarUrl, Boolean verified) {
        this.name = username;
        this.email = email;
        this.password = password;
        this.age = age;
        this.avatarUrl = avatarUrl;
        this.verified = verified;
    }
}
