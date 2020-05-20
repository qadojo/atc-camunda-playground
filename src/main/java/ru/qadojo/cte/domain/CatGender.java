package ru.qadojo.cte.domain;

import static java.lang.String.format;

public enum CatGender {
    MALE, FEMALE;

    public static CatGender from(Object value) {
        if (value == null) {
            return null;
        }

        final String stringVal = value.toString();

        for (CatGender gender : CatGender.values()) {
            if (gender.name().equals(stringVal)) {
                return gender;
            }
        }

        throw new IllegalArgumentException(
            format("unable to construct %s from value %s", CatGender.class.getName(), stringVal)
        );
    }
}
