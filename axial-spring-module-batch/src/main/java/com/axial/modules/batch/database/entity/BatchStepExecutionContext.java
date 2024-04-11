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
@Table(name = "BATCH_STEP_EXECUTION_CONTEXT")
public class BatchStepExecutionContext {

    @Id
    @NotNull
    @Column(name = "STEP_EXECUTION_ID")
    private Long stepExecutionId;

    @NotBlank
    @Column(name = "SHORT_CONTEXT", length = 2500)
    private String shortContext;

    @Column(name = "SERIALIZED_CONTEXT")
    private String serializedContext;

}
