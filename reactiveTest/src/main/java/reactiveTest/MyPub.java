package reactiveTest;

import java.util.Arrays;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

public class MyPub implements Publisher<Integer> {
	
	Iterable<Integer> its = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

	public void subscribe(Subscriber<? super Integer> s) {
		System.out.println("구독자 : 구독실시");
		System.out.println("신문사 : 구독 정보 만들어줄테니 wait");
		
		MySubscription subscription = new MySubscription(s,its);
		System.out.println("신문사 : 구독정보 생성 완료");
		s.onSubscribe(subscription);
	}
}
