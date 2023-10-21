package tw.intelegence.ncsist.sstp.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.intelegence.ncsist.sstp.bean.CannedMessage;
import tw.intelegence.ncsist.sstp.repo.CannedMessageRepository;

import java.util.List;

@Service("cannedMessageService")
public class CannedMessageService {
	@Autowired
	private CannedMessageRepository cannedMessageRepository;

	public Long getCannedId(){
		return cannedMessageRepository.findCannedId();
	}

	public List<CannedMessage> getCannedMessageList(){
		return cannedMessageRepository.findAllCannedMessagesOrderByMessageLevelAndCannedId();
	}

	public List<CannedMessage> getCannedMessageListByMessageLevel(long messageLevel){
		return cannedMessageRepository.findCannedMessagesByMessageLevelOrderByMessageLevelAndCannedId(messageLevel);
	}

	@Transactional
	public CannedMessage saveCannedMessage(CannedMessage cannedMessage){
		return cannedMessageRepository.save(cannedMessage);
	}


	@Transactional
	public List<CannedMessage> deleteCannedMessage (long id, long messageLevel){
		return cannedMessageRepository.delete(id, messageLevel);
	}

}
