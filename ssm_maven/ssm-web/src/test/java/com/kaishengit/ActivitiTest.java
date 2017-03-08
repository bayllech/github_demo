package com.kaishengit;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

public class ActivitiTest {

    ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

    //初始化数据库
    @Test
    public void initData() {
        ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration
                .createStandaloneProcessEngineConfiguration();
        processEngineConfiguration.setJdbcDriver("com.mysql.jdbc.Driver");
        processEngineConfiguration.setJdbcUrl("jdbc:mysql:///db_22_activiti_demo");
        processEngineConfiguration.setJdbcUsername("root");
        processEngineConfiguration.setJdbcPassword("root");

        processEngineConfiguration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
        System.out.println("processEngine: " + processEngine);
    }

    //部署流程
    @Test
    public void deploy() {
        Deployment deployment = engine.getRepositoryService()
                .createDeployment()
                .name("并行流程")
                .addClasspathResource("diagrams/Hello.bpmn")
                .deploy();
        System.out.println("name: "+ deployment.getName());
        System.out.println("id: "+ deployment.getId());
    }

    //zip格式部署流程
    @Test
    public void zipDeploy() {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("diagrams/Hello.bpmn");
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        Deployment deployment = engine.getRepositoryService()
                .createDeployment()
                .name("hello")
                .addZipInputStream(zipInputStream)
                .deploy();
    }

    //启动流程
    @Test
    public void startProcessInstance() {
        ProcessInstance pi = engine.getRuntimeService()
                .startProcessInstanceByKey("myProcess_1");
        System.out.println("流程定义ID: " + pi.getId());
        System.out.println("流程定义名：" + pi.getName());

    }

    //删除流程
    @Test
    public void deleteProcess() {
        engine.getRepositoryService().deleteDeployment("15001", true);
    }

    //查看任务列表
    @Test
    public void getTaskList() {
        String assignee = "part";
        List<Task> taskList = engine.getTaskService()
                .createTaskQuery()
                .taskAssignee(assignee)
                .orderByTaskAssignee()
                .desc()
                .list();
        System.out.println("list size: " + taskList.size());
        for (Task task : taskList) {
            System.out.println("办理人：" + task.getAssignee());
            System.out.println("name: " + task.getName());
            System.out.println("流程定义ID: " + task.getProcessDefinitionId());

        }
    }


}
