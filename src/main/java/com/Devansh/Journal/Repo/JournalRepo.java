package com.Devansh.Journal.Repo;

import com.Devansh.Journal.Entity.Journal_Entry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalRepo extends MongoRepository<Journal_Entry, ObjectId> {
}
