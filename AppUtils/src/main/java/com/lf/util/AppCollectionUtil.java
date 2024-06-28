package com.lf.util;

import java.util.Collection;

/**
 * @date: 2024/6/27
 */
public class AppCollectionUtil {

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.size() == 0;
    }

    public static boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }
}
