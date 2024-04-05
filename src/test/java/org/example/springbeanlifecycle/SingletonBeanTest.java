package org.example.springbeanlifecycle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@SpringBootTest
@Component
public class SingletonBeanTest {
  @Autowired
  private ApplicationContext context;

  @Autowired
  private CustomBeanPostProcessor customBeanPostProcessor;

  @DisplayName("Проверяем работу создания singleton бинов и работу BPP")
  @Test
  public void testSingletonBeans() {
    // Получаем два экземпляра singleton бинов
    SingletonBean singletonBean1 = context.getBean(SingletonBean.class);
    SingletonBean singletonBean2 = context.getBean(SingletonBean.class);

    // Проверяем, что хэш-коды этих экземпляров равны
    assertEquals(System.identityHashCode(singletonBean1), System.identityHashCode(singletonBean2));

    // Проверяем, что BPP работал всего лишь один раз на создание двух бинов
    assertEquals(1, customBeanPostProcessor.getSingletonCreationCounter());

    // Создаем еще один бин, тем самым проверяем что BPP не работает, так как бины получаем из кэша
    SingletonBean singletonBean3 = context.getBean(SingletonBean.class);
    assertEquals(1, customBeanPostProcessor.getSingletonCreationCounter());
  }
}
