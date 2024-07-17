package com.davylima.normaliser_job_title.implementations;

import com.davylima.normaliser_job_title.DataAccess;
import com.davylima.normaliser_job_title.algorithms.Levenshtein;
import com.davylima.normaliser_job_title.entities.ComparableResult;
import com.davylima.normaliser_job_title.interfaces.Normaliser;

import java.util.Comparator;

/**
 * Implementation of Normaliser interface using Levenshtein algorithm
 * This class can be used also in a Strategy pattern using the interface Normaliser
 */
public class NormaliseLevenshtein implements Normaliser {

    @Override
    public String normalise(String input) {
        final var dataAccess = new DataAccess(); //only a interface to retrieve data agnostic
        var match = dataAccess.retrieveData().stream().map(s ->
                    ComparableResult.builder().input(input).comparedWith(s).similarity(calculate(input, s)).build()
                ).max(Comparator.comparingDouble(ComparableResult::getSimilarity));
        return match.orElse(ComparableResult.builder().build()).getComparedWith();
    }

    private double calculate(String left, String right) {
        if(left.isEmpty() && right.isEmpty()) {
            return 1.0;
        }

        String longer = left, shorter = right;
        if(left.length() < right.length()) {
            longer = right;
            shorter = left;
        }

        return (longer.length() - calculteDistance(longer, shorter)) / (double) longer.length();
    }

    private int calculteDistance(String left, String right) {
       return Levenshtein.calculateDistance(left, right);
    }
}
