package me.tWizT3d_dreaMr.FallingCrate;

import org.bukkit.entity.Player;

public enum Permission
{
  canSetCrate("tCrate.create");
  
  String permission;
  
  private Permission(String permission)
  {
    this.permission = permission;
  }
  
  public String toString()
  {
    return this.permission;
  }
  
  public boolean has(Player player)
  {
    return player.hasPermission(this.permission);
  }
}
