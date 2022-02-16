package com.sovadeveloper.conference.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "talk")
public class TalkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "text", nullable = false)
    private String text;

    @ManyToMany()
    @JoinTable(
            name = "user_talk",
            joinColumns = @JoinColumn(name = "talk_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<UserEntity> users = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "talk")
    private List<ScheduleEntity> schedule;

    public void addUser(UserEntity userEntity){
        this.users.add(userEntity);
        userEntity.getTalks().add(this);
    }

    public void deleteUser(UserEntity userEntity){
        this.users.remove(userEntity);
        userEntity.getTalks().remove(this);
    }
}
