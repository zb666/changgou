package com.changgou.goods.service.impl;

import com.changgou.goods.dao.BrandMapper;
import com.changgou.goods.service.BrandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.goods.pojo.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<Brand> findAll() {
        //通用Mapper查询所有
        return brandMapper.selectAll();
    }

    @Override
    public Brand findById(Integer id) {
        Brand brand = brandMapper.selectByPrimaryKey(id);
        return brand;
    }

    @Override
    public List<Brand> findList(Brand brand) {
        Example example = createExample(brand);
        return brandMapper.selectByExample(example);
    }

    @Override
    public void delete(Integer id) {
        brandMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void add(Brand brand) {
        brandMapper.insertSelective(brand);
    }

    @Override
    public void update(Brand brand) {
        //通用Mapper修改数据，忽略空值
        brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public PageInfo<Brand> findPage(Brand brand, Integer pageNum, Integer size) {
        PageHelper.startPage(pageNum,size);
        //条件搜索
        Example example = createExample(brand);
        //搜索
        List<Brand> brands = brandMapper.selectByExample(example);
        return new PageInfo<Brand>(brands);
    }

    @Override
    public PageInfo<Brand> findPage(Integer pageNum, Integer size) {
        PageHelper.startPage(pageNum,size);
        List<Brand> brandList = brandMapper.selectAll();
        return new PageInfo<Brand>(brandList);
    }

    private Example createExample(Brand brand) {
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        if (brand != null) {
            //id
            if (!StringUtils.isEmpty(brand.getId())) {
                criteria.andEqualTo("id", brand.getId());
            }
            if (!StringUtils.isEmpty(brand.getName())) {
                //1)输入name-根据name查询[模糊查询]   select * from tb_brand wehere name like '%brand.getName%'
                criteria.andLike("name", "%" + brand.getName() + "%");
            }
            if (!StringUtils.isEmpty(brand.getLetter())) {
                //2)输入了letter-根据letter查询       select * from tb_brand where letter= 'brand.getLetter'
                criteria.andEqualTo("letter", brand.getLetter());
            }
        }
        return example;
    }

}
