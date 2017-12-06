package koreatech.cse.service;

import koreatech.cse.domain.Authority;
import koreatech.cse.domain.User;
import koreatech.cse.repository.AuthorityMapper;
import koreatech.cse.repository.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Inject
    private UserMapper userMapper;
    @Inject
    private AuthorityMapper authorityMapper;
    @Inject
    private PasswordEncoder passwordEncoder;


    public Boolean signup(User user) {
        if(user.getEmail() == null || user.getPassword() ==  null)  // 여기 08과 같음
            return false;

        // passwordEncoder로 부터 user의 password를 암호화해서 리턴
        user.setPassword(passwordEncoder.encode(user.getPassword()));	// 사용자의 실제 비밀번호를 Bcrypt로 암호화함
        userMapper.insert(user);    // 비밀번호를 다시 지정한 후에 DB에 삽입



        Authority authority = new Authority();  // 새로운 권한 생성
        authority.setUserId(user.getId());      //
        authority.setRole("ROLE_USER");         // 일반인권한 부여
        authorityMapper.insert(authority);      // 권한을 DB에 삽입

        if(user.getEmail().contains("admin")) { // 새로가입하는 사람 이메일에 'admin'이라는 말이 있으면 추가로 관리자 권한 생성
            Authority adminAuthority = new Authority();
            adminAuthority.setUserId(user.getId());
            adminAuthority.setRole("ROLE_ADMIN");
            authorityMapper.insert(adminAuthority);
        }

        System.out.println("user created :" + new Date());
        return true;
    }

    // TODO:: UserDetailsService interface 구현
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findByEmail(username);   // 이메일을 아이디로 로그인할것임
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username/password.");
        }

        // 이메일있으면, 있는 유저에 추가적으로 권한부여
        List<Authority> authorities = authorityMapper.findByUserId(user.getId());
        user.setAuthorities(authorities);
        System.out.println("user = " + user);
        return user;
    }

}
