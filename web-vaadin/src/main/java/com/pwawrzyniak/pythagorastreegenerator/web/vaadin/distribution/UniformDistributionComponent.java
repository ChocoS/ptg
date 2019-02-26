package com.pwawrzyniak.pythagorastreegenerator.web.vaadin.distribution;

import com.pwawrzyniak.pythagorastreegenerator.backend.service.distribution.Random01Sampler;
import com.pwawrzyniak.pythagorastreegenerator.backend.service.distribution.UniformDistribution;

public class UniformDistributionComponent extends AbstractDistributionComponent {

  public UniformDistributionComponent() {
    super("Uniform distribution");
  }

  @Override
  public Random01Sampler getSampler() {
    return new UniformDistribution();
  }
}