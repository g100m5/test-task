package com.test;

import com.test.dto.CustomerDTO;
import com.test.dto.LocationDTO;
import com.test.constants.Coefficient;

import com.test.utils.Location;
import com.test.utils.Parsing;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class CustomerService {

    public void uploadCustomers(MultipartFile file) {
        Set<CustomerDTO> newCustomers = Parsing.uploadedFile(file);
        Set<CustomerDTO> oldCustomers = Parsing.loadOldCustomers();

        if (oldCustomers != null) {
            newCustomers.addAll(oldCustomers);
        }
        Parsing.clearOldCustomerData();
        Parsing.saveActualCustomers(newCustomers);

    }

    public Map<Float, CustomerDTO> getTopCustomers(LocationDTO currentLocation) {

        SortedMap<Float, CustomerDTO> topCustomers = new TreeMap<>(Collections.reverseOrder());
        List<CustomerDTO> customers = Parsing.loadCustomers();

        for (int x = 0; x < customers.size(); x++) {
            for (int y = x + 1; y < customers.size(); y++) {
                calculateScores(customers.get(x), customers.get(y), currentLocation);
            }
            topCustomers.put(calculateTotalScore(customers.get(x)), customers.get(x));
        }
        return topCustomers;
    }

    public static void calculateScores(CustomerDTO left, CustomerDTO right, LocationDTO currentDistance) {

        if ((Location.Calculate(left.getLocation(), currentDistance) <
                (Location.Calculate(right.getLocation(), currentDistance)))) {
            left.setLocationScore(left.getLocationScore() + 1);
        } else {
            right.setLocationScore(left.getLocationScore() + 1);
        }

        if (left.getAge() > right.getAge()) {
            left.setAgeScore(left.getAgeScore() + 1);
        } else {
            right.setAgeScore(right.getAgeScore() + 1);
        }

        if (left.getAcceptedOffers() > right.getAcceptedOffers()) {
            left.setAcceptedOffersScore(left.getAcceptedOffersScore() + 1);
        } else {
            right.setAcceptedOffersScore(right.getAcceptedOffersScore() + 1);
        }

        if (left.getCanceledOffers() < right.getCanceledOffers()) {
            left.setCanceledOffersScore(left.getCanceledOffersScore() + 1);
        } else {
            right.setCanceledOffersScore(right.getCanceledOffersScore() + 1);
        }

        if (left.getAverageReplyTime() < right.getAverageReplyTime()) {
            left.setAverageReplyTimeScore(left.getAverageReplyTimeScore() + 1);
        } else {
            right.setAverageReplyTimeScore(right.getAverageReplyTimeScore() + 1);
        }

    }

    public static float calculateTotalScore(CustomerDTO customer) {
        return customer.getLocationScore() * Coefficient.DISTANCE +
                customer.getAgeScore() * Coefficient.AGE +
                customer.getAcceptedOffersScore() * Coefficient.ACCEPTANCE +
                customer.getCanceledOffersScore() * Coefficient.CANCELANCE +
                customer.getAverageReplyTimeScore() * Coefficient.RESPONSIBILITY;
    }

    public void clearCustomers() {
        Parsing.clearOldCustomerData();
    }

}
