package qya.pousheng.demo.service.team;

import javax.annotation.Resource;

import qya.pousheng.demo.dao.team.TeamDao;
import qya.pousheng.demo.model.team.Team;
import qya.pousheng.demo.service.team.TeamService;
import org.springframework.stereotype.Service;

import qya.pousheng.demo.web.ui.DataGrid;
import qya.pousheng.demo.web.ui.PageRequest;

import framework.generic.paginator.domain.PageList;

@Service("teamService")
public class TeamServiceImpl implements TeamService {

	private TeamDao teamDao;
	
	@Resource
	public void setTeamDao(TeamDao teamDao) {
		this.teamDao = teamDao;
	}
	
	/*
	 * (non-Javadoc)
	 * @see qya.pousheng.demo.service.team.TeamService#getDatagrid(qya.pousheng.demo.web.ui.PageRequest)
	 */
	@Override
	public DataGrid getDatagrid(PageRequest pageRequest) {
		PageList<Team> teams = teamDao.findByPage(pageRequest.getParameter(), pageRequest.getPageBounds());
		return new DataGrid(teams.getPaginator().getTotalCount(), teams);
	}
	
	/*
	 * (non-Javadoc)
	 * @see qya.pousheng.demo.service.team.TeamService#create(qya.pousheng.demo.model.team.Team)
	 */
	@Override
	public int create(Team team) {
		return teamDao.insert(team);
	}
	
	/*
	 * (non-Javadoc)
	 * @see qya.pousheng.demo.service.team.TeamService#modify(qya.pousheng.demo.model.team.Team)
	 */
	@Override
	public int modify(Team team) {
		return teamDao.update(team);
	}
	
	/*
	 * (non-Javadoc)
	 * @see qya.pousheng.demo.service.team.TeamService#remove(String)
	 */
	@Override
	public int remove(String teamId) {
		return teamDao.deleteByPk(teamId);
	}
	
	/*
	 * (non-Javadoc)
	 * @see qya.pousheng.demo.service.team.TeamService#removeAll(String[])
	 */
	@Override
	public int removeAll(String... teamIds) {
		return teamDao.delete(teamIds);
	}

	/*
	 * (non-Javadoc)
	 * @see qya.pousheng.demo.service.team.TeamService#getByPk(String)
	 */
	@Override
	public Team getByPk(String teamId) {
		return teamDao.findByPk(teamId);
	}
	
	/*
	 * (non-Javadoc)
	 * @see qya.pousheng.demo.service.team.TeamService#get(String)
	 */
	@Override
	public Team get(String teamId) {
		return teamDao.find(teamId);
	}
}
