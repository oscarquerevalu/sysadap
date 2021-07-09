package pe.edu.upc.sysadap.spring.security.tasklet;

import java.util.Date;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import pe.edu.upc.sysadap.spring.security.service.EmailService;

public class EmailSendingTasklet implements Tasklet, InitializingBean {

	@Autowired private EmailService emailService;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		System.out.println("Inicio envío de mensaje:"+ new Date());
		emailService.sendEmail();
		System.out.println("Fin envío de mensaje");
		return RepeatStatus.FINISHED;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
//		Assert.notNull(directory, "directory must be set");
	}
}
