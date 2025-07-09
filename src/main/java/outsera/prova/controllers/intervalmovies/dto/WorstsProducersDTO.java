package outsera.prova.controllers.intervalmovies.dto;

import java.util.List;

public class WorstsProducersDTO {
    List<ProducerDTO> max;

    List<ProducerDTO> min;

    private WorstsProducersDTO(Builder builder) {
        setMax(builder.max);
        setMin(builder.min);
    }

    public static Builder builder() {
        return new Builder();
    }

    public List<ProducerDTO> getMax() {
        return max;
    }

    public void setMax(List<ProducerDTO> max) {
        this.max = max;
    }

    public List<ProducerDTO> getMin() {
        return min;
    }

    public void setMin(List<ProducerDTO> min) {
        this.min = min;
    }


    public static final class Builder {
        private List<ProducerDTO> max;
        private List<ProducerDTO> min;

        private Builder() {
        }

        public Builder max(List<ProducerDTO> val) {
            max = val;
            return this;
        }

        public Builder min(List<ProducerDTO> val) {
            min = val;
            return this;
        }

        public WorstsProducersDTO build() {
            return new WorstsProducersDTO(this);
        }
    }
}
