package outsera.prova.services.loadcsv;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import outsera.prova.models.Movies;
import outsera.prova.repositories.movies.MoviesRep;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Service
public class LoadCsv implements CommandLineRunner {

    private final MoviesRep repository;

    public LoadCsv(MoviesRep repository) {
        this.repository = repository;
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

                    String[] fields = line.split(";", -1); // -1 mantém campos vazios

                    Movies movie = new Movies();
                    movie.setYear(Integer.parseInt(fields[0].trim()));
                    movie.setTitle(fields[1].trim());
                    movie.setStudios(fields[2].trim());
                    movie.setProducers(fields[3].trim());
                    movie.setWinner(fields[4].trim().equalsIgnoreCase("yes"));

                    repository.save(movie);
                }

                System.out.println("✅ CSV carregado com sucesso!");

            }
        } catch (Exception e) {
            System.err.println("❌ Erro ao carregar CSV: " + e.getMessage());
        }
    }
}

