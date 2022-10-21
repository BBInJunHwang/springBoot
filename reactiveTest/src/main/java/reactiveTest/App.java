package reactiveTest;


// WebFlux = 단일 스레드, 비동기 + stream을 통해 백프래셔가 적용된 데이터만큼 간헐적 응답이 가능하다 + 데이터 소비가 끝나면 응답이 종료
// SSE 적용 = 데이터 소비가 끝나도 stream이 계속 유지, servlet Webflux 둘다 사용 가능 
public class App {

	public static void main(String[] args) {
		MyPub pub = new MyPub(); // 신문사 성성
		MySub sub = new MySub(); // 구독자 생성

		pub.subscribe(sub); // 구독실시
	}
}
