package com.pwawrzyniak.pythagorastreegenerator.backend.service.distribution;

public class BinomialDistribution extends org.apache.commons.math3.distribution.BinomialDistribution implements Random01Sampler {

  public BinomialDistribution(int trials, double p) {
    super(trials, p);
  }

  @Override
  public double nextSample() {
    return (double) sample() / getNumberOfTrials();
  }
}