package com.changgou.goods.controller;

import com.changgou.goods.service.BrandService;
import com.github.pagehelper.PageInfo;
import com.goods.pojo.Brand;
import entity.Result;
import entity.StatusCode;
import jdk.net.SocketFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/brand")
@CrossOrigin
public class BrandController {

    @Autowired
    private BrandService brandService;

    //查询所有
    @GetMapping
    public Result<List<Brand>> findAll() {
        List<Brand> brandList = brandService.findAll();
        return new Result<List<Brand>>(true, StatusCode.OK, "查询品牌成功", brandList);
    }

    @GetMapping(value = "/{id}")
    public Result<Brand> findById(@PathVariable(value = "id") Integer id) {
        Brand brand = brandService.findById(id);
        return new Result<Brand>(true, StatusCode.OK, "根据ID查询成功", brand);
    }

    @PostMapping(value = "/search")
    public Result<List<Brand>> findList(@RequestBody(required = false) Brand brand) {
        List<Brand> list = brandService.findList(brand);
        return new Result<List<Brand>>(true, StatusCode.OK, "条件查询品牌集合成功", list);
    }

    @DeleteMapping(value = "/{id}")
    public Result deleteById(@PathVariable(value = "id") Integer id){
        brandService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    @PostMapping
    public Result add(@RequestBody Brand brand){
        brandService.add(brand);
        return new Result(true,StatusCode.OK,"增加成功");
    }

    @PutMapping(value = "/{id}")
    public Result update(@RequestBody Brand brand,@PathVariable Integer id){
        //设置ID
        brand.setId(id);
        //修改数据
        brandService.update(brand);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    @PostMapping(value = "/search/{pageNum}/{size}")
    public Result<PageInfo<Brand>> findPage(@PathVariable(value = "pageNum")Integer pageNum,
                                            @PathVariable(value = "size")Integer size,
                                            @RequestBody(required = false)Brand brand){
        int q=10/0;
        //调用Service实现分页查询
        PageInfo<Brand> pageInfo = brandService.findPage(brand,pageNum, size);
        return new Result<PageInfo<Brand>>(true, StatusCode.OK,"条件查询品牌集合成功！",pageInfo);
    }

    /***
     * 分页查询
     * url: /brand/search/pageNum/size
     */
    @GetMapping(value = "/search/{pageNum}/{size}")
    public Result<PageInfo<Brand>> findPage(@PathVariable(value = "pageNum")Integer pageNum,
                                            @PathVariable(value = "size")Integer size){
        //调用Service实现分页查询
        PageInfo<Brand> pageInfo = brandService.findPage(pageNum, size);
        return new Result<PageInfo<Brand>>(true, StatusCode.OK,"条件查询品牌集合成功！",pageInfo);
    }


}
