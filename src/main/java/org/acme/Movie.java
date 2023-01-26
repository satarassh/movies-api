package org.acme;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "movies", uniqueConstraints = @UniqueConstraint(columnNames={"imdbId"}))
@Entity
public class Movie implements Serializable {
    private Long id; 

    private String imdbId;

    private List<Picture> pictures;

    private String title;
    private String genre;
    private int year;
    private String description; 

    public Movie() {
    }

    public Movie(Long id, String imdbId, List<Picture> pictures, String title, String genre, int year, String description) {
        this.id = id;
        this.imdbId = imdbId;
        this.pictures = pictures;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.description = description;
    }

    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @Column(name = "imdbId")
    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "genre")
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Column(name = "year")
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy="movie")
    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    @Override
    public String toString() {
        return "Movie [id=" + id + ", imdbId=" + imdbId + ", pictures=" + pictures + ", title=" + title + ", genre="
                + genre + ", year=" + year + ", description=" + description + "]";
    }
}