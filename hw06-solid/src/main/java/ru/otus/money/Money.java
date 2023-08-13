package ru.otus.money;

import lombok.*;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Money {

    int value;

}
