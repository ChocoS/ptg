package com.pwawrzyniak.pythagorastreegenerator.backend.service.distribution;

import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;

public class UniformDistribution implements Random01Sampler {

  private final RandomGenerator random;

  public UniformDistribution() {
    random = new Well19937c();
  }

  @Override
  public double nextSample() {
    return random.nextDouble();
  }
}