package hh.sof03.games.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Platform {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long platformId;
    @NotBlank(message = "Name is needed")
    @Size(max=100)
    private String platformName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "platform")
    @JsonIgnoreProperties("platform")
    private List<Game> games;

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public Platform(String platformName) {
        this.platformName = platformName;
    }

    public Platform() {}
}
