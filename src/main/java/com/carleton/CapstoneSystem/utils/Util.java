package com.carleton.CapstoneSystem.utils;

import java.util.Collection;

public class Util {

    public static<E> boolean isCollectionEmpty(Collection<E> list) {
        return list == null || list.isEmpty();
    }
}
