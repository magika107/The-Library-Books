package mft.model.entities;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class Books {
    private int id;
    private String title;
    private String author;
    private String isbn;
    private String category;
}
