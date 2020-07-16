package com.activity.demo.command;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 发起者
 * @Author  HeMingXin
 * @Date  2020/7/16 19:38
 */
public class Macro {
    private final List<Action> actions;

    public Macro() {
        actions = new ArrayList<>();
    }

    public void record(Action action) {
        actions.add(action);
    }

    public void run() {
        actions.forEach(Action::perform);
    }
}
