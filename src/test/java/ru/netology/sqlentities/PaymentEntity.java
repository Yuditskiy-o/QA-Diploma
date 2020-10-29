package ru.netology.sqlentities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.val;

@Data
@NoArgsConstructor
public class PaymentEntity {
    val id;
    val amount;
    val created;
    val status;
    val transaction_id;
}
