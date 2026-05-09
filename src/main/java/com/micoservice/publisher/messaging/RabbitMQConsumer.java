package com.micoservice.publisher.messaging;

import com.micoservice.publisher.domain.dto.request.FraudAnalysisDTO;
import com.micoservice.publisher.domain.services.FraudAnalysisService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {

    @Autowired
    FraudAnalysisService fraudAnalysisService;
    @RabbitListener(queues = "${broker.queue.processamento.name}")
    public void listener(FraudAnalysisDTO fraudAnalysisDTO){
        fraudAnalysisService.create(fraudAnalysisDTO);
    }
}
