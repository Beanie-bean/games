package hh.sof03.games;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import hh.sof03.games.domain.Game;
import hh.sof03.games.domain.GameRepository;
import hh.sof03.games.domain.Genre;
import hh.sof03.games.domain.GenreRepository;
import hh.sof03.games.domain.Platform;
import hh.sof03.games.domain.PlatformRepository;

@SpringBootTest(classes = {hh.sof03.games.GamesApplication.class})
public class GamesRepositoryTests {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlatformRepository platformRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    public void findGameByTitle() {
        List<Game> games = gameRepository.findByName("Fallout 4");

        assertThat(games).hasSize(1);
        assertThat(games.get(0).getReleaseYear()).isEqualTo(2015);
    }

    @Test
    public void addNewPlatform() {
        Platform platform = new Platform("Wii U");
        platform = platformRepository.save(platform);
        assertThat(platform.getPlatformId()).isNotNull();
    }

    @Test
    public void addNewGenre() {
        Genre genre = new Genre("Survival");
        genre = genreRepository.save(genre);
        assertThat(genre.getGenreId()).isNotNull();
    }

    @Test
    public void addNewGame() {
        Platform platform = new Platform("Wii U");
        platform = platformRepository.save(platform);
        Game game = new Game("Captain Toad: Treasure Tracker", 2014, platformRepository.findByPlatformName("Wii U").get(0), genreRepository.findByGenreName("Puzzle").get(0));
        game = gameRepository.save(game);
        assertThat(game.getId()).isNotNull();
    }

    @Test
    public void deleteGame() {
        Platform platform = new Platform("Wii U");
        platform = platformRepository.save(platform);
        Game game = new Game("Captain Toad: Treasure Tracker", 2014, platformRepository.findByPlatformName("Wii U").get(0), genreRepository.findByGenreName("Puzzle").get(0));
        game = gameRepository.save(game);
        gameRepository.deleteById(game.getId());
        assertThat(gameRepository.findById(game.getId())).isEmpty();
    }
}
