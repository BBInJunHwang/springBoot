package reactiveTest;

import java.util.Iterator;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

// ��������(������, � ������ ��������) 2���� ���� ��������
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
				s.onNext(its.next());		// �����Ͱ� ������ ���� ������, onNext ����Ǹ� flux��� ��
			}else {
				s.onComplete();				// ������ �Ϸ�
				break;
			}
			n--;
		}
	}

	public void cancel() {

	}
}
