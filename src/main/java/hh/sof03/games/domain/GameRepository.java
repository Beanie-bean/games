package hh.sof03.games.domain;

import org.springframework.data.repository.CrudRepository;
import java.util.List;


public interface GameRepository extends CrudRepository<Game, Long> {
    List<Game> findByName(String name);
}
