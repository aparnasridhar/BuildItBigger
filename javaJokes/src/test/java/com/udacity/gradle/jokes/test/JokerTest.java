package com.udacity.gradle.jokes.test;

import com.udacity.gradle.jokes.Joker;

public class JokerTest {


    public void test() {
        Joker joker = new Joker();
        assert joker.getJoke().length() != 0;
    }
}