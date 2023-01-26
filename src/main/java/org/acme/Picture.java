package org.acme;

import javax.persistence.*;
import java.io.Serializable;
import javax.json.bind.annotation.JsonbTransient;

@Table(name = "pictures")
@Entity
public class Picture implements Serializable {
    private Long id; 
    private String url;

    private Movie movie;

    public Picture() {
    }

    public Picture(Long id, Movie movie, String url) {
        this.id = id;
        this.movie = movie;
        this.url = url;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @MapsId("imdbId")
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonbTransient
    @JoinColumn(name = "movie_id")
    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Picture [id=" + id + ", url=" + url + ", movie=" + movie + "]";
    }

}