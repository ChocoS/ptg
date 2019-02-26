package com.pwawrzyniak.pythagorastreegenerator.web.vaadin.distribution;

import com.pwawrzyniak.pythagorastreegenerator.backend.service.distribution.Random01Sampler;
import com.pwawrzyniak.pythagorastreegenerator.backend.service.distribution.TruncatedTo01NormalDistribution;
import com.vaadin.flow.component.textfield.TextField;

public class TruncatedTo01InvertedNormalDistributionComponent extends AbstractDistributionComponent {

  private TextField muTextField = new TextField("mu", "0.5", "");
  private TextField sigmaTextField = new TextField("sigma", "0.1", "");

  public TruncatedTo01InvertedNormalDistributionComponent() {
    super("Truncated inverted normal distribution");
    add(muTextField, sigmaTextField);
  }

  @Override
  public Random01Sampler getSampler() {
    return new Random01Sampler() {
      private Random01Sampler sampler = new TruncatedTo01NormalDistribution(Double.parseDouble(muTextField.getValue()), Double.parseDouble(sigmaTextField.getValue()));

      @Override
      public double nextSample() {
        return 1.0 - sampler.nextSample();
      }
    };
  }
}