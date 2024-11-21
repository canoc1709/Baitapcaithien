package me.trihung.services.impl;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import me.trihung.entity.Role;
import me.trihung.entity.Users;
import me.trihung.repository.UserRepository;
import me.trihung.services.MyUserService;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    
    public CustomUserDetailsService(UserRepository userRepository) {
    	this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch the user from the repository by either username or email
    	System.out.println("load");
        Users user = userRepository.findByUsernameOrEmail(username, username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username or email: " + username));

        // Return a new instance of your custom UserDetails implementation
        return new org.springframework.security.core.userdetails.User(user.getEmail(), 
        		user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    // This method maps Roles to GrantedAuthority objects (needed by Spring Security)
    @SuppressWarnings("unused")
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
        return roles.stream()
                    .map(role -> new SimpleGrantedAuthority(role.getName()))  
                    .collect(Collectors.toList());
    }
}
