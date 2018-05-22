package com.mink.demo.bowlingapi.model.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@ToString(of = {"id", "pins"})
@EqualsAndHashCode(of = "id")
public class Shot {

    @Id
    @GeneratedValue
    private long id;

    private int pins;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Frame frame;

}
