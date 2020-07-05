package aws.mitocode.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClient;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.sns.AmazonSNSClient;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories
@EnableWebMvc
public class App extends WebMvcConfigurerAdapter{

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
	//Es importante indicar que esta demo se probo en la region us-east-1 (Virginia)
	//Aunque el código puede funcionar en otras regiones
	@Bean
	public AmazonSimpleEmailService crearSES() {
		return  new AmazonSimpleEmailServiceClient( new DefaultAWSCredentialsProviderChain() );
	}
	
	@Bean
	public AmazonSNSClient crearSNS() {
		AmazonSNSClient snsClient = new AmazonSNSClient(new DefaultAWSCredentialsProviderChain());
		snsClient.setRegion(Region.getRegion(Regions.fromName(System.getenv("aws_Region"))));
		return snsClient;
	}

	@Bean
	public AWSCognitoIdentityProviderClient CognitoClient() {        
        AWSCognitoIdentityProviderClient cognitoClient = new AWSCognitoIdentityProviderClient(new DefaultAWSCredentialsProviderChain());
		cognitoClient.setRegion(Region.getRegion(Regions.fromName(System.getenv("aws_Region"))));
		return cognitoClient;
	}
	
}
