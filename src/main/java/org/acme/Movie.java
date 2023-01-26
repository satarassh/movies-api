package org.acme;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "movies")
@Entity
public class Movie implements Serializable {
    private Long id; 
    private String imdbId; /* tt0993846 */ 
    private String title; /* The Wolf of Wall Street */ 
    private String genre; /* "Comedy" */
    private int year; /* 2013 */
    //private ArrayList<String> pictures; /* ["https://flxt.tmsimg.com/assets/p9991602_k_v13_ab.jpg","https://flxt.tmsimg.com/assets/p9991602_k_h9_ab.jpg"] */
    private String description; 
    /*  
        "Based on the true story of Jordan Belfort, 
        from his rise to a wealthy stock-broker living 
        the high life to his fall involving crime, 
        corruption and the federal government."
    */

    public Movie() {
    }

    public Movie(Long id, String imdbId, String title, String genre, int year, String description) {
        this.id = id;
        this.imdbId = imdbId;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    
}