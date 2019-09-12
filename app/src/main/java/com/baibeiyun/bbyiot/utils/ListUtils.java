package com.baibeiyun.bbyiot.utils;

import java.util.List;

/**
 * Created by mac on 16/9/2.
 */
public class ListUtils {

    public static <T> boolean isEmpty(List<T> list) {
        return list == null || list.isEmpty();
    }

}
