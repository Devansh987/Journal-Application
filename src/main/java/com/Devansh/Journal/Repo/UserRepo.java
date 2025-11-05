package com.Devansh.Journal.Repo;

import com.Devansh.Journal.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, ObjectId> {

//    public User FindByUsername(String userName);

    User findByUserName(String userName);

    void deleteByUserName(String userName);
}
