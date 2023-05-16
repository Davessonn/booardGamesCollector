package org.mik.boardGamesCollector.repositories;

import org.mik.boardGamesCollector.models.BoardGame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardGameRepository extends JpaRepository<BoardGame, Long> {
}
