package com.Devansh.Journal.Entity;

import org.bson.types.ObjectId;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.lang.annotation.Documented;

@Component
@Data
@NoArgsConstructor
@Document(collection = "journal_entry")
public class Journal_Entry {
    private ObjectId id;
    private String title;
    private String content;
}
