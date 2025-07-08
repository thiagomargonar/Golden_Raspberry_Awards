package outsera.prova.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "movies")
public class Movies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "year_of")
    private int year;
    private String title;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "movie_studio",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "studio_id")
    )
    private List<Studios> studios;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producer_id")
    private Producers producers;

    public Movies() {
    }

    private boolean winner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Producers getProducers() {
        return producers;
    }

    public void setProducers(Producers producers) {
        this.producers = producers;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public List<Studios> getStudios() {
        return studios;
    }

    public void setStudios(List<Studios> studios) {
        this.studios = studios;
    }
}
