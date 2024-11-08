package hh.sof03.games;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import hh.sof03.games.domain.AppUser;
import hh.sof03.games.domain.AppUserRepository;
import hh.sof03.games.domain.Game;
import hh.sof03.games.domain.GameRepository;
import hh.sof03.games.domain.Genre;
import hh.sof03.games.domain.GenreRepository;
import hh.sof03.games.domain.Platform;
import hh.sof03.games.domain.PlatformRepository;

@SpringBootApplication
public class GamesApplication implements WebMvcConfigurer {

	private final LocaleChangeInterceptor localeChangeInterceptor;

	public GamesApplication(LocaleChangeInterceptor localeChangeInterceptor) {
        this.localeChangeInterceptor = localeChangeInterceptor;
    }

	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor);
    }

	public static void main(String[] args) {
		SpringApplication.run(GamesApplication.class, args);
	}

	@Bean
	public CommandLineRunner gameDemo(GameRepository gameRepository, PlatformRepository platformRepository, GenreRepository genreRepository, AppUserRepository appUserRepository) {
		return (args) -> {
			AppUser user2 = new AppUser("Veera", "$2a$10$XryM6w04gkh6JPDM7ib.C.RRBxFbdT6RkgMvFcNRaUsxxXmU9pij6", "ADMIN");

			appUserRepository.save(user2);

			platformRepository.save(new Platform("Windows"));
			platformRepository.save(new Platform("Nintendo Switch"));
			platformRepository.save(new Platform("PlayStation 3"));
			platformRepository.save(new Platform("macOS"));

			genreRepository.save(new Genre("Action"));
			genreRepository.save(new Genre("Adventure"));
			genreRepository.save(new Genre("Puzzle"));
			genreRepository.save(new Genre("Simulation"));
			genreRepository.save(new Genre("Story Rich"));
			genreRepository.save(new Genre("Platformer"));

			gameRepository.save(new Game("Fallout 4", 2015, platformRepository.findByPlatformName("Windows").get(0), genreRepository.findByGenreName("Action").get(0)));
			gameRepository.save(new Game("Cyberpunk 2077", 2020, platformRepository.findByPlatformName("Windows").get(0), genreRepository.findByGenreName("Action").get(0)));
			gameRepository.save(new Game("The Legend of Zelda: Tears of the Kingdom", 2023, platformRepository.findByPlatformName("Nintendo Switch").get(0), genreRepository.findByGenreName("Adventure").get(0)));
			gameRepository.save(new Game("Halo: The Master Chief Collection", 2019, platformRepository.findByPlatformName("Windows").get(0), genreRepository.findByGenreName("Action").get(0)));
			gameRepository.save(new Game("Detroit: Become Human", 2019, platformRepository.findByPlatformName("Windows").get(0), genreRepository.findByGenreName("Story Rich").get(0)));
			gameRepository.save(new Game("The Witness", 2016, platformRepository.findByPlatformName("Windows").get(0), genreRepository.findByGenreName("Puzzle").get(0)));
			gameRepository.save(new Game("Sims 4", 2014, platformRepository.findByPlatformName("Windows").get(0), genreRepository.findByGenreName("Simulation").get(0)));
			gameRepository.save(new Game("LittleBigPlanet", 2008, platformRepository.findByPlatformName("PlayStation 3").get(0), genreRepository.findByGenreName("Platformer").get(0)));
			gameRepository.save(new Game("Kirby and the Forgotten Land", 2022, platformRepository.findByPlatformName("Nintendo Switch").get(0), genreRepository.findByGenreName("Platformer").get(0)));
			gameRepository.save(new Game("Lies of P", 2023, platformRepository.findByPlatformName("macOS").get(0), genreRepository.findByGenreName("Action").get(0)));

		};
	}
}
