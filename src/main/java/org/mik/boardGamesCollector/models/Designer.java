package org.mik.boardGamesCollector.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "designers")
public class Designer extends AbstractEntity<Long>{

    public static final String FLD_NAME = "name";
    public static final String FLD_BOARDGAMES = "boardgames";

    @Column(name = FLD_NAME)
    private String name;

    // One-to-Many relationship with BoardGame
    @Column(name = FLD_BOARDGAMES)
    @OneToMany(mappedBy = "designer", cascade = CascadeType.ALL)
    private List<BoardGame> boardGames;
}
