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
@Table(name = "boardgames")

public class BoardGame extends AbstractEntity<Long>{

    public static final String FLD_TITLE = "title";
    public static final String FLD_OWNER = "owner";
    public static final String FLD_DESIGNER = "designer";
    public static final String FLD_CATEGORIES = "categories";

    @Column(name = FLD_TITLE)
    private String title;


    // Many-to-One relationship with User (owner)
    @Column(name = FLD_OWNER)
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    // Many-to-One relationship with Designer
    @Column(name = FLD_DESIGNER)
    @ManyToOne
    @JoinColumn(name = "designer_id")
    private Designer designer;

    // Many-to-Many relationship with Category
    @Column(name = FLD_CATEGORIES)
    @ManyToMany
    @JoinTable(
            name = "board_game_categories",
            joinColumns = @JoinColumn(name = "board_game_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;
}
