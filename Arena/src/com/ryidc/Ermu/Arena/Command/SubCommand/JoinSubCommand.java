package com.ryidc.Ermu.Arena.Command.SubCommand;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import com.ryidc.Ermu.Arena.Arena;
import com.ryidc.Ermu.game.GameStartListener;

/**
 * 加入竞技场指令
 * @author Er_mu
 *
 */
public class JoinSubCommand implements ArSubCommand{

	@Override
	public void command(Player p, String[] args) {
		//arena join arena
		//判断是否有竞技场参数及是否包含对应竞技场
		if(args.length<2) {
			p.sendMessage("§c命令输入错误 正确格式 /arena join 竞技场");
			return;
		}
		if(!Arena.arenaList.containsKey(args[1])) {
			//不包含此竞技场id
			p.sendMessage("§c此竞技场不存在!");
			return;
		}
		//判断模式是否为团队
		if(Arena.arenaList.get(args[1]).getMode().equals("team")) {
			//判断出生点是否设置
			if(Arena.arenaList.get(args[1]).getFirstLocation()==null||Arena.arenaList.get(args[1]).getSecondLocation()==null) {
				p.sendMessage("§c此竞技场设置未完成,请完善");
				return;
			}
		}else if(Arena.arenaList.get(args[1]).getMode().equals("single")) {
			if(Arena.arenaList.get(args[1]).getMinPerson()<4) {
				p.sendMessage("§c个人竞技最低4人,请完善");
				return;
			}
		}
		//判断属性是否完成 最大人数 最小人数 等待时间 最大挑战次数 坐标 以及整场游戏时间
		if(Arena.arenaList.get(args[1]).getMinPerson()<=0||Arena.arenaList.get(args[1]).getTime()<=0||Arena.arenaList.get(args[1]).getMaxPerson()<=0||Arena.arenaList.get(args[1]).getMaxNumber()<=0||Arena.arenaList.get(args[1]).getWaitLocation()==null||Arena.arenaList.get(args[1]).getGameTime()<=0) {
			p.sendMessage("§c此竞技场设置未完成,请完善");
			return;
		}
		//判断此玩家是否已经加入了竞技场
		if(Arena.playerList.get(p.getName()).getArenaId()!=null) {
			p.sendMessage("§c您已经加入其他竞技场,请先退出");
			return;
		}
		//判断此游戏是否已经开始
		if(Arena.arenaList.get(args[1]).getStartBool()) {
			p.sendMessage("§a此竞技场已经开始~");
			return;
		}
		//判断最大人数
		if(Arena.arenaList.get(args[1]).getPlayerList().size()>=Arena.arenaList.get(args[1]).getMaxNumber()) {
			p.sendMessage("§c此竞技场已满~");
			return;
		}
		//判断竞技场挑战次数是否有此玩家
		if(Arena.arenaList.get(args[1]).getNumber().containsKey(p.getName())) {
			//判断挑战次数
			if(Arena.arenaList.get(args[1]).getNumber().get(p.getName())>=Arena.arenaList.get(args[1]).getMaxNumber()) {
				p.sendMessage("§c您今日挑战次数已经用完了~");
				return;
			}
		}
		//取当前时间
		Date d = new Date();
		//转为日历类 方便计算
		Calendar c = new GregorianCalendar();
		c.setTime(d);
		//以上条件都符合 判断日期
		if(Arena.arenaList.get(args[1]).getWeek()!=0) {	
			//判断周数
			if(c.get(Calendar.DAY_OF_WEEK)!=Arena.arenaList.get(args[1]).getWeek()) {
				p.sendMessage("§a未到开放时间哦~");
				return;
			}	
		}
		if(Arena.arenaList.get(args[1]).getHour()!=0) {
			//判断小时数 24小时
			if(c.get(Calendar.HOUR_OF_DAY)!=Arena.arenaList.get(args[1]).getHour()) {
				p.sendMessage("§a未到开放时间哦~");
				return;
			}
		}
		//判断是否需要进入物品
		if(Arena.arenaList.get(args[1]).getJoinItem()!=null) {
			//玩家物品栏
			PlayerInventory pinv = p.getInventory();
			//进入物品
			ItemStack joinItem = Arena.arenaList.get(args[1]).getJoinItem();
			//检查是否有此物品
			if(!pinv.contains(joinItem.getType())) {
				p.sendMessage("§e你身上没有进入物品!");
				return;
			}else {
				//查找id对应的第一个物品
				int playerItemIndex = pinv.first(joinItem.getType());
				ItemStack playerItem = pinv.getItem(playerItemIndex);
				//取ItemMeta判断展示名和lore
				ItemMeta joinMeta = joinItem.getItemMeta();
				ItemMeta playerMeta = playerItem.getItemMeta();
				List<String> joinLore = joinMeta.getLore();
				String joinDisName = joinMeta.getDisplayName();
				//先判断名字
				if(joinDisName!=null) {
					String playerDisName = playerMeta.getDisplayName();
					//原物品有展示名 需要判断
					if(playerDisName==null||!playerDisName.equals(joinDisName)) {
						//这个物品没有这个名 或展示名不同
						p.sendMessage("§e你身上没有进入物品!");
						return;
					}
				}
				//再判断lore
				if(joinLore!=null) {
					//取现有物品lore
					List<String> playerLore = playerMeta.getLore();
					if(playerLore==null||playerLore.size()!=joinLore.size()) {
						//物品无lore 或长度不同 直接关闭
						p.sendMessage("§e你身上没有进入物品!");
						return;
					}
					//判断内容
					for(int i=0;i<joinLore.size();i++) {
						//物品相同时 lore应该是一一对应的 有一个不同则不是一个物品
						if(!joinLore.get(i).equals(playerLore.get(i))) {
							p.sendMessage("§e你身上没有进入物品!");
							return;
						}
					}
				}
				//判断个数
				if(playerItem.getAmount()<joinItem.getAmount()) {
					p.sendMessage("§e物品个数不足!");
					return;
				}else if(playerItem.getAmount()==joinItem.getAmount()){
					p.sendMessage("§e你已缴纳入场物品,准许进入!");
					//删除
					pinv.clear(playerItemIndex);
					
				}else if(playerItem.getAmount()>joinItem.getAmount()) {
					p.sendMessage("§e你已缴纳入场物品,准许进入!");
					//设置堆叠
					playerItem.setAmount(playerItem.getAmount()-joinItem.getAmount());
				}
			}
		}
		//加入竞技场
		Arena.arenaList.get(args[1]).getPlayerList().put(p.getName(), p);
		//修改状态
		Arena.playerList.get(p.getName()).setArenaId(args[1]);
		//传送到大厅
		p.teleport(Arena.arenaList.get(args[1]).getWaitLocation());
		//判断人数
		if(Arena.arenaList.get(args[1]).getMinPerson()<=Arena.arenaList.get(args[1]).getPlayerList().size()) {
			GameStartListener.senderMessageToAllPlayer("§a玩家§e"+p.getName()+"§a加入了游戏,正在等待游戏开始", args[1]);
		}else {
			//向竞技场全部玩家发送一条消息
			GameStartListener.senderMessageToAllPlayer("§a玩家§e"+p.getName()+"§a加入了游戏,还差"+(Arena.arenaList.get(args[1]).getMinPerson()-Arena.arenaList.get(args[1]).getPlayerList().size())+"人", args[1]);
		}
		
		
	}

}
