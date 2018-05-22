package com.mink.demo.bowlingapi.model.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity
@ToString(of = {"id", "name", "gameDate", "scorecards", "lane"})
@EqualsAndHashCode(of = "id")
public class Game {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @CreationTimestamp
    private OffsetDateTime gameDate;

    @OneToMany(mappedBy = "game")
    private List<Scorecard> scorecards = new ArrayList<>();

    @OneToMany(mappedBy = "game", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private Set<Bowler> bowlers;

    private int lane;

}
