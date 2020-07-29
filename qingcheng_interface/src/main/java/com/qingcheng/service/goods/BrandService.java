package com.qingcheng.service.goods;

import com.qingcheng.entity.PageResult;
import com.qingcheng.pojo.goods.Brand;

import java.util.List;
import java.util.Map;

public interface BrandService {

    /**
     * 查询所有品牌信息
     * @return
     */
    public List<Brand> findAll();

    /**
     * 分页查询所有品牌信息
     * @param page
     * @param size
     * @return
     */
    public PageResult<Brand> findPage(int page, int size);

    /**
     * 根据条件查询所有品牌信息
     * @param searchMap
     * @return
     */
    public List<Brand> findList(Map<String, Object> searchMap);

    /**
     * 根据条件，分页查询所以品牌信息
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public PageResult<Brand> findPage(Map<String, Object> searchMap, int page, int size);

    /**
     * 根据ID查询品牌信息
     * @param id
     * @return
     */
    public Brand findById(Integer id);

    /**
     * 新增品牌
     * @param brand
     */
    public void add(Brand brand);

    /**
     * 修改品牌
     * @param brand
     */
    public void update(Brand brand);

    /**
     * 根据ID删除品牌
     * @param id
     */
    public void delete(Integer id);
}
