package hello.core.singleton;

public class SingletonService {

    //static 영역에 객체 하나를 미리 생성
    private static final SingletonService instance = new SingletonService();

    //public 으로 객체 instance가 필요할 때 static 메서드를 동해서만 조회하도록 허용
    public static SingletonService getInstance() {
        return instance;
    }

    //private 생성자를 통해 외부에서 새로운 객체 생성 방지
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
    
}
