package reactiveTest;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class MySub implements Subscriber<Integer> {
	
	private Subscription s;
	private int bufferSize = 3;	// 한번에 받을 개수 
	
	public void onSubscribe(Subscription s) {
		System.out.println("구독자 : 구독정보 잘 받았어");
		this.s = s;
		
		System.out.println("구독자 : 나 이제 신문 1개씩 줘");
		s.request(bufferSize); // 신문 bufferSize씩 매일매일줘 (백 프래셔) -> 소비자가 한번에 처리 할 수 있는 개수 요청
							   // request란 한번에 받을 개수 설정
	}

	
	public void onNext(Integer t) {
		System.out.println("onNext():"+t);
		bufferSize--;
		if(bufferSize == 0) {
			System.out.println("하루 지남");
			bufferSize = 3;
			s.request(bufferSize);
		}
	}

	public void onError(Throwable t) {
		System.out.println("구독 중 에러");
	}

	public void onComplete() {
		System.out.println("구독 완료");
	}
}
