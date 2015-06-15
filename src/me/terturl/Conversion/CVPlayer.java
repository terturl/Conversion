package me.terturl.Conversion;

import me.terturl.Conversion.GameLogic.Cache;

public class CVPlayer {

	private String name;
	private int kills;
	private int deaths;
	private int powerupsUsed;
	private int conversions;
	private int converted;

	public CVPlayer(String name) {
		this.name = name;
		this.kills = 0;
		this.deaths = 0;
		this.powerupsUsed = 0;
		this.conversions = 0;
		this.converted = 0;
		
	}
	
	public int addKill() {
		this.kills = this.kills+1;
		return this.kills;
	}
	
	public int addConversion() {
		this.conversions = this.conversions+1;
		return this.conversions;
	}
	
	public int addConverts() {
		this.converted = this.converted+1;
		return this.converted;
	}
	
	public int addDeath() {
		this.deaths = this.deaths+1;
		return this.deaths;
	}
	
	public int addPowerup() {
		this.powerupsUsed = this.powerupsUsed+1;
		return this.powerupsUsed;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getKills() {
		return this.kills;
	}
	
	public int getDeaths() {
		return this.deaths;
	}
	
	public int getConversions() {
		return this.conversions;
	}
	
	public int getConverts() {
		return this.converted;
	}
	
	public int getPowerupsUsed() {
		return this.powerupsUsed;
	}
	
	public void save() {
		Cache.RSPlayers.put(this.name, this);
	}
}
 
