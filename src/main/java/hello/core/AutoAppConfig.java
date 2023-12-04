package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

// ComponentScan이 해당 패키지를 기준으로 탐색
// config 패키지에 넣으면 테스트코드가 실패된다.
@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                classes = Configuration.class
        )
)
public class AutoAppConfig {
}
