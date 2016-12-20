package service;

import java.util.Calendar;
import java.util.List;

import po.Message;
import dao.MessageDao;

public class MessageService {
	MessageDao dao=new MessageDao();
	//add
	public void add(Message message){
		dao.insert(message.getSenderId(), message.getReceiverId(), message.getMessage(), message.getTime());
	}
	//get by senderid receiverid
	public List<Message> get(int senderId,int receiverId){
		return dao.select(senderId, receiverId);
	}
	//get log count
	public long getCount(int senderId,int receiverId){
		return dao.selectCount(senderId, receiverId);
	}
}
