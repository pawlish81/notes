package com.pwldata.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
public class AuditableEntity extends BaseId{
    @CreatedDate
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastModifiedDate;

    @CreatedBy
    private String createdByUser;

    @LastModifiedBy
    private String modifiedByUser;

    @Version
    private Integer version;

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public AuditableEntity setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public AuditableEntity setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }

    public String getCreatedByUser() {
        return createdByUser;
    }

    public AuditableEntity setCreatedByUser(String createdByUser) {
        this.createdByUser = createdByUser;
        return this;
    }

    public String getModifiedByUser() {
        return modifiedByUser;
    }

    public AuditableEntity setModifiedByUser(String modifiedByUser) {
        this.modifiedByUser = modifiedByUser;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public AuditableEntity setVersion(Integer version) {
        this.version = version;
        return this;
    }

    @Override
    public String toString() {

        return "AuditableEntity{" +
                "createdDate=" + createdDate +
                ", lastModifiedDate=" + lastModifiedDate +
                ", createdByUser='" + createdByUser + '\'' +
                ", modifiedByUser='" + modifiedByUser + '\'' +
                ", version=" + version +
                '}' +  super.toString();
    }
}
