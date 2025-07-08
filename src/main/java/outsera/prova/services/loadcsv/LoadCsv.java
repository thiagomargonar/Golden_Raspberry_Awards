package outsera.prova.services.loadcsv;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import outsera.prova.exceptions.ResourceWithProblem;
import outsera.prova.models.Movies;
import outsera.prova.models.Producers;
import outsera.prova.models.Studios;
import outsera.prova.repositories.movies.MoviesRep;
import outsera.prova.repositories.producers.ProducersRep;
import outsera.prova.repositories.studios.StudiosRep;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoadCsv implements CommandLineRunner {

    private final MoviesRep moviesRep;

    private final ProducersRep producersRep;

    private final StudiosRep studiosRep;

    public LoadCsv(MoviesRep moviesRep, ProducersRep producersRep, StudiosRep studiosRep) {
        this.moviesRep = moviesRep;
        this.producersRep = producersRep;
        this.studiosRep = studiosRep;
    }

    @Override
    public void run(String... args) {
        var resource = new ClassPathResource("movielist.csv");

        try {
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {

                String line;
                boolean isFirstLine = true;

                while ((line = reader.readLine()) != null) {
                    if (isFirstLine) {
                        isFirstLine = false;
                        continue;
                    }

                    String[] fields = line.split(";", -1);

                    Movies movie = new Movies();
                    movie.setYear(Integer.parseInt(fields[0].trim()));
                    movie.setTitle(fields[1].trim());
                    movie.setStudios(getStudioByField(fields[2].trim()));
                    movie.setProducers(getProducerByField(fields[3].trim()));
                    movie.setWinner("yes".equalsIgnoreCase(fields[4].trim()));

                    saveMovie(movie);
                }

                System.out.println("CSV loader success");

            }
        } catch (Exception e) {
            throw new ResourceWithProblem("Problem with loading of CSV");
        }
    }

    private void saveMovie(Movies movie) {
        Producers producers = getOrSaveProducer(movie.getProducers());
        List<Studios> studios = getStudios(movie.getStudios());
        movie.setStudios(studios);
        movie.setProducers(producers);
        moviesRep.save(movie);
    }

    private List<Studios> getStudios(List<Studios> studios) {
        List<Studios> studiosList = new ArrayList<>();

        for (Studios studio : studios) {
            String[] nomes = studio.getName().split(",");

            getOrSaveStudios(studiosList, nomes);
        }

        return studiosList;
    }

    private void getOrSaveStudios(List<Studios> studiosList, String[] nomes) {
        for (String nome : nomes) {
            Optional<Studios> existing = studiosRep.findFirstByName(nome);

            if (existing.isPresent()) {
                studiosList.add(existing.get());
            } else {
                Studios stu = new Studios();
                stu.setName(nome);
                Studios saved = studiosRep.save(stu);
                studiosList.add(saved);
            }
        }
    }

    private Producers getOrSaveProducer(Producers producers) {
        Optional<Producers> producer = producersRep
                .findFirstByName(producers.getName());

        if(producer.isPresent()){
            return producer.get();
        }

        return producersRep.save(producers);
    }

    private Producers getProducerByField(String trim) {
        Producers producers = new Producers();
        producers.setName(trim);
        return producers;
    }

    private List<Studios> getStudioByField(String name) {
        Studios studios = new Studios();

        studios.setName(name);

        return List.of(studios);
    }
}

