package org.mik.boardGamesCollector.models;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "designers")
public class Category extends AbstractEntity<Long>{

    public static final String FLD_NAME = "name";
    public static final String FLD_BOARDGAMES = "boardgames";

    @Column(name = FLD_NAME)
    private String name;

    // Many-to-Many relationship with BoardGame
    @Column(name = FLD_BOARDGAMES)
    @ManyToMany(mappedBy = "categories")
    private List<BoardGame> boardGames;
}
