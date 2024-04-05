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
public class PrototypeBeanTest {

  @Autowired
  private ApplicationContext context;

  @Autowired
  private CustomBeanPostProcessor customBeanPostProcessor;

  @DisplayName("Проверяем работу создания prototype бинов и работу BPP")
  @Test
  public void testPrototypeBeans() {
    // Получаем два экземпляра prototype бинов
    PrototypeBean prototypeBean1 = context.getBean(PrototypeBean.class);
    PrototypeBean prototypeBean2 = context.getBean(PrototypeBean.class);

    // Проверяем, что хэш-коды этих экземпляров не равны
    assertNotEquals(System.identityHashCode(prototypeBean1), System.identityHashCode(prototypeBean2));

    // Проверяем, что BPP работал два раза на создание двух бинов
    assertEquals(2, customBeanPostProcessor.getPrototypeCreationCounter());

    // Создаем еще один бин, тем самым проверяем что BPP работает в момент создания
    PrototypeBean prototypeBean3 = context.getBean(PrototypeBean.class);
    assertEquals(3, customBeanPostProcessor.getPrototypeCreationCounter());
  }
}
