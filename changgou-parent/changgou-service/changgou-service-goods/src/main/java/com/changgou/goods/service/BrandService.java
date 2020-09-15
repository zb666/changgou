package com.changgou.goods.service;

import com.github.pagehelper.PageInfo;
import com.goods.pojo.Brand;
import org.omg.CORBA.INTERNAL;

import java.util.List;

public interface BrandService {

    List<Brand> findAll();

    Brand findById(Integer id);

    List<Brand> findList(Brand brand);

    void delete(Integer id);

    void add(Brand brand);

    void update(Brand brand);

    //分页+条件搜索
    PageInfo<Brand> findPage(Brand brand,Integer pageNum,Integer size);

    //分页查询
    PageInfo<Brand> findPage(Integer pageNum,Integer size);

}
