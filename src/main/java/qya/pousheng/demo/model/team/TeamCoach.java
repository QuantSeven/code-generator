package qya.pousheng.demo.model.team;

import framework.generic.annotation.Column;
import framework.generic.annotation.Table;
import framework.generic.model.PersistentModel;


@Table(name = "t_team_coach")
public class TeamCoach implements PersistentModel {
	
	@Column(name = "COACH_ID", pk = true, order = 0)
	private String coachId;
	@Column(name = "COACH_NAME")
	private String coachName;
	@Column(name = "COACH_TITLE")
	private String coachTitle;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "TEAM_ID")
	private String teamId;
	
	public String getCoachId() {
		return this.coachId;
	}
	
	public void setCoachId(String coachId) {
		this.coachId = coachId;
	}
	
	public String getCoachName() {
		return this.coachName;
	}
	
	public void setCoachName(String coachName) {
		this.coachName = coachName;
	}
	
	public String getCoachTitle() {
		return this.coachTitle;
	}
	
	public void setCoachTitle(String coachTitle) {
		this.coachTitle = coachTitle;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getTeamId() {
		return this.teamId;
	}
	
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	
}
