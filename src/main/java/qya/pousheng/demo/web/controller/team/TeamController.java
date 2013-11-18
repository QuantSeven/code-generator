package qya.pousheng.demo.web.controller.team;


import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qya.pousheng.demo.model.team.Team;
import qya.pousheng.demo.service.team.TeamService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import qya.pousheng.demo.web.controller.base.AbstractController;
import qya.pousheng.demo.web.ui.DataGrid;
import qya.pousheng.demo.web.ui.Json;
import qya.pousheng.demo.web.ui.PageRequest;

import framework.generic.utils.string.StringUtil;
import framework.generic.utils.webutils.ServletUtil;

@Controller
@RequestMapping("team/*")
public class TeamController extends AbstractController<Team, String> {

	private TeamService teamService;

	@Resource
	public void setTeamService(TeamService teamService) {
		this.teamService = teamService;
	}

	/*-------------------------------列表显示页面---------------------------------*/
	@Override
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("team/team_list");
	}

	@Override
	public DataGrid dataGrid(PageRequest pageRequest, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> paramMap = ServletUtil.getParametersMapStartingWith(request, "filter_");
		pageRequest.setParameter(paramMap);
		DataGrid dataGrid = teamService.getDatagrid(pageRequest);
		return dataGrid;
	}

	/*--------------------------------添加操作-----------------------------------*/
	@Override
	public ModelAndView addFrom(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("action", "team/insert");
		return new ModelAndView("team/team_edit");
	}

	@Override
	public Json insert(Team team, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			int insertRecords = teamService.create(team);
			if (insertRecords <= 0) {
				return error(getMessage("msg.error.add"));
			}
			return success(getMessage("msg.success.add"));
		} catch (Exception e) {
			e.printStackTrace();
			return error(getMessage("msg.error.add"));
		}
	}

	/*--------------------------------编辑操作-----------------------------------*/
	@Override
	public ModelAndView editForm(String teamId, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Team team = teamService.get(teamId);
		request.setAttribute("team", team);
		request.setAttribute("action", "team/update");
		return new ModelAndView("team/team_edit");
	}

	@Override
	public Json update(Team team, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			int updatedRecords = teamService.modify(team);
			if (updatedRecords <= 0) {
				return error(getMessage("msg.error.add"));
			}
			return success(getMessage("msg.success.update"));
		} catch (Exception e) {
			e.printStackTrace();
			return error(getMessage("msg.error.update"));
		}
	}

	/*--------------------------------删除操作-----------------------------------*/
	@Override
	public Json deleteAll(String[] teamIds, HttpServletRequest request, HttpServletResponse response) {
		try {
			int deletedRecords = teamService.removeAll(teamIds);
			if (deletedRecords <= 0) {
				return error(getMessage("msg.error.delete"));
			} 
			return success(getMessage("msg.success.delete"));
		} catch (Exception e) {
			e.printStackTrace();
			return error(getMessage("msg.error.delete"));
		}
	}
	/*--------------------------------查看操作-----------------------------------*/
	@Override
	public ModelAndView view(String teamId, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("team", teamService.get(teamId));
		return new ModelAndView("team/team_edit");
	}
	
	/*--------------------------------验证操作-----------------------------------*/
	@Override
	public boolean validatePk(String teamId, HttpServletRequest request, HttpServletResponse response) {
		Team team = teamService.get(teamId);
		if (StringUtil.isNullOrEmpty(team)) {
			return true;
		}
		return false;
	}
}




