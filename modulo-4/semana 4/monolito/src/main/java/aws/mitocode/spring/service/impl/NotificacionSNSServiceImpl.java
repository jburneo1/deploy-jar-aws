package aws.mitocode.spring.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.fasterxml.jackson.databind.ObjectMapper;

import aws.mitocode.spring.model.FeedBack;
import aws.mitocode.spring.service.INotificacionSNS;

@Service
public class NotificacionSNSServiceImpl implements INotificacionSNS {
	
	private Logger logger = Logger.getLogger(NotificacionSNSServiceImpl.class);

	@Value("${arnSNS}")
	private String arnSNS;

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private AmazonSNSClient servicioSNS;

	public void enviarNotificacionSubscriptores(FeedBack feedback) {
		try {
			//PublishRequest publishRequest = new PublishRequest("arn:aws:sns:" + awsRegion + ":" + accountIdAWS + ":" + nameTopicSNS,
			PublishRequest publishRequest = new PublishRequest(
				arnSNS,
				mapper.writeValueAsString(feedback));
			
			PublishResult publishResult = servicioSNS.publish(publishRequest);
			
			logger.info("MessageId - " + publishResult.getMessageId());
		} catch (Exception e) {
			logger.error("Error al enviar mensaje a SNS ", e);
		}
	}
}
