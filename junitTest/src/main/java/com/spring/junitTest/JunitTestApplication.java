package com.spring.junitTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



/**
 * Junit Test ->  DB ���� ���¿��� �׽�Ʈ
 * Security Test -> �׽�Ʈ
 * 
 * TDD �׽�Ʈ �ֵ� ����� (���� ������ ���)
 * ���� -> Junit Test �ۼ� -> ������ ���� ���ɼ� �׽�Ʈ (������ ������ �߻���Ŵ) -> ���� -> ���ڵ�
 * 
 * ������ ���� �̷����Ѵ�..? TDD�� ����������.. �ʼ��� �ƴϰ� �ڱ⸸�� ������� �غ���!
 * ���� -> ���ڵ� -> Junit Test �ۼ� -> ���ڵ� ����
 * 
 * localhost:8080/h2-console 
 * 
 * url : jdbc:h2:mem:test ����
 * username �״��, pw ���� testConnection �ؼ� h2-console ���� �����ϴ�.
 * 
 * git�� ��Ű�� ��������� ���������� �ݿ� �ȵɋ��� �ִ�.
 * 
 * 
 * test ����
 * 1. Repository �׽�Ʈ (å�� : DB �� ����, ��ȸ �ߵǴ���)
 * 2. Service �׽�Ʈ (å�� : ��ɵ��� ������ �°� ����Ǵ���, Ʈ����� ��Ÿ����)
 * 3. Controller �׽�Ʈ (å�� : Ŭ���̾�Ʈ�� �׽�Ʈ, �Ķ���� null üũ)
 * 
 * �̷��� �޼���� �ϳ��� ���(�ϳ��� å�Ӹ��� ����Ѵ� -> ��������)
 * 
 * */

@SpringBootApplication
public class JunitTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(JunitTestApplication.class, args);
	}

}
