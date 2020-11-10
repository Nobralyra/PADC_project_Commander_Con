package com.padc.demo.core;

import javassist.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface IService<E>
{
    void save(E element);
    E findById(long id);
    List<E> findAll();
    void deleteByID(long id);
}
