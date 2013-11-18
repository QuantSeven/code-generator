package qya.pousheng.demo.dao.team;

import org.apache.ibatis.annotations.Param;

import qya.pousheng.demo.model.team.TeamCoach;

import framework.generic.dao.GenericDao;

public interface TeamCoachDao extends GenericDao<TeamCoach, String> {

	/**
	 * 根据主键查询一条记录
	 */
	TeamCoach findByPk(@Param("coachId") String coachId);

	/**
	 * 根据主键删除一条记录
	 */
	Integer deleteByPk(@Param("coachId") String coachId);
}
