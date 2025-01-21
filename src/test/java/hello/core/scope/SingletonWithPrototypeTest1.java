package hello.core.scope;

import static org.junit.jupiter.api.Assertions.assertEquals;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonWithPrototypeTest1 {

  @Test
  void prototypeFind() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
        PrototypeBean.class);
    PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
    prototypeBean1.addCount();
    assertEquals(1, prototypeBean1.getCount());

    PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
    prototypeBean2.addCount();
    assertEquals(1, prototypeBean2.getCount());
  }

  @Test
  void singletonClientUsePrototype() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
        PrototypeBean.class, ClientBean.class);

    ClientBean clientBean1 = ac.getBean(ClientBean.class);
    int count1 = clientBean1.logic();
    assertEquals(1, count1);

    ClientBean clientBean2 = ac.getBean(ClientBean.class);
    int count2 = clientBean2.logic();
    assertEquals(1, count2);
  }

  @Scope("singleton")
  static class ClientBean {

//    @Autowired
//    private ObjectProvider<PrototypeBean> prototypeBeanProvider;
    @Autowired
    private Provider<PrototypeBean> prototypeBeanProvider;

    public ClientBean(Provider<PrototypeBean> prototypeBeanProvider) {
      this.prototypeBeanProvider = prototypeBeanProvider;
    }

    public int logic() {
//      PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
      PrototypeBean prototypeBean = prototypeBeanProvider.get();
      prototypeBean.addCount();
      return prototypeBean.getCount();
    }
  }

  @Scope("prototype")
  static class PrototypeBean {

    private int count = 0;

    public void addCount() {
      count++;
    }

    public int getCount() {
      return count;
    }

    @PostConstruct
    public void init() {
      System.out.println("PrototypeBean.init " + this);
    }

    @PreDestroy
    public void destroy() {
      System.out.println("PrototypeBean.destroy");
    }
  }
}
