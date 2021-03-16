package com.test.dto;

import java.util.Objects;

public class CustomerDTO {

    String id;
    String name;
    LocationDTO location;

    int age;
    int acceptedOffers;
    int canceledOffers;
    int averageReplyTime;

    transient int ageScore;
    transient int locationScore;
    transient int acceptedOffersScore;
    transient int canceledOffersScore;
    transient int averageReplyTimeScore;

    public LocationDTO getLocation() {
        return location;
    }

    public int getAge() {
        return age;
    }

    public int getAcceptedOffers() {
        return acceptedOffers;
    }

    public int getCanceledOffers() {
        return canceledOffers;
    }

    public int getAverageReplyTime() {
        return averageReplyTime;
    }

    public int getAgeScore() {
        return ageScore;
    }

    public int getLocationScore() {
        return locationScore;
    }

    public int getAcceptedOffersScore() {
        return acceptedOffersScore;
    }

    public int getCanceledOffersScore() {
        return canceledOffersScore;
    }

    public int getAverageReplyTimeScore() {
        return averageReplyTimeScore;
    }

    public void setAgeScore(int ageScore) {
        this.ageScore = ageScore;
    }

    public void setLocationScore(int locationScore) {
        this.locationScore = locationScore;
    }

    public void setAcceptedOffersScore(int acceptedOffersScore) {
        this.acceptedOffersScore = acceptedOffersScore;
    }

    public void setCanceledOffersScore(int canceledOffersScore) {
        this.canceledOffersScore = canceledOffersScore;
    }

    public void setAverageReplyTimeScore(int averageReplyTimeScore) {
        this.averageReplyTimeScore = averageReplyTimeScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerDTO)) return false;
        CustomerDTO that = (CustomerDTO) o;
        return age == that.age &&
                acceptedOffers == that.acceptedOffers &&
                canceledOffers == that.canceledOffers &&
                averageReplyTime == that.averageReplyTime &&
                id.equals(that.id) &&
                name.equals(that.name) &&
                location.equals(that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, location, age, acceptedOffers, canceledOffers, averageReplyTime);
    }
}
