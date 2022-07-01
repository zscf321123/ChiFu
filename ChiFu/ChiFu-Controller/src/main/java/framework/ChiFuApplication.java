package framework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
//@PropertySource(
//	    value = { "classpath:aaaa.properties" }, 
//	    ignoreResourceNotFound = true,
//	    encoding = "UTF-8"
//	)
@ServletComponentScan
//@ComponentScan(basePackages="framework.controller")
//public class ChiFuApplication {
//	
//	public static void main(String[] args) {
//		SpringApplication.run(ChiFuApplication.class, args);
//	}
//
//}

public class ChiFuApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ChiFuApplication.class, args);
	}

}