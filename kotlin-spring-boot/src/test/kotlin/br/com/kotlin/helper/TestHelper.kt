package br.com.kotlin.helper

import org.jeasy.random.EasyRandom

inline fun <reified T> dummyObject(): T = EasyRandom().nextObject(T::class.java)