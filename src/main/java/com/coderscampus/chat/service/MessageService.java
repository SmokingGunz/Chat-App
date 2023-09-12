package com.coderscampus.chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.chat.domain.Channel;
import com.coderscampus.chat.domain.Message;
import com.coderscampus.chat.domain.MessageDTO;
import com.coderscampus.chat.domain.User;
import com.coderscampus.chat.repository.ChannelRepository;
import com.coderscampus.chat.repository.MessageRepository;
import com.coderscampus.chat.repository.UserRepository;

@Service
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ChannelRepository channelRepository;

	public List<Message> findMessagesByChannelId(Long channelId) {
		List<Message> messages = messageRepository.findByChannel_Id(channelId);
		System.out.println("Messages fetched: " + messages.size());
		return messages;

	}

	public Message saveMessageForChannel(Long channelId, MessageDTO messageDTO) throws Exception {
		Message message = new Message();
		message.setContent(messageDTO.getContent());

		// Fetch the user based on the username from the DTO.
		User user = userRepository.findByUsername(messageDTO.getSender());
		if (user == null) {
			user = new User();
			user.setUsername(messageDTO.getSender());
			userRepository.save(user);
		}

		message.setUser(user);

		// Set the channel as before.
		Channel channel = channelRepository.findById(channelId).orElse(null);
		if (channel == null) {
			throw new Exception("Channel with ID " + channelId + " not found.");
		}
		message.setChannel(channel);

		return messageRepository.save(message);
	}

}
