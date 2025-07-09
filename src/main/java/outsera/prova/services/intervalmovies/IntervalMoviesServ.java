package outsera.prova.services.intervalmovies;

import org.springframework.stereotype.Service;
import outsera.prova.controllers.intervalmovies.dto.ProducerDTO;
import outsera.prova.controllers.intervalmovies.dto.WorstsProducersDTO;
import outsera.prova.models.Movies;
import outsera.prova.models.Producers;
import outsera.prova.repositories.movies.MoviesRep;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IntervalMoviesServ {

    public static final int MAX_VALUE = 999;
    public static final int MIN_VALUE = 0;
    private final MoviesRep moviesRep;

    public IntervalMoviesServ(MoviesRep moviesRep) {
        this.moviesRep = moviesRep;
    }

    public WorstsProducersDTO findAll() {
        List<Movies> movies = moviesRep.findAll();
        List<ProducerDTO> producerDTOS = getProducersByMovies(movies);
        return WorstsProducersDTO
                .builder()
                .min(getProducersWithMinInterval(producerDTOS))
                .max(getProducersWithMaxInterval(producerDTOS))
                .build();
    }

    private List<ProducerDTO> getProducersWithMinInterval(List<ProducerDTO> producerDTOS) {
        int minInterval = producerDTOS.stream()
                .mapToInt(ProducerDTO::getInterval)
                .min().orElse(MIN_VALUE);

        return producerDTOS.stream()
                .filter(producerDTO -> producerDTO.getInterval() == minInterval)
                .collect(Collectors.toList());
    }

    private List<ProducerDTO> getProducersWithMaxInterval(List<ProducerDTO> producerDTOS) {
        int maxInterval = producerDTOS.stream()
                .mapToInt(ProducerDTO::getInterval)
                .max().orElse(MAX_VALUE);

        return producerDTOS.stream()
                .filter(producerDTO -> producerDTO.getInterval() == maxInterval)
                .collect(Collectors.toList());
    }

    private List<ProducerDTO> getProducersByMovies(List<Movies> collect) {
        List<ProducerDTO> producers = new ArrayList<>();

        collect.stream()
                .map(Movies::getProducers)
                .map(Producers::getName)
                .distinct()
                .forEach(producer -> {
                    List<Movies> movies = collect
                            .stream()
                            .filter(Movies::isWinner)
                            .filter(movie -> movie.getProducers().getName().equalsIgnoreCase(producer))
                            .collect(Collectors.toList());
                    if(!movies.isEmpty()){
                        int bestYear = movies
                                .stream()
                                .max(Comparator.comparing(Movies::getYear))
                                .map(Movies::getYear)
                                .orElse(MIN_VALUE);

                        int lastYear = movies
                                .stream()
                                .min(Comparator.comparing(Movies::getYear))
                                .map(Movies::getYear)
                                .orElse(MIN_VALUE);

                        producers.add(ProducerDTO
                                .builder()
                                .producer(producer)
                                .interval(bestYear - lastYear)
                                .followingWin(bestYear)
                                .previousWin(lastYear)
                                .build());
                    }
                });
        return producers;
    }
}
