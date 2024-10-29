package hh.sof03.games;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.sof03.games.domain.Game;
import hh.sof03.games.domain.GameRepository;
import hh.sof03.games.domain.Genre;
import hh.sof03.games.domain.GenreRepository;
import hh.sof03.games.domain.Platform;
import hh.sof03.games.domain.PlatformRepository;

@SpringBootApplication
public class GamesApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamesApplication.class, args);
	}

	@Bean
	public CommandLineRunner gameDemo(GameRepository gameRepository, PlatformRepository platformRepository, GenreRepository genreRepository) {
		return (args) -> {
			platformRepository.save(new Platform("PC"));
			platformRepository.save(new Platform("Nintendo Switch"));

			genreRepository.save(new Genre("Action"));
			genreRepository.save(new Genre("Adventure"));

			gameRepository.save(new Game("Fallout 4", 2015, platformRepository.findByPlatformName("PC").get(0), genreRepository.findByGenreName("Action").get(0)));
			gameRepository.save(new Game("Cyberpunk 2077", 2020, platformRepository.findByPlatformName("PC").get(0), genreRepository.findByGenreName("Action").get(0)));
			gameRepository.save(new Game("The Legend of Zelda: Tears of the Kingdom", 2023, platformRepository.findByPlatformName("Nintendo Switch").get(0), genreRepository.findByGenreName("Adventure").get(0)));
		};
	}
}
