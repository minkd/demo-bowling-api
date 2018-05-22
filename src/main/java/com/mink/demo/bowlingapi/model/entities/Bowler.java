package com.mink.demo.bowlingapi.model.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@ToString(of = {"id", "name"})
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "game_id"})
})
@EqualsAndHashCode(of = "id")
public class Bowler {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @ManyToOne
    private Game game;

    @OneToMany
    private List<Scorecard> scorecards = new ArrayList<>();

}
