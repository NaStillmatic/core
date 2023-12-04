package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA: A사용자 10000원 주문
        statefulService1.order("userA", 10000);
        // ThreadB: B사용자 10000원 주문
        statefulService2.order("userB", 20000);

        // ThreadA: 사용자A 주문 금액 조회
        int price = statefulService1.getPrice();
        System.out.println("price = " + price);

        assertThat(statefulService1.getPrice()).isEqualTo(20000);

        /**
         - 최대한 단순히 설명하기 위해, 실제 쓰레드는 사용하지 않았다.
         - ThreadA가 사용자A 코드를 호출하고 ThreadB가 사용자 B 코드르를 호출한다 가장하자.
         - StatefulService 의 price필드는 공유되는 필드인데, 특정 클라이언트가 값을 변경한다.
         - 사용자A의 주문금액은 10000원이 되어야 하는데, 20000원이라는 겨로가가 나왔다.
         - 실무에서 이런 경우를 종종 보는데, 이로인해 정말 해결하기 어려운 큰 문제들이 터진다. (몇년에 한번씩 꼭 만난다.)
         - 진짜 공유필드는 조심해야 한다! 스프링 빈은 항상 무상태(stateless)로 설계하자.
         */
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}