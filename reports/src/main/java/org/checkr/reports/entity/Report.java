package org.checkr.reports.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="report")
public class Report {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public enum Status{
        CLEAR,
        CONSIDER
    }
    public enum Adjudication{
        ENGAGED,
        ADVERSE_ACTION
    }

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Adjudication adjudication;

    @NotNull
    @Column(name = "package")
    private String reportPackage;

    @NotNull
    @Column(name = "completed_date")
    private LocalDateTime completedDate;

    @NotNull
    @Column(name = "turn_around_time")
    private String turnAroundTime;

    @NotNull
    @Column(name = "candidate_id")
    private int candidateId;
}
