package hh.sof03.games;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import hh.sof03.games.web.AppUserController;
import hh.sof03.games.web.GameController;
import hh.sof03.games.web.GenreController;
import hh.sof03.games.web.PlatformController;

@SpringBootTest
class GamesApplicationTests {

	@Autowired
	private GameController gameController;
	@Autowired
	private PlatformController platformController;
	@Autowired
	private GenreController genreController;
	@Autowired
	private AppUserController appUserController;

	@Test
	void contextLoads() throws Exception {
		assertThat(gameController).isNotNull();
		assertThat(platformController).isNotNull();
		assertThat(genreController).isNotNull();
		assertThat(appUserController).isNotNull();

	}

}
