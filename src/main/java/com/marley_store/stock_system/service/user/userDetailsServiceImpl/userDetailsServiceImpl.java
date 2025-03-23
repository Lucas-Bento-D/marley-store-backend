package com.marley_store.stock_system.service.user.userDetailsServiceImpl;

import com.marley_store.stock_system.model.user.User;
import com.marley_store.stock_system.model.user.userDetailsImpl.UserDetailsImpl;
import com.marley_store.stock_system.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class userDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("usuario nao encontrado"));
        return new UserDetailsImpl(user);
    }

}
