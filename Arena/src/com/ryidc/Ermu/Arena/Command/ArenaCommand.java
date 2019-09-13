package com.ryidc.Ermu.Arena.Command;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.ryidc.Ermu.Arena.Command.SubCommand.ArReloadSubCommand;
import com.ryidc.Ermu.Arena.Command.SubCommand.ArSubCommand;
import com.ryidc.Ermu.Arena.Command.SubCommand.CancelSubCommand;
import com.ryidc.Ermu.Arena.Command.SubCommand.CreateSubCommand;
import com.ryidc.Ermu.Arena.Command.SubCommand.HelpSubCommand;
import com.ryidc.Ermu.Arena.Command.SubCommand.JoinSubCommand;
import com.ryidc.Ermu.Arena.Command.SubCommand.LeaveSubCommand;
import com.ryidc.Ermu.Arena.Command.SubCommand.SetFirstItemSubCommand;
import com.ryidc.Ermu.Arena.Command.SubCommand.SetFirstLocationSubCommand;
import com.ryidc.Ermu.Arena.Command.SubCommand.SetGameTimeSubCommand;
import com.ryidc.Ermu.Arena.Command.SubCommand.SetHourSubCommand;
import com.ryidc.Ermu.Arena.Command.SubCommand.SetLoseItemSubCommand;
import com.ryidc.Ermu.Arena.Command.SubCommand.SetLoseMoneySubCommand;
import com.ryidc.Ermu.Arena.Command.SubCommand.SetMaxPersonSubCommand;
import com.ryidc.Ermu.Arena.Command.SubCommand.SetMinPersonSubCommand;
import com.ryidc.Ermu.Arena.Command.SubCommand.SetNumberSubCommand;
import com.ryidc.Ermu.Arena.Command.SubCommand.SetSecondItemSubCommand;
import com.ryidc.Ermu.Arena.Command.SubCommand.SetSecondLocationSubCommand;
import com.ryidc.Ermu.Arena.Command.SubCommand.SetSubCommand;
import com.ryidc.Ermu.Arena.Command.SubCommand.SetThirdItemSubCommand;
import com.ryidc.Ermu.Arena.Command.SubCommand.SetWaitTimeSubCommand;
import com.ryidc.Ermu.Arena.Command.SubCommand.SetWeekSubCommand;
import com.ryidc.Ermu.Arena.Command.SubCommand.SetWaitLocationSubCommand;
import com.ryidc.Ermu.Arena.Command.SubCommand.SetWinItemSubCommand;
import com.ryidc.Ermu.Arena.Command.SubCommand.SetWinMoneySubCommand;
import com.ryidc.Ermu.Arena.Command.SubCommand.SetWinnerItemSubCommand;
import com.ryidc.Ermu.Arena.Command.SubCommand.SetJoinItemSubCommand;

/**
 * 命令的执行
 * @author Er_mu
 *
 */
public class ArenaCommand implements CommandExecutor{
	private Plugin plug;
	//存储所有指令
	private Map<String,ArSubCommand> commandList;
	public ArenaCommand(Plugin plug) {
		this.commandList = new HashMap<>();
		this.plug = plug;
		//取消
		this.commandList.put("cancel", new CancelSubCommand());
		//进入选点状态
		this.commandList.put("set", new SetSubCommand(this.plug));
		//创建竞技场
		this.commandList.put("create", new CreateSubCommand());
		//帮助
		this.commandList.put("help", new HelpSubCommand());
		//加入
		this.commandList.put("join", new JoinSubCommand());
		//离开
		this.commandList.put("leave", new LeaveSubCommand());
		//设置最小人数
		this.commandList.put("setminplayer", new SetMinPersonSubCommand());
		//设置最大人数
		this.commandList.put("setmaxplayer", new SetMaxPersonSubCommand());
		//设置等待时间
		this.commandList.put("setwaittime", new SetWaitTimeSubCommand());
		//设置游戏时长
		this.commandList.put("setgametime", new SetGameTimeSubCommand());
		//设置最大挑战次数
		this.commandList.put("setnumber", new SetNumberSubCommand());
		//设置等待大厅坐标
		this.commandList.put("setwait", new SetWaitLocationSubCommand());
		//设置出生点
		this.commandList.put("setfirstloc", new SetFirstLocationSubCommand());
		this.commandList.put("setsecondloc", new SetSecondLocationSubCommand());
		//重载
		this.commandList.put("reload", new ArReloadSubCommand(this.plug));
		//设置奖励
		this.commandList.put("setwinitem", new SetWinItemSubCommand());
		this.commandList.put("setwinmoney", new SetWinMoneySubCommand());
		this.commandList.put("setloseitem", new SetLoseItemSubCommand());
		this.commandList.put("setlosemoney", new SetLoseMoneySubCommand());
		//设置时间
		this.commandList.put("setweek", new SetWeekSubCommand());
		this.commandList.put("sethour", new SetHourSubCommand());
		//设置进入物品
		this.commandList.put("setjoinitem", new SetJoinItemSubCommand());
		//设置获胜物品
		this.commandList.put("setwinneritem", new SetWinnerItemSubCommand());
		//设置个人竞技前三物品
		this.commandList.put("setfirstitem", new SetFirstItemSubCommand());
		this.commandList.put("setseconditem", new SetSecondItemSubCommand());
		this.commandList.put("setthirditem", new SetThirdItemSubCommand());
	}
	public boolean onCommand(CommandSender sender,Command cmd,String label,String[] args) {
		//判断命令
		if(cmd.getName().equalsIgnoreCase("Arena")) {
			//判断执行者
			if(sender instanceof Player) {
				Player p = (Player)sender;
				//判断是否有二级命令
				if(args.length==0) {
					//没有输入直接提示帮助
					this.commandList.get("help").command(p, args);
					return true;
				}
				//存在状态
				if(!this.commandList.containsKey(args[0])) {
					//不存在
					p.sendMessage("§c子命令不存在");
					return true;
				}
				//判断权限
				if(!p.hasPermission("*")&&!p.hasPermission("Arena."+args[0])&&!args[0].equals("help")) {
					p.sendMessage("§c你没有权限哦~");
					return true;
				}
				//调用对应方法
				this.commandList.get(args[0]).command(p, args);
			}
			else {
				sender.sendMessage("该指令只能被玩家执行!");
			}
			return true;
		}
		return false;
	}
}
