package com.sovadeveloper.conference.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "room")
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "number", nullable = false, unique = true)
    private int number;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "room")
    private List<ScheduleEntity> schedule;
}
