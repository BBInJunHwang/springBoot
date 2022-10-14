package com.spring.SpringBatchGradle.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Tasklet�� ��� ���� or ������ 2������ ���� 
 * chunk�� �޸� RPW ������ �������°� �ƴ�, 1ȸ�� or RPW�� �ѹ��� ��Ƴ��� �����Ͻ� ���� ���
 * chunk�� �޸� ��Ÿ ���̺� read,write count �ȳ��� 
 * -> ���� �����ؾ��� 
 * 
 * RepeatStatus.FINISHED	- ó���� ���� ���� ������� �½�ũ���� �Ϸ��ϰ� ���� ó���� �̾ �ϰڴ�
 * RepeatStatus.CONTINUABLE - ������ ��ġ���� �ش� �½�ũ���� �ٽ� ����
 * 
 * */

@Slf4j
@RequiredArgsConstructor
@Configuration
public class TaskletJob {

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	
	
	@Bean
	public Job taskletJob_batchBuild() {
		return jobBuilderFactory.get("taskletJob")
				.start(taskletJob_step1())
				.next(taskletJob_step2(null))	// next �̿��ؼ� step �� �����Ѵ�.
				.build();
	}
	
	
	/**
	 * contribution - ���� �ܰ� ������ ������Ʈ�ϱ� ���� �ٽ� ���޵Ǵ� ���� ������ ����
     * chunkContext - ȣ�� ������ ���������� ����� ������ �������� �ʴ� �Ӽ�
	 * */
	@Bean
	public Step taskletJob_step1(){
		return stepBuilderFactory.get("taskletJob_step1")
				.tasklet((contribution,chunkContext)->{
					log.debug("-> job -> [step1]");
					return RepeatStatus.FINISHED;
				}).build();
	}
	
	@Bean
	@JobScope	// late binding - Bean ���������� ���ø����̼� ������ �ƴ� ������ scope�� ����Ǵ� ���� -> ������ ����Ǵ� ������ ���� ���� �� ���� step�� ���� or �������� ��ó��
	public Step taskletJob_step2(@Value("#{jobParameters[date]}") String date){
		return stepBuilderFactory.get("taskletJob_step2")
				.tasklet((a,b)->{
					log.debug("-> step1 -> [step2]" + date);
					return RepeatStatus.FINISHED;
				}).build();
	}
	
}
