package com.activity.demo.command;

/**
 * @Description 具体命令者
 * @Author  HeMingXin
 * @Date  2020/7/16 19:39
 */
public class Open implements Action {
    private final Editor editor;

    public Open(Editor editor) {
        this.editor = editor;
    }

    @Override
    public void perform() {
        editor.open();
    }
}