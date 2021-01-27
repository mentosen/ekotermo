package ekotermo.service.security


import ekotermo.data.domain.User
import ekotermo.data.service.UserDataService
import ekotermo.service.security.model.UserPrincipal
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CustomUserDetailsService implements UserDetailsService{

    @Autowired UserDataService userDataService;

    @Override
    @Transactional
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Let people login with either username or email
        User user = userDataService.findByUserName(username)

        return UserPrincipal.create(user);
    }

    // This method is used by JWTAuthenticationFilter
    @Transactional
    UserDetails loadUserById(String id) {
        User user = userDataService.findById(id)

        return UserPrincipal.create(user);
    }
}
