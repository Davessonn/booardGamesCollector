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
@Table(name = "users")

public class User extends AbstractEntity<Long>{

    public static final String FLD_NAME = "username";
    public static final String FLD_BOARDGAMES = "boardgames";

    @Column(name = FLD_NAME)
    private String userName;

    // One-to-Many relationship with BoardGame
    @Column(name = FLD_BOARDGAMES)
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<BoardGame> boardGames;

}
