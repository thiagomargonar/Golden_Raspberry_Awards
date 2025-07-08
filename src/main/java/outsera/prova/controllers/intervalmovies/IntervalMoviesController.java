package outsera.prova.controllers.intervalmovies;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import outsera.prova.controllers.intervalmovies.dto.WorstsProducersDTO;
import outsera.prova.services.intervalmovies.IntervalMoviesServ;

import java.util.List;

@RestController
@RequestMapping("/worst_movies")
public class IntervalMoviesController {
    private final IntervalMoviesServ intervalMoviesServ;

    public IntervalMoviesController(IntervalMoviesServ intervalMoviesServ) {
        this.intervalMoviesServ = intervalMoviesServ;
    }

    @GetMapping
    public WorstsProducersDTO getAll() {
        return intervalMoviesServ.findAll();
    }
}
