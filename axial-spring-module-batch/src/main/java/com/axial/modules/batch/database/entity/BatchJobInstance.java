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
@Table(name = "BATCH_JOB_INSTANCE")
public class BatchJobInstance {

    @Id
    @NotNull
    @Column(name = "JOB_EXECUTION_ID")
    private Long jobExecutionId;

    @Column(name = "VERSION")
    private Long version;

    @NotBlank
    @Column(name = "JOB_NAME", length = 100)
    private String jobName;

    @NotBlank
    @Column(name = "JOB_KEY", length = 32)
    private String jobKey;

}
