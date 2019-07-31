package com.yyj.service;
/**
 * @author Yanjiang
 * @create 2019-07-24 17:22
 */

import com.yyj.entity.Position;

import java.util.List;

/**
 *@ClassName positionService
 *@Description TODO
 *@Author Yanjiang
 *@Date 2019/7/24 17:22
 *@Version 1.0
 **/
public interface IPositionService {
    List<Position> selectAll();
    boolean add(Position position);
    boolean delete(int id);
    boolean update(Position position);
    Position selectOne(int id);
}

