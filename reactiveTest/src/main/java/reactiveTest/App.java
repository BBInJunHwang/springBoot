package reactiveTest;


// WebFlux = ���� ������, �񵿱� + stream�� ���� �������Ű� ����� �����͸�ŭ ������ ������ �����ϴ� + ������ �Һ� ������ ������ ����
// SSE ���� = ������ �Һ� ������ stream�� ��� ����, servlet Webflux �Ѵ� ��� ���� 
public class App {

	public static void main(String[] args) {
		MyPub pub = new MyPub(); // �Ź��� ����
		MySub sub = new MySub(); // ������ ����

		pub.subscribe(sub); // �����ǽ�
	}
}
