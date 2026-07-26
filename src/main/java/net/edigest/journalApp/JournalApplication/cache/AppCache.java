package net.edigest.journalApp.JournalApplication.cache;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import net.edigest.journalApp.JournalApplication.entity.ConfigJournalAppEntity;
import net.edigest.journalApp.JournalApplication.repository.ConfigJournalAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
@Component
public class AppCache {

    public enum keys{
        WEATHER_API;
    }
    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    public Map<String, String> appCache;

    @PostConstruct
    public void init(){
        appCache = new HashMap<>();
        try {
        List<ConfigJournalAppEntity> all = configJournalAppRepository.findAll();
        for (ConfigJournalAppEntity configJournalAppEntity : all) {
            appCache.put(configJournalAppEntity.getKey(), configJournalAppEntity.getValue());
        }
        } catch (Exception e) {
            log.error("Error",e);
        }
    }
}
