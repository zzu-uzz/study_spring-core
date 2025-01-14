package hello.core.xml;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import hello.core.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class XmlAppContext {

  @Test
  void xmlAppContext() {
    ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
    MemberService memberService = ac.getBean("memberService", MemberService.class);
    assertInstanceOf(MemberService.class, memberService);
  }
}
