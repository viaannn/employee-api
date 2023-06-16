package com.edts.edtstechnicaltest.util;

import com.edts.edtstechnicaltest.enumeration.Grade;

public class EmployeeUtil {

    public static long calculateTotalBonus(int grade, long salary) {
        long totalBonus = 0;
        if (Grade.MANAGER.code == grade) {
            totalBonus = salary + (long) (salary * Grade.MANAGER.bonusPercent);
        } else if (Grade.SUPERVISOR.code == grade) {
            totalBonus = salary + (long) (salary * Grade.SUPERVISOR.bonusPercent);
        } else if (Grade.STAFF.code == grade) {
            totalBonus = salary + (long) (salary * Grade.STAFF.bonusPercent);
        }

        return totalBonus;
    }


}
