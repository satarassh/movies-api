package org.acme;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.List;

@Singleton
public class MovieResource {

    @Inject
    EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<Movie> getMovies() {
        return entityManager.createQuery("SELECT m FROM Movie m").getResultList();
    }

    public Movie getMovie(String imdbId) {
        return (Movie)entityManager.createQuery("SELECT m FROM Movie m WHERE m.imdbId = :imdbId1").setParameter("imdbId1", imdbId).getSingleResult();
    }

    @Transactional
    public Movie addMovie(Movie movie) {
        entityManager.persist(movie);
        return movie;
    }

    @Transactional
    public void updateMovie(String imdbId, Movie movie) {
        Movie movie_update = getMovie(imdbId);

        if (movie_update != null) {
            if (movie.getTitle() != null) movie_update.setTitle(movie.getTitle());
            if (movie.getYear() != 0) movie_update.setYear(movie.getYear());
            if (movie.getDescription() != null) movie_update.setDescription(movie.getDescription());
            if (movie.getGenre() != null) movie_update.setGenre(movie.getGenre());
        } else {
            throw new RuntimeException("No movie can be found");
        }
    }

    @Transactional
    public void deleteMovie(String imdbId) {
        Movie movie = getMovie(imdbId);
        entityManager.remove(movie);
    }
}