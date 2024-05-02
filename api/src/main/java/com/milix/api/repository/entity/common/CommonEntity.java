package com.milix.api.repository.entity.common;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


/**
 * Entityにデータ登録日時と更新日時をセットするためのクラス
 * 各Entityで継承して使うのを想定.
 */
@MappedSuperclass
@Getter
@Setter
public class CommonEntity {

    /* データ作成日時 */
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    /* データ更新日時 */
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    /* データ作成時に作成日時と更新日時をセット */
    @PrePersist
    public void preInsert() {
        Date date = new Date();
        setCreatedAt(date);
        setUpdatedAt(date);
    }

    /* データ更新時に更新日時をセット */
    @PreUpdate
    public void preUpdate() {
        setUpdatedAt(new Date());
    }
}
