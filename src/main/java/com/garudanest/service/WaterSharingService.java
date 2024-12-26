package com.garudanest.service;

import com.garudanest.model.Flat;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WaterSharingService {

    public List<String[]> calculateShares(List<Flat> flats, double tankerCost) {
        double totalHeadCount = flats.stream()
                                     .mapToDouble(Flat::calculateHeadCount)
                                     .sum();

        return flats.stream()
                    .map(flat -> {
                        double flatHeadCount = flat.calculateHeadCount();
                        double share = (flatHeadCount / totalHeadCount) * tankerCost;
                        return new String[]{
                            flat.getFlatNumber(),
                            String.valueOf(flatHeadCount),
                            String.format("%.2f", share)
                        };
                    })
                    .collect(Collectors.toList());
    }
}