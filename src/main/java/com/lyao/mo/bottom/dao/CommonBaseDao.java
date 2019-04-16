package com.lyao.mo.bottom.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommonBaseDao extends SqlSessionDaoSupport{
    @Autowired
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
        super.setSqlSessionFactory(sqlSessionFactory);
    }
    
    @SuppressWarnings("unchecked")
	public <E,T> T selectOne(String statement, E parameter){
        return (T)getSqlSession().selectOne(statement, parameter);
    }
    
    @SuppressWarnings("unchecked")
	public <E,T> List<T> selectList(String statement, E parameter){
        return (List<T>)getSqlSession().selectList(statement, parameter);
    }
    
    @SuppressWarnings("unchecked")
	public <E> E selectOne(String statement){
        return (E)getSqlSession().selectOne(statement);
    }
    
    public <E> List<?> selectList(String statement){
        return (List<?>) getSqlSession().selectList(statement);
    }
    
    public <E> int insert(String statement, E parameter){
        return getSqlSession().insert(statement, parameter);
    }
    
    public <E> int update(String statement, E parameter){
        return getSqlSession().update(statement, parameter);
    }
    
    public int update(String statement){
        return getSqlSession().update(statement);
    }
    
    public <E> int delete(String statement, E parameter){
        return getSqlSession().delete(statement, parameter);
    }
}
