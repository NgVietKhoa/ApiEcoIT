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
public class InternWSDTO {
    @NotNull
    private Integer internId; // Chỉ nhận intern_id

    @NotNull
    private Integer availableHoursPerWeek;

    @NotNull
    private Date startDate;

    private Date endDate;

    private LocalDateTime createdAt;

    private Integer createdBy;

    private LocalDateTime updatedAt;

    private Integer updatedBy;
}
