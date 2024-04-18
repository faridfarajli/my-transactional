package az.transactional;

import az.transactional.service.Service;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequiredArgsConstructor
public class MyTransactionalApplication {
	private final Service service;

	private static final Logger LOG = LoggerFactory.getLogger(MyTransactionalApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MyTransactionalApplication.class, args);
	}


	@RequestMapping("/insert")
	public void insert(){
		service.insertStudents();
		service.getAll().forEach(student -> {
			LOG.info(student.toString());
		});

		service.insertStudentsExp();
		service.getAll().forEach(student -> {
			LOG.info(student.toString());
		});
	}
}
