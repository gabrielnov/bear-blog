package br.com.bearblog.postItems;

import br.com.bearblog.postItems.controller.NewsController;
import br.com.bearblog.postItems.controller.RegisterController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SmokeTest {

    @Autowired
    private NewsController newsController;

    @Autowired
    private RegisterController registerController;

    @Test
    public void newsContextLoads() throws Exception{
        assertThat(newsController).isNotNull();
    }

    @Test
    public void registerContextLoads() throws Exception{
        assertThat(registerController).isNotNull();
    }
}
