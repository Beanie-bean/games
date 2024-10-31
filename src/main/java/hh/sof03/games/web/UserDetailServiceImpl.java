package hh.sof03.games.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hh.sof03.games.domain.AppUser;
import hh.sof03.games.domain.AppUserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService  {
	private final AppUserRepository repository;

    @Autowired
	public UserDetailServiceImpl(AppUserRepository userRepository) {
		this.repository = userRepository;
	}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {   
    	AppUser curruser = repository.findByUsername(username);
        UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPasswordHash(), 
        		AuthorityUtils.createAuthorityList(curruser.getRole()));
        return user;
    }   
} 
