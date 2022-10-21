package reactiveTest;

import java.util.Arrays;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

public class MyPub implements Publisher<Integer> {
	
	Iterable<Integer> its = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

	public void subscribe(Subscriber<? super Integer> s) {
		System.out.println("������ : �����ǽ�");
		System.out.println("�Ź��� : ���� ���� ��������״� wait");
		
		MySubscription subscription = new MySubscription(s,its);
		System.out.println("�Ź��� : �������� ���� �Ϸ�");
		s.onSubscribe(subscription);
	}
}
