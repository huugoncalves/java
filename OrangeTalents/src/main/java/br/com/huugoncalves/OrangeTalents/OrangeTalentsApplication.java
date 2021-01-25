package br.com.huugoncalves.OrangeTalents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "br.com.huugoncalves.OrangeTalents")
@EntityScan(basePackages = "br.com.huugoncalves.OrangeTalents.model")
public class OrangeTalentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrangeTalentsApplication.class, args);
	}

}
