package com.ryidc.Ermu.Arena.Command.SubCommand;

import org.bukkit.entity.Player;

import com.ryidc.Ermu.Arena.Arena;
import com.ryidc.Ermu.Arena.ArenaInfo;
import com.ryidc.Ermu.save.ArSave;

/**
 * 设置等待大厅
 * @author Er_mu
 *
 */
public class SetWaitLocationSubCommand implements ArSubCommand{
	@Override
	public void command(Player p, String[] args) {
		//arena setlocation id
		// TODO Auto-generated method stub
		//判断长度
		if(args.length<2) {
			p.sendMessage("§c命令格式错误 正确格式:/arena setlocation 竞技场id");
			return;
		}
		//判断竞技场是否存在
		if(!Arena.arenaList.containsKey(args[1])) {
			p.sendMessage("§c该竞技场不存在");
			return;
		}
		ArenaInfo arena = Arena.arenaList.get(args[1]);
		//设置
		arena.setWaitLocation(p.getLocation());
		p.sendMessage("§a等待大厅设置成功!");
		//序列化存储
		//使用序列化对其进行一次保存
		ArSave.save(arena);
	}
	
}
