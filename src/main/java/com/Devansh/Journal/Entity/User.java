package com.Devansh.Journal.Entity;

import javax.validation.constraints.NotNull;

import com.mongodb.annotations.Beta;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "User")
public class User {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    @NonNull
    private String userName;
    @NotNull
    private String password;
    private String email;
    private boolean SentimentalAnalysis;

    @DBRef
    List<Journal_Entry> journalEntryList = new ArrayList<>();

    List<String> roles = new ArrayList<>();
}
