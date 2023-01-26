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

    @Transactional(Transactional.TxType.REQUIRED)
    public Movie addMovie(Movie movie) {
        entityManager.persist(movie);
        return movie;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void updateMovie(String imdbId, Movie movie) {
        Movie movie_update = getMovie(imdbId);

        if (movie_update != null) {
            movie_update.setTitle(movie.getTitle());
        } else {
            throw new RuntimeException("No movie can be found");
        }
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void deleteMovie(String imdbId) {
        Movie movie = getMovie(imdbId);
        entityManager.remove(movie);
    }
}