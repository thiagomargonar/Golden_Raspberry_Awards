package outsera.prova.controllers.movies.dto;

public class MaxDTO extends ProducerDTO{

    private MaxDTO(Builder builder) {
        setProducer(builder.producer);
        setInterval(builder.interval);
        setPreviousWin(builder.previousWin);
        setFollowingWin(builder.followingWin);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String producer;
        private int interval;
        private int previousWin;
        private int followingWin;

        private Builder() {
        }

        public Builder producer(String val) {
            producer = val;
            return this;
        }

        public Builder interval(int val) {
            interval = val;
            return this;
        }

        public Builder previousWin(int val) {
            previousWin = val;
            return this;
        }

        public Builder followingWin(int val) {
            followingWin = val;
            return this;
        }

        public MaxDTO build() {
            return new MaxDTO(this);
        }
    }
}
