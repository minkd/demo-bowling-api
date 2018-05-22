package com.mink.demo.bowlingapi.model.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@ToString(of = {"id", "frames", "bowler"})
@EqualsAndHashCode(of = "id")
public class Scorecard {

    @Id
    @GeneratedValue
    private long id;

    @Size(max = 10, message = "A scorecard may only have 10 frames")
    @OneToMany(mappedBy = "scorecard", cascade = CascadeType.PERSIST)
    private List<Frame> frames = new ArrayList<>();

    @ManyToOne
    private Bowler bowler;

    @ManyToOne
    private Game game;

}
