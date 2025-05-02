package com.example.ecoit2.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InternPRDTO {
    @NotNull
    private Integer internId;

    @NotNull
    private Integer performanceScore;

    private Date reviewDate;

    private Integer reviewerId;

    private String comments;

    private LocalDateTime createdAt;

    private Integer createdBy;

    private LocalDateTime updatedAt;

    private Integer updatedBy;

}
