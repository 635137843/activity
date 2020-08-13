package com.activity.demo.test;

import com.activity.demo.config.Holiday;
import com.activity.demo.config.SecurityUtil;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Demo
 * @Description TODO
 * @Author xin
 * @Date 2020/7/14 22:16
 * @Version 1.0
 **/
/**
 * SpringBoot与Junit整合，测试流程定义的相关操作
 *  任务完成
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo {

    @Autowired
    private SecurityUtil securityUtil;//SpringSecurity相关的工具类

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private HistoryService historyService;
    //流程定义信息的查看
    @Test
    public void testDefinition(){
        securityUtil.logInAs("salaboy");//SpringSecurity认证
        /*//分页查询出流程定义信息
        Page<ProcessDefinition> processDefinitionPage = processRuntime
                .processDefinitions(Pageable.of(0, 10));
        System.out.println("可用的流程定义数量：" + processDefinitionPage.getTotalItems());//查看已部署的流程个数
        for (ProcessDefinition pd : processDefinitionPage.getContent()) {
            System.out.println("流程定义：" + pd);
        }*/
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        List<ProcessDefinition> list = processDefinitionQuery
                .orderByProcessDefinitionVersion().desc().list();
        //repositoryService.deleteDeployment("2");
        for (int i=0;i<list.size();i++){
            ProcessDefinition processDefinition = list.get(i);
            System.out.println("流程定义id：" + processDefinition.getId());
            System.out.println("流程定义名称：" + processDefinition.getName());
            System.out.println("流程定义key：" + processDefinition.getKey());
            System.out.println("流程定义版本：" + processDefinition.getVersion());
            System.out.println("流程部署id：" + processDefinition.getDeploymentId());
        }
    }

    /**
     * 启动流程实例
     */
    @Test
    public void testStartProcess() {
        securityUtil.logInAs("salaboy");

        String processDefinitionKey = "demo2";
        Holiday holiday = new Holiday();
        holiday.setApplyName("test1");
        holiday.setManagerName("test2");
        Map<String, Object> map = new HashMap<>();

        //使用UEL 表达式设置

        map.put ("holiday", holiday);


        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, map);
        System.out.println ("流程实例ID:" + processInstance.getId ());
        System.out.println ("流程定义ID:" + processInstance.getProcessDefinitionId ());
    }

    /**
     * 查询任务，并完成自己的任务
     */
    @Test
    public void testTask() {
        List<Task> test01 = taskService.createTaskQuery()
                //流程实例key
                .processDefinitionKey("demo")
                //查询谁的任务
                //.taskAssignee("salaboy")
                .list();
        List<String> idList = new ArrayList<String>();

        Map<String, Object> map = new HashMap<>();

        //使用UEL 表达式设置

        map.put ("test2", "ryandawsonuk");

        for (Task task : test01) {
            idList.add (task.getId ());
            //完成任务
            //taskService.complete(task.getId(), map);
            //分派
            //taskService.delegateTask(task.getId(), "other");
            //taskService.resolveTask(task.getId());
            //移交
            //taskService.setAssignee(task.getId(),"other");

            System.out.println ("任务ID:" + task.getId ());
            System.out.println ("任务名称:" + task.getName ());
            System.out.println ("任务的创建时间:" + task.getCreateTime ());
            System.out.println ("任务的办理人:" + task.getAssignee ());
            System.out.println ("流程实例ID：" + task.getProcessInstanceId ());
            System.out.println ("执行对象ID:" + task.getExecutionId ());
            System.out.println ("流程定义ID:" + task.getProcessDefinitionId ());
        }
    }

    @Test
    public void queryHistoryData(){
        List<HistoricActivityInstance> demo = historyService.createHistoricActivityInstanceQuery()
                .processInstanceId("5b179294-c67b-11ea-bddc-00ff0a0d866d")
                .orderByHistoricActivityInstanceStartTime()
                .asc()
                .list();
        demo.stream().forEach(System.out::println);
    }
}
