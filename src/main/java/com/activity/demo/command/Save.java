package com.activity.demo.command;

/**
 * @Description 具体命令者
 * @Author  HeMingXin
 * @Date  2020/7/16 19:39
 */
public class Save implements Action {
    private final Editor editor;

    public Save(Editor editor) {
        this.editor = editor;
    }

    @Override
    public void perform() {
        editor.save();
    }
}