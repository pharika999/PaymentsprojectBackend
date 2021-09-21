package com.dbs.web.rest;

 

import java.util.HashMap;
import java.util.List;
import java.util.Map;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dbs.web.beans.Transaction;
import com.dbs.web.service.TransactionService;

 

@RestController
@CrossOrigin
@RequestMapping("/transaction")
public class TransactionRestController {
    
    @Autowired
    private TransactionService transService;    
    
    @GetMapping("/{cid}")
    public List<Transaction> findTransactions(@PathVariable String cid)
    {
        return this.transService.getTransactions(cid);
    }
    @PostMapping
    public ResponseEntity<Map<String, String>> insertTransaction(@RequestBody Transaction transaction)
    {
        String s=this.transService.insertTransaction(transaction);
        Map<String,String> map=new HashMap<>();
        if(s.equalsIgnoreCase("success"))
        {
            
            //System.out.println("if block");
            map.put("message", s);
            return  ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(map);
        }
        //System.out.println("outside");
        map.put("message",s);
        return  ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(map);
    }
}