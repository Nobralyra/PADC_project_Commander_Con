package com.padc.demo.core;

import java.util.List;

public interface IService<E>
{
    void save(E element);
    E findById(long id);
    List<E> findAll();
    void deleteByID(long id);
}
