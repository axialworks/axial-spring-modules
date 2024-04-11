package com.axial.modules.batch.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "BATCH_JOB_EXECUTION_PARAMS")
public class BatchJobExecutionParams {

    @Id
    @NotNull
    @Column(name = "JOB_EXECUTION_ID")
    private Long jobExecutionId;

    @NotBlank
    @Column(name = "PARAMETER_NAME", length = 100)
    private String parameterName;

    @NotBlank
    @Column(name = "PARAMETER_TYPE", length = 100)
    private String parameterType;

    @Column(name = "PARAMETER_VALUE", length = 2500)
    private String parameterValue;

    @NotNull
    @Column(name = "IDENTIFYING")
    private Boolean identifying;

}
