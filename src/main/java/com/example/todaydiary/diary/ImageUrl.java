<<<<<<< HEAD
//package com.example.todaydiary.diary;
//
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//@Getter
//@NoArgsConstructor
//
//public class ImageUrl {
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Id
//    private Long id;
//
//    @Column(nullable = false)
//    private String imageUrl;
//
//    @Builder
//    public ImageUrl(String imageUrl){
//
//        this.imageUrl = imageUrl;
//    }
//
//}
//
//
//
=======
package com.example.todaydiary.diary;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor

public class ImageUrl {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String imageUrl;

}



>>>>>>> 724bf1c2091b2579a0277906d891d51694ed9cd9
