package com.edts.edtstechnicaltest.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Grade {
    MANAGER(1, 0.10),
    SUPERVISOR(2, 0.6),
    STAFF(3, 0.3);

    public final int code;
    public final double bonusPercent;

}
