package com.coderscampus.chat.web;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coderscampus.chat.domain.Message;
import com.coderscampus.chat.service.ChannelService;
import com.coderscampus.chat.service.MessageService;

@Controller
public class ChannelController {

    private final MessageService messageService;
    private final ChannelService channelService;

    public ChannelController(MessageService messageService, ChannelService channelService) {
        this.messageService = messageService;
        this.channelService = channelService;
    }

    @GetMapping("/channels/{channelId}")
    public String getChannelMessages(@PathVariable Long channelId, ModelMap model) {
        // Assuming the channel has a name
        String channelName = channelService.findChannelNameById(channelId);
        List<Message> messages = messageService.findMessagesByChannelId(channelId);
        
        model.put("channelName", channelName);
        model.put("messages", messages);
        
        return "channel";
    }

    @PostMapping("/channels/{channelId}/sendMessage")
    @ResponseBody
    public String sendMessage(@PathVariable Long channelId, 
                              @RequestBody Message message) {
        messageService.saveMessageForChannel(channelId, message);
        return "Message received"; // You can return any appropriate response here
    }
    
    @GetMapping("/channels/{channelId}/latestMessages")
    @ResponseBody
    public List<Message> getLatestMessages(@PathVariable Long channelId) {
        return messageService.findMessagesByChannelId(channelId);
    }
}
