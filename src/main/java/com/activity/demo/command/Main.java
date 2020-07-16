package com.activity.demo.command;

/**
 * @program: eoms-workflow
 * @description: 客户端  ps：命令模式
 * @author: HeMingXin
 * @create: 2020/7/16 19:12
 **/
public class Main {
    public static void main(String[] args) {
        Editor editor = new Editor();
        Macro macro = new Macro();
        /*macro.record(new Open(editor));
        macro.record(new Save(editor));
        macro.record(() -> editor.open());
        macro.record(() -> editor.save());*/
        macro.record(editor::open);
        macro.record(editor::save);
        macro.run();
    }
}
