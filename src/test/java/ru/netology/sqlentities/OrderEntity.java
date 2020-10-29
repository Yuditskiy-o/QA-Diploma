package ru.netology.sqlentities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.val;

@Data
@NoArgsConstructor
public class OrderEntity {
    val id;
    val created;
    val creditId;
    val payment_id;
}
