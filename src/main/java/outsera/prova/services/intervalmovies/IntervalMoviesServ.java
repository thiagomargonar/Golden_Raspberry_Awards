package outsera.prova.services.intervalmovies;

import org.springframework.stereotype.Service;
import outsera.prova.controllers.intervalmovies.dto.WorstsProducersDTO;
import outsera.prova.models.Movies;
import outsera.prova.repositories.movies.MoviesRep;

import java.util.List;

@Service
public class IntervalMoviesServ {

    private final MoviesRep moviesRep;

    public IntervalMoviesServ(MoviesRep moviesRep) {
        this.moviesRep = moviesRep;
    }

    public List<WorstsProducersDTO> findAll() {
        List<Movies> movies = moviesRep.findAll();

        return null;
    }
}
