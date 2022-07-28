package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/*
    bean 생성 주기
    스프링 컨테이너 생성 -> 스프링 빈 생성 -> 의존 관계 주입 -> 초기화 콜백 -> 사용 -> 종료 콜백 -> 스프링 종료
 */
public class NetworkClient {

    public String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작 시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call = " + url + " message = " + message);
    }

    //서비스 종료 시 호출
    public void disconnect() {
        System.out.println("close: " + url);
    }

    /*
        InitializingBean, DisposableBean 단점
        해당 코드가 스프링에 의존한다, 메소드 이름 변경이 불가하다.
        거의 사용 x
     */
//    //InitializingBean
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        connect();
//        call("초기화 연결 메세지");
//    }
//
//    //DisposableBean
//    @Override
//    public void destroy() throws Exception {
//        disconnect();
//    }

    /*
        @PostConstruct, @PreDestroy
        스프링에서 권장하는 방법
        자바의 기술, 다른 컨테이너에서도 사용 가능
        단, 외부 라이브러리에서는 사용 불가. 코드를 변경하지 못하는 경우 @Bean 설정을 사용
     */
    @PostConstruct
    public void init() {
        connect();
        call("초기화 연결 메세지");
    }

    @PreDestroy
    public void close() {
        disconnect();
    }
}

