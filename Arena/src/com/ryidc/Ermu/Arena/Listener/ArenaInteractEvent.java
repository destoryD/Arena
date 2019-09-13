package com.ryidc.Ermu.Arena.Listener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import com.ryidc.Ermu.Arena.Arena;

/**
 * 点击事件的监听 保存点
 * @author Er_mu
 *
 */
public class ArenaInteractEvent implements Listener{
	private Plugin plug;
	public ArenaInteractEvent(Plugin plug) {
		this.plug = plug;
	}
	@EventHandler
	public void playerClick(PlayerInteractEvent e) {
		if(e.getItem()==null) {
			return;
		}
		//查看此玩家是否在游戏内 判断交互时手里是否是末影珍珠 是的话取消掉事件
		if(Arena.playerList.get(e.getPlayer().getName()).getArenaId()!=null) {
			//只有没有权限的人会受到限制
			if(!e.getPlayer().hasPermission("*")) {
				//判断物品
				if(e.getItem().getType().equals(Material.ENDER_PEARL)) {
					//发送消息
					e.getPlayer().sendMessage("§c禁止使用末影珍珠");
					//取消事件
					e.setCancelled(true);
				}
			}	
		}
		//判断交互方式
		if(e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
			//左击方块手里物品为空或左击空气
			if(e.getItem()==null||e.getClickedBlock()==null) {
				return;
			}
			//保存点
			if(this.saveLocation(e.getPlayer(), e.getItem(), e.getClickedBlock().getLocation(), "left")) {
				//保存点成功取消时间
				e.setCancelled(true);
			}
			
		}
		else if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			//右击方块手里物品为空或左击空气
			if(e.getItem()==null||e.getClickedBlock()==null) {
				return;
			}
			//保存点
			if(this.saveLocation(e.getPlayer(), e.getItem(), e.getClickedBlock().getLocation(), "right")) {
				e.setCancelled(true);
			}
		}
	}
	/**
	 * 判断并保存点
	 * @return 选点状态
	 */
	private boolean saveLocation(Player p,ItemStack item,Location loc,String mode) {
		//判断当前选中状态
		if(Arena.playerList.get(p.getName()).getBool()) {
			//判断物品
			if(item.getTypeId()==this.plug.getConfig().getInt("selectId")) {
				//等于配置文件中物品
				//选点
				if(mode.equals("left")) {
					Arena.playerList.get(p.getName()).setLoc1(loc);
					p.sendMessage("§a你选中了X: "+loc.getX()+" Y: "+loc.getY()+" Z: "+loc.getZ());
					return true;
				}else {
					Arena.playerList.get(p.getName()).setLoc2(loc);
					p.sendMessage("§a你选中了X: "+loc.getX()+" Y: "+loc.getY()+" Z: "+loc.getZ());
					return true;
				}
			}
			else{
				p.sendMessage("§c请使用指定物品进行选点,如需退出创建请输入/Arena cancel");
			}
		}
		return false;
		
	}
	
}
