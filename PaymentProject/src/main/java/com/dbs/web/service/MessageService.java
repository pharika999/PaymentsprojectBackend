package com.dbs.web.service;

 

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

 

import javax.persistence.EntityNotFoundException;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 

import com.dbs.web.beans.Customer;
import com.dbs.web.beans.Message;
import com.dbs.web.repository.MessageRepository;
import com.dbs.web.repository.MessageRepository;

 

@Service
public class MessageService {
    @Autowired
    private MessageRepository repo;

 

    public List<Message> getMessages()
    {
        List<Message> messages = new ArrayList<Message>();
        this.repo.findAll().forEach(msg->messages.add(msg));
        return messages;
    }
    public Message findMessageById(String id)
    {
        try {
            Optional<Message> message = this.repo.findById(id);
            return message.orElseThrow(()->{
                
                return new EntityNotFoundException("Message with code"+id + " does not exist");
            });

 


        }catch(IllegalArgumentException e )
        {
            return null;
        }

 

    }

 

}
 