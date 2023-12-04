package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {

    @Test
    void basicScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);

        // config 패키지에 AutoAppConfig 파일을 넣은경우 NoSuchBeanDefinitionException 에러가 발생됨
        // ComponentScan이 해당 패키지를 기준으로 탐색한다는
    }
}
