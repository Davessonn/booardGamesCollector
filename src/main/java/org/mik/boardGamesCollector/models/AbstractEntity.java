package org.mik.boardGamesCollector.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

public class AbstractEntity<ID extends Serializable> implements Serializable {

    public static final String FLD_ID = "id";
    public static final String FLD_VERSION = "version";
    public static final String FLD_CREATED = "created";
    public static final String FLD_UPDATED = "updated";

    @Id
    @Column(name = FLD_ID, unique = true,nullable = false,updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private ID id;

    @JsonIgnore
    @Version
    private Integer version;

    @JsonIgnore
    @CreationTimestamp
    private LocalDateTime created;

    @JsonIgnore
    @UpdateTimestamp
    private LocalDateTime updated;
}
