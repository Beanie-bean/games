package hh.sof03.games.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Name is needed")
    @Size(max=100)
    private String name;
    @NotNull(message = "Release Year is needed")
    private Integer releaseYear;

    
    @ManyToOne
    @JsonIgnoreProperties("games")
    @JoinColumn(name = "platformId")
    private Platform platform;

    @ManyToOne
    @JsonIgnoreProperties("games")
    @JoinColumn(name = "genreId")
    private Genre genre;

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
    
    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Game(String name, Integer releaseYear, Platform platform, Genre genre) {
        super();
        this.name = name;
        this.releaseYear = releaseYear;
        this.platform = platform;
        this.genre = genre;
    }

    public Game() {
        super();
        this.name = null;
        this.releaseYear = null;
        this.platform = null;
        this.genre = null;
    }

    @Override
    public String toString() {
        return "Game [id=" + id + ", name=" + name + ", releaseYear=" + releaseYear + "]";
    }


}
