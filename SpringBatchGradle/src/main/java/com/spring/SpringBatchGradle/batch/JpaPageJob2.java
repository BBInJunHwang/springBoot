package com.spring.SpringBatchGradle.batch;




import javax.persistence.EntityManagerFactory;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.spring.SpringBatchGradle.domain.Dept;
import com.spring.SpringBatchGradle.domain.Dept2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * 
 * �ѹ� ������ ��ġ�� version �ٸ��ų� �Ķ���Ͱ� �ٸ��� �ʴ��� �������� �ʴ´�.
 * 
 * ������ ��ġ�� ��ü������ �����ٸ� ����� ����
 * �ܺο��� cronTab/Jeknins ����ؼ� JOB �Ķ���� �Ѱ������
 * �׽�Ʈ�� ���ؼ� program argument ���� job name�� job parameter ���� 
 * 
 * 
 * JobInstance 		- Job �������, Job ����� �ϳ��� JobInstance ����
 * 			     	ex) ����,���� Job ����� ���� �����Ǹ�, �ٽ� ���� Job ���� �� �ν��Ͻ� ����x ���� ���� ��ġ�� �ٽ� ����
 * 
 * Job Execution 	- JobInstance�� ����õ� ���� ��ü
 * 					ex) ���� �ν��Ͻ� ���н� �������� ���� �ٽý���Ǹ�, �̰�� ���� �ν��Ͻ�, ���� �ν��Ͻ� ���λ��� �����丮��
 * 
 * Step				- Job ��ġó�� ����, ���������� ĸ��ȭ, Job�� �Ѱ��̻� Step �ʿ� 
 * 					Tasklet�� chunk ó����� ����
 * 
 * Step Execution	- Step ����õ� ���� ��ü, Job�� ������ Step���� ������ ���� Step ���н� �����ܰ� ����x, stepExecution ����x
 * 					read ,write ,commit �� �� ����
 * 
 * Execution Context - Job���� �����͸� ������ �� �ִ� ������ �����, Job, Step 2���� ����
 * 					JobExecution Context�� Commit���� ����, StepExecution Context�� ���� ���� ����
 * 					Execution Context ���� Step�� ������ ���� �� Job ���н� ������ ���ప �籸�� ���� 
 * 
 * JobRepository	 - ��� Batch ó�� ��������, Job ����� job,step Execution ����
 * 					 Execution ������ �����ϰ� ��ȸ
 * 
 * JobLauncher		 - Job�� JobParameters ����� Job ���� ��ü
 * 
 * ItemReader		 - Step���� item �д� �������̽� 
 * 
 * ItemWriter		 - ó���� �����͸� insert,update,send(MQ�� ����) �Ҷ� ���
 * 						Item�� chunk ������ ���� ó��
 * 
 * ItemProcessor	 - Reader���� ���� item ó��
 * 					 �ʼ���Ҵ� �ƴϸ�, ������ ���� �� �����Ͻ� ����
 * 
 * 
 * 
 * 
 * 
 * 
 * */







@Slf4j
@RequiredArgsConstructor
@Configuration
public class JpaPageJob2 {

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	private final EntityManagerFactory entityManagerFactory;
	
	
	private int chunkSize = 20; // �߶� �ش� ũ�⸸ŭ DB ó�� 
	
	
	@Bean
	public Job JpaPageJob2_batchBuild() {
		return jobBuilderFactory.get("jpaPageJob2")
				.start(JpaPageJob2_step1()).build();
	}
	
	@Bean
	public Step JpaPageJob2_step1(){
		return stepBuilderFactory.get("jpaPageJob2_step2")
				.<Dept,Dept2>chunk(chunkSize)
				.reader(jpaPageJob2_dbItemReader())
				.processor(jpaPageJob2_processor())
				.writer(jpaPageJob2_dbItenWriter())
				.build();
	}
	

	@Bean
	public JpaPagingItemReader<Dept> jpaPageJob2_dbItemReader(){
		return new JpaPagingItemReaderBuilder<Dept>()
				.name("jpaPageJob2_dbItemReader")
				.entityManagerFactory(entityManagerFactory)
				.pageSize(chunkSize)
				.queryString("SELECT d FROM Dept d order by deptno asc")
				.build();
	}
	
	
	private ItemProcessor<Dept ,Dept2> jpaPageJob2_processor() {
		return dept ->{
			return new Dept2(dept.getDeptNo(),"NEW_"+ dept.getDName(), "NES_" + dept.getLoc());
		};
	}
	
	
	@Bean
	public JpaItemWriter<Dept2> jpaPageJob2_dbItenWriter(){
		JpaItemWriter<Dept2> jpaItemWriter = new JpaItemWriter<>();		// JpaItemWriter ���� ���Ӽ� ������ ���� EntityManager ���� �ʿ�
		jpaItemWriter.setEntityManagerFactory(entityManagerFactory);	// �Ѿ�� Entity�� �����ͺ��̽��� �ݿ�
																		// Entity Ŭ������ ���׸� Ÿ�� -> �Ѿ�� Item�� �״�� entityManger.merge()�� ���̺� �ݿ�
		return jpaItemWriter;
	}
}
