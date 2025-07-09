package outsera.prova.controllers.intervalmovies.dto;

public class ProducerDTO {

    private String producer;
    private int interval;
    private int previousWin;
    private int followingWin;

    private ProducerDTO(Builder builder) {
        setProducer(builder.producer);
        setInterval(builder.interval);
        setPreviousWin(builder.previousWin);
        setFollowingWin(builder.followingWin);
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public int getPreviousWin() {
        return previousWin;
    }

    public void setPreviousWin(int previousWin) {
        this.previousWin = previousWin;
    }

    public int getFollowingWin() {
        return followingWin;
    }

    public void setFollowingWin(int followingWin) {
        this.followingWin = followingWin;
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

        public ProducerDTO build() {
            return new ProducerDTO(this);
        }
    }
}
