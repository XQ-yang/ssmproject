package com.ssm.service;

import com.ssm.entity.UserMoodPraiseRel;

import java.util.List;

/**
 * (UserMoodPraiseRel)表服务接口
 *
 * @author makejava
 * @since 2021-08-21 16:31:27
 */
public interface UserMoodPraiseRelService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserMoodPraiseRel queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<UserMoodPraiseRel> queryAllByLimit(int offset, int limit);


    /**
     * 新增数据
     *
     * @param userMoodPraiseRel 实例对象
     * @return 实例对象
     */
    boolean save(UserMoodPraiseRel userMoodPraiseRel);

    /**
     * 修改数据
     *
     * @param userMoodPraiseRel 实例对象
     * @return 实例对象
     */
    UserMoodPraiseRel update(UserMoodPraiseRel userMoodPraiseRel);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
