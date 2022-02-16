package com.example.todaydiary.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReturnCheckId {
    //중복확인이 완료되었을때 오류없을시 리턴
    private Boolean ok;
    private String msg;
}
