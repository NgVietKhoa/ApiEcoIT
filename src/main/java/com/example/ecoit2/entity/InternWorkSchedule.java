package com.example.ecoit2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "intern_work_schedules",
        uniqueConstraints = @UniqueConstraint(columnNames = {"intern_id", "start_date"}))
public class InternWorkSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "intern_id", referencedColumnName = "intern_id")
    private Intern intern;

    @NotNull
    @Column(name = "available_hours_per_week", nullable = false)
    private Integer availableHoursPerWeek;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @NotNull
    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @NotNull
    @Column(name = "updated_by")
    private Integer updatedBy;
}