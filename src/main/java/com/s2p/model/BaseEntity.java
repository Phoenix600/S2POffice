package com.s2p.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity
{
	@CreatedBy
	@Column(updatable = false)
	@Schema(description = "User who created this record", example = "admin")
	private String createdBy;
	
	@CreatedDate
	@Column(updatable = false)
	@Schema(description = "Timestamp when this record was created", example = "2025-09-19 14:30:00")
	private LocalDateTime createdAt;
	
	@LastModifiedBy
	@Column(insertable = false)
	@Schema(description = "User who last updated this record", example = "editorUser")
	private String updatedBy;
	
	@LastModifiedDate
	@Column(insertable = false)
	@Schema(description = "Timestamp of last update", example = "2025-09-20 10:45:00")
	private LocalDateTime updatedAt;
}