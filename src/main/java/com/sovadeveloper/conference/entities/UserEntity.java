package com.sovadeveloper.conference.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    private boolean active;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private Set<TalkEntity> talks;

    public void addTalk(TalkEntity talkEntity){
        this.talks.add(talkEntity);
        talkEntity.getUsers().add(this);
    }

    public void deleteTalk(TalkEntity TalkEntity){
        this.talks.remove(TalkEntity);
        TalkEntity.getUsers().remove(this);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.name()));
        return authorities;
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
        return isActive();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return active == that.active && Objects.equals(id, that.id) && Objects.equals(username, that.username) && Objects.equals(password, that.password) && role == that.role && Objects.equals(talks, that.talks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, active, role, talks);
    }
}
