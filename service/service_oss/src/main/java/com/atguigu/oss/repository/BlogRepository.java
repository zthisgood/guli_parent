package com.atguigu.oss.repository;

import com.atguigu.oss.entity.BlogModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BlogRepository extends ElasticsearchRepository<BlogModel, String> {
}
