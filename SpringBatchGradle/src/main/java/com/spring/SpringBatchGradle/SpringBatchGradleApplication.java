package com.spring.SpringBatchGradle;


import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



/**
 * Spring web ������Ʈ�� �ƴϱ� ������
 * ��ġ������Ʈ�� ���� �� ��ġ�� ������ -> ���� �� ����, ������ �ٷ� ����
 * 
 * properties -> run/debug arguments -> --job.name=taskletJob v=1..v=2... ����� taskletJob name�� version v ���� ��� �־��ش�
 * ���� ������ �ѹ� �� ��ġ�� �ٽ� ���� �ʴ´�.
 * 
 * */
@EnableBatchProcessing	// �ش� ������Ʈ�� ��ġ�Դϴ� ����
@SpringBootApplication
public class SpringBatchGradleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchGradleApplication.class, args);
	}

}
