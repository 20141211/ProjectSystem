package service;

import java.util.List;

import po.Position;

import dao.PositionDao;

public class PositionService {
	PositionDao dao=new PositionDao();
	//get all
	public List<Position> get(){
		return dao.select();
	}
}
