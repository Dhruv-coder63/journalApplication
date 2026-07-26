package net.edigest.journalApp.JournalApplication.repository;

import net.edigest.journalApp.JournalApplication.entity.ConfigJournalAppEntity;
import net.edigest.journalApp.JournalApplication.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournalAppEntity, ObjectId>{
}
