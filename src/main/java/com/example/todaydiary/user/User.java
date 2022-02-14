package com.example.todaydiary.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class User {

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    // nullable: null 허용 여부
    // unique: 중복 허용 여부 (false 일때 중복 허용)
    @Column(nullable = false, unique = true)
    private String user_id;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private String user_profile;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

//    @ManyToOne
//    @JoinColumn(name="restaurant_id", nullable = false)
//    private Restaurant restaurant;

    public User(String user_id, String nickname, String password, String user_profile, UserRoleEnum role) {
        this.user_id = user_id;
        this.nickname = nickname;
        this.password = password;
        this.user_profile = user_profile;
        this.role = role;
    }
}
