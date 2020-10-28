package com.padc.demo.core;

import java.util.List;
import java.util.Optional;

public interface IService<E>
{
    void save(E element);
    Optional<E> findById(long id);
    List<E> findAll();
    void deleteByID(long id);
}
