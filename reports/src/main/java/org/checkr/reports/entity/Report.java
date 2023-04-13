package org.checkr.reports.entity;


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
@Table(name="report")
public class Report {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int report_id;

    public enum Status{
        CLEAR,
        CONSIDER
    };
    public enum Adjudication{
        ENGAGED,
        ADVERSE_ACTION
    };

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Adjudication adjudication;

    @Column(name = "package")
    private String reportPackage;

    @Column(name = "completed_date")
    private Date completedDate;

    @Column(name = "turn_around_time")
    private String turnAroundTime;

    @Column(name = "candidate_id")
    private int candidateId;
}
