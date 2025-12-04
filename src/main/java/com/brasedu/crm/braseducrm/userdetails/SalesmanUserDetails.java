package com.brasedu.crm.braseducrm.userdetails;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.brasedu.crm.braseducrm.entities.SalesmanEntity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SalesmanUserDetails implements UserDetails {

    private final SalesmanEntity salesman;

    @Override
    public String getUsername() {
        return this.salesman.getEmail();
    }

    @Override
    public String getPassword() {
        return this.salesman.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
