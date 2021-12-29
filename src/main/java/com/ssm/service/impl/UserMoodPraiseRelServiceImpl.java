package com.ssm.service.impl;

import com.ssm.entity.UserMoodPraiseRel;
import com.ssm.dao.UserMoodPraiseRelDao;
import com.ssm.service.UserMoodPraiseRelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (UserMoodPraiseRel)表服务实现类
 *
 * @author makejava
 * @since 2021-08-21 16:31:27
 */
@Service("userMoodPraiseRelService")
public class UserMoodPraiseRelServiceImpl implements UserMoodPraiseRelService {
    @Resource
    private UserMoodPraiseRelDao userMoodPraiseRelDao;

    public void setUserMoodPraiseRelDao(UserMoodPraiseRelDao userMoodPraiseRelDao) {
        this.userMoodPraiseRelDao = userMoodPraiseRelDao;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UserMoodPraiseRel queryById(Long id) {
        return this.userMoodPraiseRelDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<UserMoodPraiseRel> queryAllByLimit(int offset, int limit) {
        return this.userMoodPraiseRelDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param userMoodPraiseRel 实例对象
     * @return 实例对象
     */
    @Override
    public boolean save(UserMoodPraiseRel userMoodPraiseRel) {
        return this.userMoodPraiseRelDao.save(userMoodPraiseRel);
    }

    /**
     * 修改数据
     *
     * @param userMoodPraiseRel 实例对象
     * @return 实例对象
     */
    @Override
    public UserMoodPraiseRel update(UserMoodPraiseRel userMoodPraiseRel) {
        this.userMoodPraiseRelDao.update(userMoodPraiseRel);
        return this.queryById(userMoodPraiseRel.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.userMoodPraiseRelDao.deleteById(id) > 0;
    }
}
