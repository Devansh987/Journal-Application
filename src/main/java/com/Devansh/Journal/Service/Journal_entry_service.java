package com.Devansh.Journal.Service;

import com.Devansh.Journal.Entity.Journal_Entry;
import com.Devansh.Journal.Entity.User;
import com.Devansh.Journal.Repo.JournalRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class Journal_entry_service {

    @Autowired
    private JournalRepo journalRepo;

    @Autowired
    private User_Service userService;

    public List<Journal_Entry> getAll(){
        return journalRepo.findAll();
    }

    @Transactional
    public void saveEntry(Journal_Entry journalEntry, String userName){
        try{
            User user=userService.findByUserName(userName);
            Journal_Entry saved=journalRepo.save(journalEntry);
            user.getJournalEntryList().add(saved);
            userService.saveUser(user);
        }
        catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("this is error",e);
        }
    }

    public  void  saveEntry(Journal_Entry journalEntry){
        journalRepo.save(journalEntry);
    }

    public Optional<Journal_Entry> findById(ObjectId id){
        return journalRepo.findById(id);
    }

    public void  deleteById(ObjectId id){journalRepo.deleteById(id);}

    public void deleteById(ObjectId id, String userName) {
        try {
            User user = userService.findByUserName(userName);
            boolean removed = user.getJournalEntryList().removeIf(a -> a.getId().equals(id));
            if (removed)
                userService.saveUser(user);
            journalRepo.deleteById(id);
        }catch (Exception e){
            System.out.print(e);
            throw new RuntimeException("Error occurred while deleting this entry "+e);
    }

    }

    public Optional<Journal_Entry> getById(ObjectId id){
        return journalRepo.findById(id);
    }




}
