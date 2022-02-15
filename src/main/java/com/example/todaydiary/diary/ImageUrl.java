package com.example.todaydiary.diary;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor

public class ImageUrl {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String imageUrl;

    @Builder
    public ImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }

}



