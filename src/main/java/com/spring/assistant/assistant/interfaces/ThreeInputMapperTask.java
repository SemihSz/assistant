package com.spring.assistant.assistant.interfaces;

/**
 * @author semih on Aralık, 2019, 14.12.2019, 00:53:01
 */

/**
 * 3 input alan generic service T, S, K input.
 * Response ise R'dir
 */
public interface ThreeInputMapperTask<T, S, K, R> {

    R apply(T t, S s, K k);

}
