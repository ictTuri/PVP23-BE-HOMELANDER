package com.codeonmars.usersms.security.auth;

import com.codeonmars.usersms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String credential) throws UsernameNotFoundException {

        if (credential != null) {
            var userEntity = userRepo.findByUsername(credential);
            if (userEntity.isPresent()) {
                return new ApplicationUser(userEntity.get());
            }
        }
        throw new UsernameNotFoundException("User: " + credential + " not found!");
    }

}
