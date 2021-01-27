package ekotermo.service.security.model

import com.fasterxml.jackson.annotation.JsonIgnore
import ekotermo.data.domain.User
import ekotermo.data.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserPrincipal implements UserDetails{

    String userId;

    String name;

    String username;

    @JsonIgnore
    String email;

    @JsonIgnore
    String password;

    Collection<? extends GrantedAuthority> authorities;

    @Override
    String getUsername() {
        return username;
    }

    @Override
    String getPassword() {
        return password;
    }

    @Override
    Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    boolean isAccountNonExpired() {
        return true;
    }

    @Override
    boolean isAccountNonLocked() {
        return true;
    }

    @Override
    boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    boolean isEnabled() {
        return true;
    }

    static final UserPrincipal instance(String userId, List<SimpleGrantedAuthority> authorities){
        UserPrincipal principal = new UserPrincipal()
        principal.userId = userId
        principal.authorities = authorities
        return principal
    }

    static UserPrincipal create(User user, List<SimpleGrantedAuthority> authorities) {

        return new UserPrincipal(
                userId: user.id,
                name: user.name,
                email: user.email,
                password: user.password,
                authorities: authorities
        );
    }
}
