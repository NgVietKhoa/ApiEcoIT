package com.example.ecoit2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "intern_performance_reviews",
        uniqueConstraints = @UniqueConstraint(columnNames = {"intern_id", "review_date"}))
public class InternPerformanceReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "intern_id", referencedColumnName = "intern_id")
    private Intern intern;

    @NotNull
    @Column(name = "performance_score", nullable = false)
    private Integer performanceScore;

    @NotNull
    @Column(name = "review_date", nullable = false)
    private Date reviewDate;

    @NotNull
    @Column(name = "reviewer_id")
    private Integer reviewerId;

    @Size(max = 500)
    @Nationalized
    @Column(name = "comments", length = 500)
    private String comments;

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