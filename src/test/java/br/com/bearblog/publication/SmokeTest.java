package br.com.bearblog.publication;

import br.com.bearblog.publication.controller.NewsController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SmokeTest {
    @Autowired
    private NewsController newsController;

    @Test
    public void newsContextLoads() throws Exception{
        assertThat(newsController).isNotNull();
    }
}
