package com.Devansh.Journal.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@Document(collection = "config_journal_app")
public class config_App {
     private String key;
    private String value;
}
