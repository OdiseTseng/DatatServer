package tw.intelegence.ncsist.sstp.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.intelegence.ncsist.sstp.bean.Message;
import tw.intelegence.ncsist.sstp.repo.MessageRepository;

import java.util.List;

@Service("cannedMessageService")
public class MessageService {
	@Autowired
	private MessageRepository messageRepository;

	public Long getCannedId(){
		return messageRepository.findCannedId();
	}

	public List<Message> getCannedMessageList(){
		return messageRepository.findAllMessagesOrderByMessageLevelAndCannedId();
	}

	public List<Message> getCannedMessageListByMessageLevel(long messageLevel){
		return messageRepository.findMessagesByMessageLevelOrderByMessageLevelAndCannedId(messageLevel);
	}

	@Transactional
	public Message saveCannedMessage(Message message){
		return messageRepository.save(message);
	}


	@Transactional
	public List<Message> deleteCannedMessage (long id, long messageLevel){
		return messageRepository.delete(id, messageLevel);
	}

}
