package org.mik.boardGamesCollector.repositories;

import org.mik.boardGamesCollector.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
