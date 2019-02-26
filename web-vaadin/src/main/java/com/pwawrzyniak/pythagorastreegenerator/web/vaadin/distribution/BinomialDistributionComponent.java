package com.pwawrzyniak.pythagorastreegenerator.web.vaadin.distribution;

import com.pwawrzyniak.pythagorastreegenerator.backend.service.distribution.Random01Sampler;
import com.pwawrzyniak.pythagorastreegenerator.backend.service.distribution.BinomialDistribution;
import com.vaadin.flow.component.textfield.TextField;

public class BinomialDistributionComponent extends AbstractDistributionComponent {

  private TextField trialsTextField = new TextField("trials", "10", "");
  private TextField pTextField = new TextField("p", "0.5", "");

  public BinomialDistributionComponent() {
    super("Binomial distribution");
    add(trialsTextField, pTextField);
  }

  @Override
  public Random01Sampler getSampler() {
    return new BinomialDistribution(Integer.parseInt(trialsTextField.getValue()), Double.parseDouble(pTextField.getValue()));
  }
}