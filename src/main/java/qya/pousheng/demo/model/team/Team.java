package qya.pousheng.demo.model.team;

import framework.generic.annotation.Column;
import framework.generic.annotation.Table;
import framework.generic.model.PersistentModel;

import qya.pousheng.demo.model.team.TeamPlayer;
import qya.pousheng.demo.model.team.TeamCoach;

/**
 * 团队表
 */
@Table(name = "t_team")
public class Team implements PersistentModel {
	
	@Column(name = "TEAM_ID", pk = true, order = 0)
	private String teamId;
	@Column(name = "TEAM_NAME")
	private String teamName;
	@Column(name = "CREATE_DATE")
	private java.util.Date createDate;
	
	public String getTeamId() {
		return this.teamId;
	}
	
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	
	public String getTeamName() {
		return this.teamName;
	}
	
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	
}
