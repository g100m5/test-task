package com.test.utils;

import com.test.dto.LocationDTO;

public class Location {

    public final static double AVERAGE_RADIUS_OF_EARTH_KM = 6371;

    public static int Calculate(LocationDTO left, LocationDTO right) {

        double latitudeDistance = Math.toRadians(left.getLatitude() - right.getLatitude());
        double longitudeDistance = Math.toRadians(left.getLongitude() - right.getLongitude());

        double a = Math.sin(latitudeDistance / 2) * Math.sin(latitudeDistance / 2)
                + Math.cos(Math.toRadians(left.getLatitude()))
                * Math.cos(Math.toRadians(right.getLatitude()))
                * Math.sin(longitudeDistance / 2) * Math.sin(longitudeDistance / 2);

        double angle = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return (int) (Math.round(AVERAGE_RADIUS_OF_EARTH_KM * angle));
    }
}
