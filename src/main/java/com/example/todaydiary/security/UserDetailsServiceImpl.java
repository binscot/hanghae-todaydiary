package com.example.todaydiary.security;

import com.example.todaydiary.user.UserRepository;
import com.example.todaydiary.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(String user_id) throws UsernameNotFoundException {
        User user = userRepository.findByUser_id(user_id)
                .orElseThrow(() -> new UsernameNotFoundException("Can't find " + user_id));

        return new UserDetailsImpl(user);
    }
}
