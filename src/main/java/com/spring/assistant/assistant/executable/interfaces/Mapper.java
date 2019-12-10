package com.spring.assistant.assistant.executable.interfaces;

import java.util.function.BiFunction;


/**
 * @author semih on Aralık, 2019
 */
public interface Mapper<T, U, R> extends BiFunction<T, U, R> {
}
