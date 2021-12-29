package com.ssm.dao;

import com.ssm.entity.Mood;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Mood)表数据库访问层
 *
 * @author makejava
 * @since 2021-08-21 16:22:49
 */
public interface MoodDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Mood queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Mood> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param mood 实例对象
     * @return 对象列表
     */
    List<Mood> queryAll();

    /**
     * 新增数据
     *
     * @param mood 实例对象
     * @return 影响行数
     */
    int insert(Mood mood);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Mood> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Mood> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Mood> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Mood> entities);

    /**
     * 修改数据
     *
     * @param mood 实例对象
     * @return 影响行数
     */
    int update(Mood mood);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}

