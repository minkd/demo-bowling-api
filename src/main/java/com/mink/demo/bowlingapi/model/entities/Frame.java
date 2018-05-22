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
import javax.persistence.OneToOne;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(of = "id")
@ToString(exclude = "scorecard")
public class Frame {

    @Id
    @GeneratedValue
    private long id;

    private int frameNumber;

    @OneToMany(mappedBy = "frame", cascade = CascadeType.PERSIST)
    private List<Shot> shots;

    @ManyToOne
    private Scorecard scorecard;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Frame previousFrame;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Frame nextFrame;


}
