package qya.pousheng.demo.service.team;

import qya.pousheng.demo.model.team.Team;

import qya.pousheng.demo.web.ui.DataGrid;
import qya.pousheng.demo.web.ui.PageRequest;

public interface TeamService {
	
	/**
	 * 获取DataGrid列表数据和总数
	 * @param pageRequest datagrid页面请求数据
	 * @return DataGrid
	 */
	DataGrid getDatagrid(PageRequest pageRequest);
	
	/**
	 * 创建一条数据库记录
	 * @param team
	 * @return 受影响的行数
	 */
	int create(Team team);

	/**
	 * 修改一条数据库记录
	 * @param team
	 * @return 受影响的行数
	 */
	int modify(Team team);

	/**
	 * 根据主键删除一条数据库记录
	 * @return 受影响的行数
	 */
	int remove(String teamId);
	
	/**
	 * 批量删除数据库记录
	 * @return 受影响的行数
	 */
	int removeAll(String... teamIds);
	
	/**
	 * 根据主键查询一条数据库记录
	 * @return 实体类
	 */
	Team getByPk(String teamId);
	
	/**
	 * 查询一条数据库记录
	 * @return 实体类
	 */
	Team get(String teamId);
	
}
