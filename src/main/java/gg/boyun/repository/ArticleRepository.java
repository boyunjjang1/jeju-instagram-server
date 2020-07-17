package gg.boyun.repository;


import gg.boyun.entity.ArticleEntity;
import gg.boyun.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Integer> {

    ArticleEntity findFirstByIdx(@RequestParam("idx") Integer idx);
    List<ArticleEntity> findAllByTitleContaining(@RequestParam("title")String title);

}
