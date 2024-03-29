package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

// baseBackages : 탐색할 패키지의 시작 위치를 지정한다. 이 패키지ㅡㄹ 포함해서 하위 패키지를 모두 탐색한다.
// - basePackages = {"hello.core", "hello.service} 이렇게 여러 시작 위치를 지정할 수도 있다.
// - basePackageClass: 지정한 클래스의 패키지를 탐색 시작 위로 정한다.
// 만약 지정하지 않으면 @ComponentScan 이 붙은 설정 정보 클래스의 시작 위치가 된다.

/**
 * 권장하는 방법
 *
 * 개인적으로 즐겨 사용하는 방법은 패키지 위치를 지정하지 않고, 설정 정보 클리스의 위치를 프로젝트 최상단에 두는 것이다.
 * 최근 스프링 부트도 이 방법을 기본으로 제공한다.
 *
 * 예를 들어서 프로젝트가 다음과 같이 구조가 되어 있으면
 * com.hello
 * com.hello.service
 * com.hello.repository
 *
 * com.hello -> 프로젝트 시작 루트, 여기에 AppConfig 같은 메인 설정 정보를 두고, @ComponentScan 애노테이션을 붙이고, basePackages 지정은 생략한다.
 *
 * 이렇게 하면 com.hello 를 포함한 하위는 모두 자동으로 컴포넌트 스캔의 대상이 된다.
 * 그리고 프로젝트 메인 설정 정보는 프로젝트를 대표하는 정보이기 때문에 프로젝트 시작 루트 위치에 두는것이 좋다 생각한다.
 * 참고로 스프링 부트를 사용하면 스프링 부트의 대표 시작 정보인 @SpringBootApplication 를 이 프로젝트 시작 루트 위치에 두는 것이 관례이다.
 * (그리고 이 설정안에 바로 @ComponentScan 이 들어있다.)
 */

@Configuration
@ComponentScan(
        basePackages = {"hello.core.member", "hello.core.service", "hello.core.repository", "hello.core.discount"},
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                classes = Configuration.class
        )
)
public class AutoAppConfig {
}
