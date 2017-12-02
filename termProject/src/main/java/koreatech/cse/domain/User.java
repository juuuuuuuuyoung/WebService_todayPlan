package koreatech.cse.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class User implements UserDetails {  // UserDetails :: Spring Security가 강제로 요구
    private int id = -9999999;
    private String name;
    private String email;
    private String password;
    private int age;

    private List<Authority> authorities;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        // interface를 구현
        return authorities;
    }

    public String getPassword() {
        // interface를 구현
        return password;
    }

    public String getUsername() {
        // interface를 구현
        return email;
    }

    // 다 true로 : 계정만료,정지,비밀번호만료, 등의 개념이 없기 때문에 사용하지 않겠다.
    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }


    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static User current() {
        try {
            return (User) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }

//    // TODO (1) : 만들어 놓으면 좋음
//    public boolean hasRole(String role) {
//         for (Authority authority: authorityList) {
//             if (authority.getRole().equals(role)) {
//                 return true;
//             }
//         }
//         return false;
//    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", authorities=" + authorities +
                '}';
    }
}
