package hh.lemmikkikauppa.lemmikkikauppaprojekti.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hh.lemmikkikauppa.lemmikkikauppaprojekti.domain.AppUser;
import hh.lemmikkikauppa.lemmikkikauppaprojekti.domain.AppUserRepository;


@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final AppUserRepository userRepository;

    public UserDetailServiceImpl(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser curruser = userRepository.findByUsername(username);

        if (curruser == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        UserDetails user = new org.springframework.security.core.userdetails.User(
            curruser.getUsername(),
            curruser.getPasswordHash(),
            AuthorityUtils.createAuthorityList(curruser.getRole())
        );

        return user;
    }
}

