1. BeanPostProcessor запускается всегда при старте приложения
   1. Даже создаются prototype бины
2. Если prototype бины создаются в момент запроса, то в каком состоянии они находятся?
   1. После bean post processor они сохраняются в одном месте, как ?

---------------
## Singleton Bean
GenericWebApplicationContext
GenericApplicationContext
AbstractApplicationContext

DefaultListableBeanFactory#resolveBean
DefaultListableBeanFactory#resolveBean

AbstractBeanFactory#doGetBean
AbstractAutowireCapableBeanFactory#createBean