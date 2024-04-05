package org.example.springbeanlifecycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {
  private int prototypeCreationCounter = 0;
  private int singletonCreationCounter = 0;

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    if (beanName.equals("prototypeBean")) {
      prototypeCreationCounter++;
    }
    if (beanName.equals("singletonBean")) {
      singletonCreationCounter++;
    }
    return bean;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    return bean;
  }

  public int getPrototypeCreationCounter() {
    return prototypeCreationCounter;
  }

  public int getSingletonCreationCounter() {
    return singletonCreationCounter;
  }
}