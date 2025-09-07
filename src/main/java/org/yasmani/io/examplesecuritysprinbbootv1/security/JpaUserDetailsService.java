package org.yasmani.io.examplesecuritysprinbbootv1.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.yasmani.io.examplesecuritysprinbbootv1.entity.UserEntity;
import org.yasmani.io.examplesecuritysprinbbootv1.repository.UserRepository;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.List;
public class JpaUserDetailsService
    implements UserDetailsService {

        private final UserRepository repo;

    public JpaUserDetailsService(UserRepository repo) {
            this.repo = repo;
        }

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            UserEntity user = repo.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

            List<SimpleGrantedAuthority> authorities = Arrays.stream(user.getRoles().split(","))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role)) // añadimos prefijo ROLE_
                    .collect(Collectors.toList());

            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    user.isEnabled(),
                    true, true, true,
                    authorities
            );
        }
}
