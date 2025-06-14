package mft.model.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.awt.print.Book;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@SuperBuilder
public class Loans {
    private int id;
    private Person person;
    private Book book;
    private LocalDate loanDate;
    private LocalDate returnDate;
}
