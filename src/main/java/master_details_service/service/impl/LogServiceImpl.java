package master_details_service.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import master_details_service.model.MessageLog;
import master_details_service.repository.MessageLogRepository;
import master_details_service.service.LogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class LogServiceImpl implements LogService {
private final MessageLogRepository repository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveLogMessage(String message) {
        MessageLog messageLog = new MessageLog();
        messageLog.setMessage(message);
        repository.save(messageLog);
        log.info("Added a new logMessage: " + message);
    }
}
