package com.itechart.app.dao;

import com.itechart.app.entity.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Maxim on 12/1/2015.
 */
public abstract class AbstractDAO  <K,T extends Entity>{
    protected static final Logger LOGGER = LoggerFactory.getLogger("DAO");

    public abstract ArrayList<T> findAll();
    public abstract T findEntityById(K id);
    public abstract void delete(K id);
    public abstract void create(T entity);
    public abstract void update(T entity);
    protected abstract T readEntityFrom(ResultSet entityResultSet) throws SQLException;

}
