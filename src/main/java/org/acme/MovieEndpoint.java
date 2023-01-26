package org.acme;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

@Path("/movies")
public class MovieEndpoint {

    @Inject
    MovieResource movieResource;

    // curl -X GET http://localhost:8080/movies
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movie> getMovies() {
        return movieResource.getMovies();
    }

    // curl -X GET http://localhost:8080/movies/tt0993846
    @GET
    @Path("/{imdbId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMovie(@PathParam("imdbId") String imdbId) {
        Movie movie = null;
        
        try {
            movie = movieResource.getMovie(imdbId);
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).type(MediaType.TEXT_PLAIN_TYPE).entity("Movie with imdbId="+imdbId+" not found.").build();
        }

        return Response.ok(movie).status(Response.Status.OK).build();
    }

    // curl -X PUT -H "Content-Type: application/json" -d '{"title":"The Wolf of Ljubljana","genre":"Drama","year":1969,"description":"I am very short"}' http://localhost:8080/movies/tt0993846
    @PUT
    @Path("/{imdbId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateMovie(@PathParam("imdbId") String imdbId, Movie m) {
        try {
            movieResource.getMovie(imdbId);
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).type(MediaType.TEXT_PLAIN_TYPE).entity("Movie with imdbId="+imdbId+" not found.").build();
        }

        System.out.println(m.toString());
        
        movieResource.updateMovie(imdbId, m);
        return Response.status(Response.Status.OK).type(MediaType.TEXT_PLAIN_TYPE).entity("Movie with imdbId="+imdbId+" updated successfully.").build();
    }

    // curl -X POST -H "Content-Type: application/json" -d '{"imdbId":"tt0333701","title":"Kajmak in marmelada","genre":"Drama","year":2003,"description":"A story about a couple from the bottom of the social ladder."}' http://localhost:8080/movies
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addMovie(Movie m) {
        try {
            movieResource.addMovie(m);
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN_TYPE).entity("Movie with imdbId="+m.getImdbId()+" cannot be added.").build();
        }
        
        return Response.status(Response.Status.OK).type(MediaType.TEXT_PLAIN_TYPE).entity("Movie with imdbId="+m.getImdbId()+" added successfully.").build();
    }

    // curl -X DELETE http://localhost:8080/movies/tt0993846
    @DELETE
    @Path("/{imdbId}")
    public Response deleteMovie(@PathParam("imdbId") String imdbId) {
        try {
            movieResource.getMovie(imdbId);
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).type(MediaType.TEXT_PLAIN_TYPE).entity("Movie with imdbId="+imdbId+" not found.").build();
        }
        
        movieResource.deleteMovie(imdbId);
        return Response.status(Response.Status.OK).type(MediaType.TEXT_PLAIN_TYPE).entity("Movie with imdbId="+imdbId+" deleted successfully.").build();
    }
}