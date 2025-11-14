package com.Devansh.Journal.Repo;

import com.Devansh.Journal.Entity.Journal_Entry;
import com.Devansh.Journal.Entity.config_App;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigureJournalApp extends MongoRepository<config_App, ObjectId> {

}
