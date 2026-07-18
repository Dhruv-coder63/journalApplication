package net.edigest.journalApp.JournalApplication.repository;

import net.edigest.journalApp.JournalApplication.entity.JournalEntry;
import net.edigest.journalApp.JournalApplication.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId>{
    User findByUsername(String username);

    void deleteByUsername(String name);
}
