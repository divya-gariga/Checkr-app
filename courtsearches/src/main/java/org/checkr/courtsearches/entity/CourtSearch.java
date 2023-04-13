package org.checkr.courtsearches.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="courtsearch")
public class CourtSearch {
    public enum Status{
        CLEAR,
        CONSIDER
    }
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="search")
    private String search;

//    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "date")
    private Date date;

       @Column(name = "candidate_id")
       private int candidateId;


}

