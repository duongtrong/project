package com.spring.projectsem4.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails {

    private Long id;

    private String fullName;

    private String username;

    private String email;

    private String password;

    private Date birthday;

    private String phone;

    private String address;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(Long id, String fullName, String username, String email, String password, Date birthday, String phone, String address, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.phone = phone;
        this.address = address;
        this.authorities = authorities;
    }

    private UserPrincipal(Long id, String fullName, String username, String password, Date birthday, String phone, String address, List<GrantedAuthority> authorities) {
    }

    public static UserPrincipal create(User user){
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName().name())).collect(Collectors.toList());
        return new UserPrincipal(
                user.getId(),
                user.getFullName(),
                user.getUsername(),
                user.getPassword(),
                user.getBirthday(),
                user.getPhone(),
                user.getAddress(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
