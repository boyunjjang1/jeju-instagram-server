package gg.boyun;

import gg.boyun.entity.ArticleEntity;
import gg.boyun.repository.ArticleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DataInjecition {
    @Autowired
    ArticleRepository articleRepository;

    @Test
    public void insertData(){
        List<ArticleEntity> articleEntityList = new ArrayList<>();
        for(int i=0; i<2000; i++){
            String title = "제목입니다."+i;
            String content = "content"+i;
            ArticleEntity articleEntity = new ArticleEntity();
            articleEntity.setTitle(title);
            articleEntity.setContent(content);
            articleEntityList.add(articleEntity);
        }
        articleRepository.saveAll(articleEntityList);
    }
}
