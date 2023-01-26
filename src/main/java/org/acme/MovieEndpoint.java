package org.acme;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/movies")
public class MovieEndpoint {

    @Inject
    MovieResource movieResource;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movie> getMovies() {
        return movieResource.getMovies();
    }

    @GET
    @Path("/{imdbId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Movie getMovie(@PathParam("imdbId") String imdbId) {
        return movieResource.getMovie(imdbId);
    }

    @PUT
    @Path("/{imdbId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateMovie(@PathParam("imdbId") String imdbId, Movie m) {
        movieResource.updateMovie(imdbId, m);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Movie addMovie(Movie m) {
        return movieResource.addMovie(m);
    }

    @DELETE
    @Path("/{imdbId}")
    public void deleteMovie(@PathParam("imdbId") String imdbId) {
        movieResource.deleteMovie(imdbId);
    }
}