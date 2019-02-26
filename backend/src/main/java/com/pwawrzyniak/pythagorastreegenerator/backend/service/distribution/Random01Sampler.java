package com.pwawrzyniak.pythagorastreegenerator.backend.service.distribution;

public interface Random01Sampler {

  // Any (0, 1) distribution
  double nextSample();
}