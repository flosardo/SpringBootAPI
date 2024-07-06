package com.amigosCode.demo.mapper;

public interface IMapper<Input, Output> {
  public Output map(Input input);
}
