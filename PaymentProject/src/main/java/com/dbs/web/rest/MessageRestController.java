package com.dbs.web.rest;

 

import java.util.List;

 

import javax.persistence.EntityNotFoundException;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

 

import com.dbs.web.beans.Customer;
import com.dbs.web.beans.Message;
import com.dbs.web.service.MessageService;

 


@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/message")
public class MessageRestController {

 

    @Autowired
    private MessageService service; 
    
    @GetMapping
    public List<Message> getMessages()
    {
        return this.service.getMessages();
    }
    @GetMapping("/{msgcode}")
        public ResponseEntity<String> findMessageById(@PathVariable String msgcode)
        {
            try { 
                Message message= this.service.findMessageById(msgcode);
                return ResponseEntity.status(HttpStatus.OK)
                        .body(message.getInstruction());
                        
                
            }catch (EntityNotFoundException e) {
                System.out.println("error");
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Message with id "+msgcode+"not found");
            }
        }

 

}
 