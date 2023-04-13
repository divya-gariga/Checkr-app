package org.checkr.adverseactions.entity;

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
@Table(name="adverseaction")
public class AdverseAction {
    public enum Status{
        SCHEDULED
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name="name")
    private String name;

    @Enumerated(EnumType.STRING)
    private Status status;


    @Column(name = "prenotice_date")
    private Date prenoticeDate;


    @Column(name = "postnotice_date")
    private Date postnoticeDate;

    @Column(name="candidate_id")
    private int candidateId;

}
