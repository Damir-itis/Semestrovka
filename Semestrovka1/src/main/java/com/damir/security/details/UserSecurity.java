package com.damir.security.details;

import com.damir.models.User;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;


@Getter
@RequiredArgsConstructor
public class UserSecurity implements UserDetails {

    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return user.getPasswordHash();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.getStatus().equals(User.Status.ACTIVE);
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getStatus().equals(User.Status.ACTIVE);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.getStatus().equals(User.Status.ACTIVE);
    }

    @Override
    public boolean isEnabled() {
        return user.getStatus().equals(User.Status.ACTIVE);
    }

    public String getFirstName(){
        return user.getFirstName();
    }

}
