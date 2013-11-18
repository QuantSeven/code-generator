package qya.pousheng.demo.dao.team;

import org.apache.ibatis.annotations.Param;

import qya.pousheng.demo.model.team.Team;

import framework.generic.dao.GenericDao;

public interface TeamDao extends GenericDao<Team, String> {

	/**
	 * 根据主键查询一条记录
	 */
	Team findByPk(@Param("teamId") String teamId);

	/**
	 * 根据主键删除一条记录
	 */
	Integer deleteByPk(@Param("teamId") String teamId);
}
