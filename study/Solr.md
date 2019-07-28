# Solr

[什么是Solr,它能为我们解决什么问题,怎么用?](https://blog.csdn.net/luo609630199/article/details/82494708)

## 一.Solr 简介

1. Solr 是什么?
  1.1 就是一个 war 项目

2. 自己的项目如何和 Solr 进行交互?
  2.1 特定的 API 叫做 SolrJ

3. 具备数据持久化功能. 

  3.1 Solr 中会存储需要进行搜索的数据
  3.2 把所有数据都初始化到 Solr 中.

4. Solr 作用(什么时候使用 Solr)
  4.1 大量数据检索时使用 Solr,能提升检索效率.

5. Solr 是基于索引进行查询的.
  5.1 顺序查询:从内容的最开始找到内容为止
  5.2 反向键索引:
  5.2.1 在内容中进行拆分

  ![](pic/solr1.png)

6. 国内实现检索的常用方案. 

   6.1 apache lucene : 实现检索的解决方案(Solr 基于 lucene)
   6.2 Baidu API:
   6.3 Google API



## 二. IK Analyzer 中文拆词器

1. Solr 默认对中文拆词功能支持不好. 

   1.1 解决方案:使用 IK Analyzer 拆词器

2. 配置 IK Analyzer 时本质实际上是给 solr 新建了一个 filedType,只要某个属性(field)是这个类型,这个属性就会使用 IK Analyzer 进行拆词

