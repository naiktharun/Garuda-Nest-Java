package com.garudanest.service;

import com.garudanest.model.Flat;
import com.garudanest.model.TotalMembersHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WaterSharingService {

    public List<String[]> calculateShares(List<Flat> flats, double tankerCost, TotalMembersHolder totalMembersHolder) {
        if (flats == null) {
            flats = Collections.emptyList();
        }
        double totalHeadCount = flats.stream().mapToDouble(Flat::calculateHeadCount).sum();
        int totalMembers = flats.stream().mapToInt(flat -> flat.getAdults() + flat.getKids() + flat.getRelatives()).sum();

        totalMembersHolder.setTotalMembers(totalMembers);

        return flats.stream()
                .map(flat -> {
                    double flatHeadCount = flat.calculateHeadCount();
                    double share = (flatHeadCount / totalHeadCount) * tankerCost;
                    return new String[]{
                            flat.getFlatNumber(), // Ensure flat number is included
                            String.valueOf(flatHeadCount),
                            String.format("%.2f", share)
                    };
                })
                .collect(Collectors.toList());
    }
}
