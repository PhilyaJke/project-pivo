package com.obshaga.zapivkom.Security;

import com.obshaga.zapivkom.Entity.UsersEntity;
import com.obshaga.zapivkom.Repo.SignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailServiceImpl")
public class UserDetailServiceImpl implements UserDetailsService {

    private final SignRepository signRepository;

    @Autowired
    public UserDetailServiceImpl(SignRepository signRepository) {
        this.signRepository = signRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersEntity usersEntity = signRepository.findByUsername(username).orElseThrow(() ->
        new UsernameNotFoundException("User doesn't exist"));
        System.out.println(usersEntity.getUsername());
        return SecurityUser.fromUser(usersEntity);
    }
}
