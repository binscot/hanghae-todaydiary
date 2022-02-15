package com.mini.coffzag.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ReturnUserInfo {
    private boolean ok;
    private String username;
    private String email;

    public ReturnUserInfo(boolean ok, String username, String email) {
        this.ok = ok;
        this.username = username;
        this.email = email;
    }
}
