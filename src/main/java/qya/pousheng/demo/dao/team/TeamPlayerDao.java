package qya.pousheng.demo.dao.team;

import org.apache.ibatis.annotations.Param;

import qya.pousheng.demo.model.team.TeamPlayer;

import framework.generic.dao.GenericDao;

public interface TeamPlayerDao extends GenericDao<TeamPlayer, String> {

	/**
	 * 根据主键查询一条记录
	 */
	TeamPlayer findByPk(@Param("playerId") String playerId);

	/**
	 * 根据主键删除一条记录
	 */
	Integer deleteByPk(@Param("playerId") String playerId);
}
