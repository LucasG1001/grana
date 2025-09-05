package com.lucas.grana.infra.persistence;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class BaseEntity {
    private UUID id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UUID getId(){
        return this.id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createAt){
        this.createdAt = createAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updateAt) {
        this.updatedAt = updateAt;
    }
}
