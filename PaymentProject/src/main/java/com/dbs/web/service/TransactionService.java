package com.dbs.web.service;

 

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

 

import javax.persistence.EntityNotFoundException;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 

import com.dbs.web.beans.Customer;
import com.dbs.web.beans.Transaction;
import com.dbs.web.repository.CustomerRepository;
import com.dbs.web.repository.TransactionRepository;

 

@Service
public class TransactionService {
    
    @Autowired
    private TransactionRepository transactionRepo;
    
    @Autowired
    private CustomerService custService;
    
    @Autowired
    private CustomerRepository custrepo;
    @Autowired
    private SdnProcessing sdnservice;
    
    public List<Transaction> getTransactions(String custId)
    {
        List<Transaction> transactions = new ArrayList<Transaction>();
        this.transactionRepo.findAllByCustomerCustomerid(custId).forEach(trans->transactions.add(trans));
        return transactions;
    }
    
    public String insertTransaction(Transaction trans) 
    {
        System.out.println(trans);
        if(this.transactionRepo.findById(trans.getTransactionId()).isPresent())
            return "Transaction id already exists";
        try {
            Customer c=trans.getCustomer();
            double transferfees=trans.getInramount()*0.25;
            double totalamount=trans.getInramount()+transferfees;
            Customer rec = null;
             if(this.custrepo.findById(trans.getReceiverAccountNumber()).isPresent()) {
                     rec=custService.findCustomerById(trans.getReceiverAccountNumber());
            }
            System.out.println(c.getCustomerid());
            if(!sdnservice.checkSDNlist(trans.getReceiverAccountHolderName()))
            {
            if(c.getCustomertype()=='B')
            {
                System.out.println("Bank if block");
                
                if(rec!=null && rec.getCustomertype()=='B') {
                    if(c.isOverdraftflag()||totalamount<=c.getClearbalance())
                    {
                        
                        trans.setTransferfees(transferfees);
                        trans.setTransferdate(LocalDate.now());
                        System.out.println("Bank ");
                        //System.out.println(totalamount+"    "+trans.getInramount()+" "+c.getClearbalance());
                        c.setClearbalance(c.getClearbalance()-totalamount);
                        rec.setClearbalance(rec.getClearbalance()+trans.getInramount());
                        this.custService.updateCustomer(rec);
                        this.custService.updateCustomer(c);
                        this.transactionRepo.save(trans);
                    }
                    else
                    {
                        //System.out.println("Bank else block");
                        //throw new Exception("Transfer amount exceeded.Please check your balance!");
                        return "Payment unsuccessful.\n Transfer amount exceeded.Please check your balance!";
                    }
                }
                
                else
                     return  "Payment unsuccessful.\nYou can only transfer to other banks.\n kindly check your receiver account number";
            }
            else {
                //System.out.println("Customer if block");
                
                
                System.out.println(rec);
                if(rec!=null)
                    if( rec.getCustomertype()=='B')
                    return "Payment unsuccessful.\nYou are not allowed to transfer bank";
                if((c.getClearbalance()>0 &&c.isOverdraftflag()||totalamount<=c.getClearbalance()))
                {
                     System.out.println("Customer if block");
                    System.out.println(transferfees);
                    System.out.println(totalamount+"    "+trans.getInramount()+" "+c.getClearbalance()+" "+c.getAccountholdername());
                    trans.setTransferfees(transferfees);
                    trans.setTransferdate(LocalDate.now());
                    c.setClearbalance(c.getClearbalance()-totalamount);
                    //System.out.println(transferfees);
                    //System.out.println(totalamount+"    "+trans.getInramount()+" "+c.getClearbalance()+" "+c.getAccountholdername());
                    this.custService.updateCustomer(c);
                    this.transactionRepo.save(trans);
                }
                else
                {
                    System.out.println("Customer else block");
                    return "Payment unsuccessful.\nPlease check your balance!";
                }
            }
            }
            else
                return "Payment unsuccessful.\n Receiver is in sanction list";
        }catch(IllegalArgumentException e )
        {
            return "Payment unsuccessful";
        }
        return "Payment successful";
    }
}