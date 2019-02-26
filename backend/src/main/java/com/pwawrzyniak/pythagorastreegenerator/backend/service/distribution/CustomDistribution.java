package com.pwawrzyniak.pythagorastreegenerator.backend.service.distribution;

import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomDistribution implements Random01Sampler {

  private final RandomGenerator random;

  private List<Integer> angleList;
  private List<Integer> cumulativeWeightList = new ArrayList<>();

  public CustomDistribution(List<Integer> angleList, List<Integer> weightList) {
    if (angleList.size() != weightList.size()) {
      throw new IllegalArgumentException("Angle and weight lists have different sizes");
    }
    random = new Well19937c();
    this.angleList = angleList;
    cumulativeWeightList.add(weightList.get(0));
    for (int i = 1; i < weightList.size(); i++) {
      cumulativeWeightList.add(weightList.get(i) + cumulativeWeightList.get(i - 1));
    }
  }

  @Override
  public double nextSample() {
    Integer randomValue = random.nextInt(cumulativeWeightList.get(cumulativeWeightList.size() - 1));
    int index = findIndexByValue(randomValue);
    return transformAngle(index);
  }

  private double transformAngle(int index) {
    Integer angle = angleList.get(index);
    return ((double) angle) / 90.0;
  }

  private int findIndexByValue(Integer value) {
    int binarySearchIndex = Collections.binarySearch(cumulativeWeightList, value);
    if (binarySearchIndex >= 0) {
      return binarySearchIndex + 1;
    }
    return -(binarySearchIndex + 1);
  }
}