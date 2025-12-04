package com.brasedu.crm.braseducrm.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.brasedu.crm.braseducrm.repositories.SalesmanRepository;
import com.brasedu.crm.braseducrm.userdetails.SalesmanUserDetails;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    private final SalesmanRepository salesmanRepository;

    @Override
    public SalesmanUserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        var salesman = salesmanRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Salesman not found"));

        return new SalesmanUserDetails(salesman);
    }
}
