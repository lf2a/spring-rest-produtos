package com.github.lf2a.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <h1>URL.java</h1>
 * ---
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 18/03/2021
 */
public class URL {

    public static String decodeParam(String s) {
        try {
            return URLDecoder.decode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    public static List<Integer> decodeIntList(String s) {
        return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
    }
}
