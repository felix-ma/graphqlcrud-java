# 数据服务中台

## 元数据维护

- [x] 表定义加载
- [x] 表关联加载
- [ ] 表定义自定义维护
- [ ] 表关系自定义维护
- [ ] 

## 原子服务Graphql

- [ ] 实体基础CRUD服务[<sup>2</sup>](#refer-2)
    - [x] **QUERIES** findOne filter
    - [x] **QUERIES** findList filter
    - [x] **QUERIES** create
    - [x] **MUTATIONS** update filter
    - [x] **MUTATIONS** delete filter
- [ ] 方法支持带参数(Query Variables)模式
- [ ] 实体聚合计算服务[<sup>3</sup>](#refer-3)
- [ ] 变更订阅服务
- [ ] 

## 数据权限

- [ ] 动态schema权限（列字段权限+方法权限）
- [ ] 查询嵌入条件的业务数据权限（行权限）
- [ ] 

## 扩展服务

- [ ] 自定义表关联join
- [ ] 自定义查询
- [ ] 

## 多数据源

- [ ] 

## 服务编排

> - flink
> - node-red
> - js-engine

# 参考

<div id="refer-1"></div>

- [1] [graphql-java-extended-scalars](https://github.com/graphql-java/graphql-java-extended-scalars)

<div id="refer-2"></div>

- [2] [graphqlcrud-java](https://github.com/graphqlcrud/graphqlcrud-java)

<div id="refer-3"></div>

- [2] [hasura/graphql-engine](https://github.com/hasura/graphql-engine)