package org.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.test.dto.GameType;
import org.test.models.BetModel;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BetRepository extends JpaRepository<BetModel, Integer> {

//    @Query()
    List<BetModel> findByGameTypeAndClientIdAndDate(GameType gameType, int clientId, LocalDate date);

}
