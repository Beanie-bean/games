package hh.sof03.games.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer releaseYear;

    @ManyToOne
    @JsonIgnoreProperties("games")
    @JoinColumn(name = "platformId")
    private Platform platform;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }
    
    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public Game(String name, Integer releaseYear, Platform platform) {
        super();
        this.name = name;
        this.releaseYear = releaseYear;
        this.platform = platform;
    }

    public Game() {
        super();
        this.name = null;
        this.releaseYear = null;
        this.platform = null;
    }

    @Override
    public String toString() {
        return "Game [id=" + id + ", name=" + name + ", releaseYear=" + releaseYear + "]";
    }

}
