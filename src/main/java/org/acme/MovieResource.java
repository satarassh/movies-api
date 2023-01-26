package org.acme;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class MovieResource {

    @Inject
    EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<Movie> getMovies() {
        return entityManager.createQuery("SELECT m FROM Movie m").getResultList();
    }

    public List<Movie> getMoviesWithPagination(int page, int results) {
        List<Movie> movie_list = getMovies();
        int list_size = movie_list.size();

        int true_results = results;
        int true_page = page;

        // A little bit of validation
        if(results > 250) {
            true_results = list_size;
        } else if(results < 1) {
            true_results = 1;
        }

        int total_pages = (int)Math.ceil((double)list_size/(double)true_results);

        // A little bit of validation
        if(page > total_pages) {
            true_page = total_pages;
        } else if(page < 1) {
            true_page = 1;
        }
        
        int true_offset=(true_page*true_results)-true_results;
        int true_limit=true_results;

        int remainder = ((total_pages*true_results)-list_size);

        if(true_page == total_pages) {
            true_limit = true_results - remainder;
        }

        return movie_list.subList(true_offset, true_offset+true_limit);
    }

    public List<Movie> getMoviesSearch(String keyword) { 
        List<Movie> result = getMovies().stream()
                .filter(item -> {
                    if( item.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                        item.getDescription().toLowerCase().contains(keyword.toLowerCase()) ||
                        item.getGenre().toLowerCase().contains(keyword.toLowerCase()) ||
                        item.getImdbId().toLowerCase().contains(keyword.toLowerCase())
                        ) {
                        return true;
                    }
                    return false;
                })
                .collect(Collectors.toList());

        return result;
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
            if (movie.getPictures() != null) movie_update.setPictures(movie.getPictures());
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