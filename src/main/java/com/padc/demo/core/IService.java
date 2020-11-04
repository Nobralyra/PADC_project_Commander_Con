package com.padc.demo.core;

import javassist.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface IService<E>
{
    void save(E element);
    Optional<E> findById(long id) throws NotFoundException;
    List<E> findAll();
    void deleteByID(long id);
}
