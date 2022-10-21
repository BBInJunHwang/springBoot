package reactiveTest;

import java.util.Iterator;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

// 구독정보(구독자, 어떤 데이터 구독할지) 2가지 정보 가져야함
public class MySubscription implements Subscription {

	private Subscriber s;
	private Iterator<Integer> its;

	public MySubscription(Subscriber s, Iterable<Integer> its) {
		this.s = s;
		this.its = its.iterator();
	}

	public void request(long n) {
		while(n > 0) {
			if(its.hasNext()) {
				s.onNext(its.next());		// 데이터가 있으면 다음 데이터, onNext 적용되면 flux라고 함
			}else {
				s.onComplete();				// 없으면 완료
				break;
			}
			n--;
		}
	}

	public void cancel() {

	}
}
