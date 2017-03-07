package com.kaishengit;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

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
                .name("helloword")
                .addClasspathResource("diagrams/HelloWord.bpmn")
                .addClasspathResource("diagrams/HelloWord.png")
                .deploy();
        System.out.println("name: "+ deployment.getName());
        System.out.println("id: "+ deployment.getId());
    }


    //启动流程
    @Test
    public void startProcessInstance() {
        ProcessInstance pi = engine.getRuntimeService()
                .startProcessInstanceByKey("myProcess_1");
        System.out.println("流程定义ID: " + pi.getId());
        System.out.println("流程定义名：" + pi.getName());

    }
}
