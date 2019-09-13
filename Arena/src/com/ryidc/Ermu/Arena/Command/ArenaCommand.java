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
 * �����ִ��
 * @author Er_mu
 *
 */
public class ArenaCommand implements CommandExecutor{
	private Plugin plug;
	//�洢����ָ��
	private Map<String,ArSubCommand> commandList;
	public ArenaCommand(Plugin plug) {
		this.commandList = new HashMap<>();
		this.plug = plug;
		//ȡ��
		this.commandList.put("cancel", new CancelSubCommand());
		//����ѡ��״̬
		this.commandList.put("set", new SetSubCommand(this.plug));
		//����������
		this.commandList.put("create", new CreateSubCommand());
		//����
		this.commandList.put("help", new HelpSubCommand());
		//����
		this.commandList.put("join", new JoinSubCommand());
		//�뿪
		this.commandList.put("leave", new LeaveSubCommand());
		//������С����
		this.commandList.put("setminplayer", new SetMinPersonSubCommand());
		//�����������
		this.commandList.put("setmaxplayer", new SetMaxPersonSubCommand());
		//���õȴ�ʱ��
		this.commandList.put("setwaittime", new SetWaitTimeSubCommand());
		//������Ϸʱ��
		this.commandList.put("setgametime", new SetGameTimeSubCommand());
		//���������ս����
		this.commandList.put("setnumber", new SetNumberSubCommand());
		//���õȴ���������
		this.commandList.put("setwait", new SetWaitLocationSubCommand());
		//���ó�����
		this.commandList.put("setfirstloc", new SetFirstLocationSubCommand());
		this.commandList.put("setsecondloc", new SetSecondLocationSubCommand());
		//����
		this.commandList.put("reload", new ArReloadSubCommand(this.plug));
		//���ý���
		this.commandList.put("setwinitem", new SetWinItemSubCommand());
		this.commandList.put("setwinmoney", new SetWinMoneySubCommand());
		this.commandList.put("setloseitem", new SetLoseItemSubCommand());
		this.commandList.put("setlosemoney", new SetLoseMoneySubCommand());
		//����ʱ��
		this.commandList.put("setweek", new SetWeekSubCommand());
		this.commandList.put("sethour", new SetHourSubCommand());
		//���ý�����Ʒ
		this.commandList.put("setjoinitem", new SetJoinItemSubCommand());
		//���û�ʤ��Ʒ
		this.commandList.put("setwinneritem", new SetWinnerItemSubCommand());
		//���ø��˾���ǰ����Ʒ
		this.commandList.put("setfirstitem", new SetFirstItemSubCommand());
		this.commandList.put("setseconditem", new SetSecondItemSubCommand());
		this.commandList.put("setthirditem", new SetThirdItemSubCommand());
	}
	public boolean onCommand(CommandSender sender,Command cmd,String label,String[] args) {
		//�ж�����
		if(cmd.getName().equalsIgnoreCase("Arena")) {
			//�ж�ִ����
			if(sender instanceof Player) {
				Player p = (Player)sender;
				//�ж��Ƿ��ж�������
				if(args.length==0) {
					//û������ֱ����ʾ����
					this.commandList.get("help").command(p, args);
					return true;
				}
				//����״̬
				if(!this.commandList.containsKey(args[0])) {
					//������
					p.sendMessage("��c���������");
					return true;
				}
				//�ж�Ȩ��
				if(!p.hasPermission("*")&&!p.hasPermission("Arena."+args[0])&&!args[0].equals("help")) {
					p.sendMessage("��c��û��Ȩ��Ŷ~");
					return true;
				}
				//���ö�Ӧ����
				this.commandList.get(args[0]).command(p, args);
			}
			else {
				sender.sendMessage("��ָ��ֻ�ܱ����ִ��!");
			}
			return true;
		}
		return false;
	}
}
