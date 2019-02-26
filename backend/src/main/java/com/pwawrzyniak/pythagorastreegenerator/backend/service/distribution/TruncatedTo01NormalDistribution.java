package com.pwawrzyniak.pythagorastreegenerator.backend.service.distribution;

import org.apache.commons.math3.distribution.AbstractRealDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.random.Well19937c;

public class TruncatedTo01NormalDistribution extends AbstractRealDistribution implements Random01Sampler {

  private NormalDistribution normalDistribution = new NormalDistribution();
  private double mu;
  private double sigma;
  private double alpha;
  private double beta;
  private double Z;

  public TruncatedTo01NormalDistribution(double mu, double sigma) {
    super(new Well19937c());
    this.mu = mu;
    this.sigma = sigma;
    this.alpha = -mu / sigma;
    this.beta = (1.0d - mu) / sigma;
    this.Z = normalDistribution.cumulativeProbability(beta) - normalDistribution.cumulativeProbability(alpha);
  }

  private double xi(double x) {
    return (x - mu) / sigma;
  }

  @Override
  public double density(double x) {
    return normalDistribution.density(xi(x)) / (sigma * Z);
  }

  @Override
  public double cumulativeProbability(double x) {
    return (normalDistribution.cumulativeProbability(xi(x)) - normalDistribution.cumulativeProbability(alpha)) / Z;
  }

  @Override
  public double getNumericalMean() {
    return mu;
  }

  @Override
  public double getNumericalVariance() {
    return sigma;
  }

  @Override
  public double getSupportLowerBound() {
    return 0;
  }

  @Override
  public double getSupportUpperBound() {
    return 1;
  }

  @Override
  public boolean isSupportLowerBoundInclusive() {
    return true;
  }

  @Override
  public boolean isSupportUpperBoundInclusive() {
    return true;
  }

  @Override
  public boolean isSupportConnected() {
    return true;
  }

  @Override
  public double nextSample() {
    return sample();
  }
}