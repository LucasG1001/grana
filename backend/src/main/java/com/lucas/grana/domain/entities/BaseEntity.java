package com.lucas.grana.domain.entities;

import java.time.LocalDateTime;

public abstract class BaseEntity {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Long getId(){
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
