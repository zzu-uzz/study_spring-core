package hello.core.beanfind;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextBasicFindTest {

  AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

  @Test
  @DisplayName("빈 이름으로 조회")
  void findBeanByName() {
    MemberService memberService = ac.getBean("memberService", MemberService.class);
    assertInstanceOf(MemberServiceImpl.class, memberService);
  }

  @Test
  @DisplayName("이름 없이 빈 타입으로만 조회")
  void findBeanByType() {
    MemberService memberService = ac.getBean(MemberService.class);
    assertInstanceOf(MemberServiceImpl.class, memberService);
  }

  @Test
  @DisplayName("구체적인 타입으로 조회")
  void findBeanByName2() {
    MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
    assertInstanceOf(MemberServiceImpl.class, memberService);
  }

  @Test
  @DisplayName("빈 이름으로 조회X")
  void findByNameX() {
//    ac.getBean("xxxx", MemberService.class);
    assertThrows(NoSuchBeanDefinitionException.class,
        () -> ac.getBean("xxxx", MemberService.class));
  }
}
