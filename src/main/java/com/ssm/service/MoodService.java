package com.ssm.service;

import com.ssm.entity.Mood;

import java.util.List;

/**
 * (Mood)表服务接口
 *
 * @author makejava
 * @since 2021-08-21 16:31:24
 */
public interface MoodService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Mood queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Mood> queryAllByLimit(int offset, int limit);

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    List<Mood> queryAll();

    /**
     * 新增数据
     *
     * @param mood 实例对象
     * @return 实例对象
     */
    Mood insert(Mood mood);

    /**
     * 修改数据
     *
     * @param mood 实例对象
     * @return 实例对象
     */
    Mood update(Mood mood);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    boolean praiseMood(String userId, String moodId);

    boolean praiseMoodForRedis(String userId, String moodId);

    List<Mood> findAllForRedis();

}
