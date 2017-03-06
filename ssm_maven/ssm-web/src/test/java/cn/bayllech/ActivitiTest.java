package cn.bayllech;

import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

public class ActivitiTest {

    @Test
    public void initData() {
        ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration
                .createStandaloneProcessEngineConfiguration();
        processEngineConfiguration.setJdbcDriver("com.mysql.jdbc.Driver");
        processEngineConfiguration.setJdbcUrl("jdbc:mysql:///db_22_activiti_demo");
        processEngineConfiguration.setJdbcUsername("root");
        processEngineConfiguration.setJdbcPassword("root");

        
    }


}
