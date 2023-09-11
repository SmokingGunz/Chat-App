package com.coderscampus.chat.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.chat.domain.Channel;
import com.coderscampus.chat.repository.ChannelRepository;

@Service
public class ChannelService {

	@Autowired
	private ChannelRepository channelRepository;

	public List<Channel> findAllChannels() {

		return channelRepository.findAll();
	}

	public List<Channel> ensureChannelsAndFetchAll() {
		List<Channel> channels = findAllChannels();
        if (channels.isEmpty()) {
            channelRepository.saveAll(Arrays.asList(
                    new Channel(1L, "General"),
                    new Channel(2L, "Tech Talk"),
                    new Channel(3L, "Whats New")
            ));
        }
        return findAllChannels();
	}

	public String findChannelNameById(Long channelId) {

		Optional<Channel> channel = channelRepository.findById(channelId);
		if (channel.isPresent()) {
			return channel.get().getName();
		}

		return null;
	}

}
