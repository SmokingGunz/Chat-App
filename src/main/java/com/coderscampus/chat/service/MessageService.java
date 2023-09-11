package com.coderscampus.chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.chat.domain.Message;
import com.coderscampus.chat.repository.MessageRepository;

@Service
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;

	public List<Message> findMessagesByChannelId(Long channelId) {
		return messageRepository.findByChannelId(channelId);
	}

	public Message saveMessageForChannel(Long channelId, Message message) {

		return messageRepository.save(message);

	}

}
