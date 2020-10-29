package ru.netology.sqlentities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.val;

@Data
@NoArgsConstructor
public class CreditRequestEntity {
    val id;
    val bank_id;
    val created;
    val status;
}
