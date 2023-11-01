package com.smallworld.Repository.Interfaces;
import java.util.List;
public interface IBaseRepository<T> {

    List<T> getAll();

}
