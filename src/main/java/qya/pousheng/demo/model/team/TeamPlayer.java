package qya.pousheng.demo.model.team;

import framework.generic.annotation.Column;
import framework.generic.annotation.Table;
import framework.generic.model.PersistentModel;


@Table(name = "t_team_player")
public class TeamPlayer implements PersistentModel {
	
	@Column(name = "PLAYER_ID", pk = true, order = 0)
	private String playerId;
	@Column(name = "PLAYER_NO")
	private String playerNo;
	@Column(name = "PLAYER_NAME")
	private String playerName;
	@Column(name = "TEAM_ID")
	private String teamId;
	
	public String getPlayerId() {
		return this.playerId;
	}
	
	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}
	
	public String getPlayerNo() {
		return this.playerNo;
	}
	
	public void setPlayerNo(String playerNo) {
		this.playerNo = playerNo;
	}
	
	public String getPlayerName() {
		return this.playerName;
	}
	
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public String getTeamId() {
		return this.teamId;
	}
	
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	
}
