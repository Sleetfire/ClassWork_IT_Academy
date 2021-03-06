package by.it.academy.MK_JD2_88_2.cw1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AirportCity {

    @JsonProperty("en")
    private String en;
    @JsonProperty("ru")
    private String ru;

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getRu() {
        return ru;
    }

    public void setRu(String ru) {
        this.ru = ru;
    }

    @Override
    public String toString() {
        return "AirportCity{" +
                "en='" + en + '\'' +
                ", ru='" + ru + '\'' +
                '}';
    }
}
