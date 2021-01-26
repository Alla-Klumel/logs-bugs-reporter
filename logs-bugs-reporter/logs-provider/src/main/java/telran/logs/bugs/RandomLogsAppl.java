package telran.logs.bugs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RandomLogsAppl {
@Autowired
RandomLogs randomLogs;
	public static void main(String[] args) {
		SpringApplication.run(RandomLogsAppl.class, args);
 
	}

}
