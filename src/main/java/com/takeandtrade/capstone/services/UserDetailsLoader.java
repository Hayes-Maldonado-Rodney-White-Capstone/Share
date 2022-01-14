package com.takeandtrade.capstone.services;

import com.takeandtrade.capstone.models.User;
import com.takeandtrade.capstone.models.UserWithRoles;
import com.takeandtrade.capstone.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsLoader implements UserDetailsService{
    private final UserRepository users;

    public UserDetailsLoader(UserRepository users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = users.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user found for " + username);
        }

        return new UserWithRoles(user);
    }

}
