package com.yyj.service.Impl;
/**
 * @author Yanjiang
 * @create 2019-07-24 17:23
 */

import com.yyj.dao.PositionDao;
import com.yyj.entity.Position;
import com.yyj.service.IPositionService;

import java.util.List;

/**
 *@ClassName positionServiceImpl
 *@Description TODO
 *@Author Yanjiang
 *@Date 2019/7/24 17:23
 *@Version 1.0
 **/
public class PositionServiceImpl implements IPositionService {
    private PositionDao positionDao=new PositionDao();

    public List<Position> selectAll() {
        String sql="select positionID,positionName from position";
        return positionDao.query(sql, Position.class, null);
    }

    @Override
    public boolean add(Position position) {
        String sql="INSERT INTO `kqms`.`position` VALUES (null,?)";
        return positionDao.update(sql, new Object[]{position.getPositionName()});
    }

    @Override
    public boolean update(Position position){
        String sql="update position set positionName=? where positionId=?";
        return positionDao.update(sql,new Object[]{position.getPositionName(),position.getPositionID()});
    }

    @Override
    public Position selectOne(int id) {
        String sql="select * from position where positionID=?";
        return (Position) positionDao.get(sql,Position.class,new Object[]{id});
    }
    @Override
    public boolean delete(int id) {
        String sql="delete from position where positionId=?";
        return positionDao.update(sql,new Object[]{id});
    }

}
