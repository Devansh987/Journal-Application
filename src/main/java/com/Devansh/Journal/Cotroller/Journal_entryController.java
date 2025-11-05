package com.Devansh.Journal.Cotroller;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.Devansh.Journal.Entity.Journal_Entry;
import com.Devansh.Journal.Entity.User;
import com.Devansh.Journal.Service.Journal_entry_service;
import com.Devansh.Journal.Service.User_Service;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/journal")
public class Journal_entryController {
        @Autowired
        Journal_entry_service journalEntryService;
        @Autowired
        User_Service userEntryService;
        @GetMapping
        public ResponseEntity<?> getAllJournalOfUser(){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            User user=userEntryService.findByUserName(userName);
            List<Journal_Entry> all=user.getJournalEntryList();
            if(all!=null&& !all.isEmpty()){
                return new ResponseEntity<>(all, HttpStatus.OK);
            }return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        @PostMapping
        public ResponseEntity<Journal_Entry> createEntry(@RequestBody Journal_Entry myEntry){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            try{
                journalEntryService.saveEntry(myEntry,userName);
                return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
            }catch (Exception e){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }


        @GetMapping("/id/{Myid}")
        public ResponseEntity<?> getById(@PathVariable ObjectId Myid){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            User user = userEntryService.findByUserName(userName);
            List<Journal_Entry> collect = user.getJournalEntryList().stream().filter(x->x.getId().equals(Myid)).collect(Collectors.toList());
            if(!collect.isEmpty()) {
                Optional<Journal_Entry> journalEntry = journalEntryService.getById(Myid);
                if (journalEntry.isPresent()) {
                    return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
                }
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        @DeleteMapping("/id/{Myid}")
        public ResponseEntity<?> deleteById(@PathVariable ObjectId Myid){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            journalEntryService.deleteById(Myid,userName);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        @PutMapping("/id/{Myid}")
        public ResponseEntity<?> updateById(@PathVariable ObjectId Myid,@RequestBody Journal_Entry myData){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            User user = userEntryService.findByUserName(userName);
            List<Journal_Entry> collect = user.getJournalEntryList().stream().filter(x->x.getId().equals(Myid)).collect(Collectors.toList());
            if(!collect.isEmpty()) {
                Optional<Journal_Entry> journalEntry = journalEntryService.getById(Myid);
                if (journalEntry.isPresent()) {
                    Journal_Entry old = journalEntry.get();
                    old.setContent(myData.getContent() != null && !myData.getContent().equals("") ? myData.getContent() : old.getContent());
                    old.setTitle(myData.getTitle() != null && !myData.getTitle().equals("") ? myData.getTitle() : old.getTitle());
                    journalEntryService.saveEntry(old);
                    return new ResponseEntity<>(old, HttpStatus.OK);
                }
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }


    }






