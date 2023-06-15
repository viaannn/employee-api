package com.edts.edtstechnicaltest.model.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @CreatedDate
    @Column(name = "created_date")
    protected Date createdDate;

    @LastModifiedDate
    @Column(name = "updated_date")
    protected Date updatedDate;

    @CreatedBy
    @Column(name = "created_by", nullable = false, updatable = false)
    private String cratedBy;

    @LastModifiedBy
    @Column(name = "updated_by")
    private String updatedBy;

}
