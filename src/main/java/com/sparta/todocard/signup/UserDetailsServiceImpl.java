package com.sparta.todocard.signup;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final SignupRepository signupRepository;

    public UserDetailsServiceImpl(SignupRepository signupRepository) {
        this.signupRepository = signupRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = signupRepository.findUserByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Not Found " + username));

        return new com.sparta.todocard.signup.UserDetailsImpl(user);
    }
}