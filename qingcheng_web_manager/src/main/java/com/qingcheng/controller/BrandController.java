package com.qingcheng.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qingcheng.entity.PageResult;
import com.qingcheng.entity.Result;
import com.qingcheng.pojo.goods.Brand;
import com.qingcheng.service.goods.BrandService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Reference
    private BrandService brandService;

    /**
     * 查询所有品牌信息
     * @return
     */
    @RequestMapping("/findAll")
    public List<Brand> findAll() {
        return brandService.findAll();
    }

    /**
     * 分页查询所有品牌信息
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/findPage")
    public PageResult<Brand> findPage(int page, int size) {
        return brandService.findPage(page, size);
    }

    /**
     * 根据条件查询所有品牌信息
     * @param searchMap
     * @return
     */
    @PostMapping("/findList")
    public List<Brand> findList(@RequestBody Map searchMap) {
        return brandService.findList(searchMap);
    }

    /**
     * 根据条件，分页查询所有品牌信息
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    @PostMapping("/findPage")
    public PageResult<Brand> findPage(@RequestBody Map searchMap, int page, int size) {
        return brandService.findPage(searchMap, page, size);
    }

    /**
     * 根据ID查询品牌信息
     * @param id
     * @return
     */
    @GetMapping("/findById")
    public Brand findById(Integer id) {
        return brandService.findById(id);
    }

    /**
     * 新增品牌
     * @param brand
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody Brand brand) {
        int x = 1/0;
        brandService.add(brand);
        return new Result();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Brand brand) {
        brandService.update(brand);
        return new Result();
    }

    @GetMapping("/delete")
    public Result delete(Integer id) {
        brandService.delete(id);
        return new Result();
    }
}
