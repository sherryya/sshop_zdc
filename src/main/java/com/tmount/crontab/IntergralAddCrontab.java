package com.tmount.crontab;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.tmount.business.integral.service.AddIntegralService;
import com.tmount.business.integral.service.IntegraIDslService;
import com.tmount.db.integral.dto.TItovIntegral;
import com.tmount.db.integral.dto.TItovIntegralIds;
import com.tmount.db.integral.dto.TItovIntegralRule;
import com.tmount.db.integral.dto.TItovIntegralTotal;
import com.tmount.db.user.dto.UsAccount;
public class IntergralAddCrontab {
	@Autowired
	AddIntegralService addIntegralService;
	@Autowired
	IntegraIDslService integraIDslService;
	Logger logger = Logger.getLogger(IntergralAddCrontab.class);
	TItovIntegralIds tItovIntegralIds=new TItovIntegralIds();
	Date date_ivr;
	Date date_voip;
	Date date_login;
	Date date_newuser;
	public void run() {
		logger.debug("积分计算定时器开始");
		tItovIntegralIds=integraIDslService.selectAll();
		if(tItovIntegralIds==null)
		{
			
		}
		else
		{
			//1 计算新注册用户的积分
			logger.debug("计算新注册用户的积分定时器开始");
			dealNewUser(tItovIntegralIds.getNewuser());
			logger.debug("计算新注册用户的积分定时器结束");
			//2.计算IVR用户的积分
			logger.debug("计算IVR用户的积分定时器开始");
			dealIVR(tItovIntegralIds.getIvr());
			logger.debug("计算IVR用户的积分定时器结束");
            //3.计算VOIP用户的积分
			logger.debug("计算VOIP用户的积分开始");
			dealVOIP(tItovIntegralIds.getVoip());
			logger.debug("计算VOIP用户的积分结束");
			tItovIntegralIds.setNewuser(date_newuser);
			tItovIntegralIds.setIvr(date_ivr);
			tItovIntegralIds.setVoip(date_voip);
			integraIDslService.updateByPrimaryKeySelective(tItovIntegralIds);
		}
		logger.debug("积分计算定时器结束");
	}
	private void dealNewUser(Date date)
	{
		List<UsAccount> account=new ArrayList<UsAccount>();
		UsAccount usAccount=new  UsAccount();
		usAccount.setCrt_date(date);
		account=addIntegralService.selectNewUser(usAccount);
		for(UsAccount ua:account)
		{
			addIntergral(1,ua.getAccount_id());
			date_newuser=ua.getCrt_date();
		}
	}
	
	private void dealIVR(Date date)
	{
		List<UsAccount> account=new ArrayList<UsAccount>();
		UsAccount usAccount=new  UsAccount();
		usAccount.setCrt_date(date);
		account=addIntegralService.selectIVR(usAccount);
		for(UsAccount ua:account)
		{
			addIntergral(3,ua.getAccount_id());
			date_ivr=ua.getCrt_date();
		}
	}
	private void dealVOIP(Date date)
	{
		List<UsAccount> account=new ArrayList<UsAccount>();
		UsAccount usAccount=new  UsAccount();
		usAccount.setCrt_date(date);
		account=addIntegralService.selectVOIP(usAccount);
		for(UsAccount ua:account)
		{
			addIntergral(4,ua.getAccount_id());
			date_voip=ua.getCrt_date();
		}
	}
	private void addIntergral(int rule_type, Long account_id) {
		try {
			// 1 通过积分规则查看积分数
			TItovIntegralRule ItovIntegralRule = new TItovIntegralRule();
			ItovIntegralRule.setType(rule_type);
			TItovIntegralRule ItovIntegralRule1 = new TItovIntegralRule();
			ItovIntegralRule1 = addIntegralService.selectIntegralByType(ItovIntegralRule);
			Integer integral = 0;// 规则对应的积分数
			String comment = "";
			if (!ItovIntegralRule1.equals(null)) {
				integral = ItovIntegralRule1.getIntegral();
				comment = ItovIntegralRule1.getName();
			}
			// 2 添加积分明细表
			TItovIntegral tItovIntegral = new TItovIntegral();
			tItovIntegral.setAccount_id(account_id);
			tItovIntegral.setComment(comment);
			tItovIntegral.setCrt_date(new java.util.Date());
			tItovIntegral.setIntegral(integral);
			tItovIntegral.setRule_type(rule_type);
			addIntegralService.insert_integral(tItovIntegral);
			// 3 添加积分综合
			TItovIntegralTotal tItovIntegralTotal = new TItovIntegralTotal();
			TItovIntegralTotal tItovIntegralTotal1 = new TItovIntegralTotal();
			tItovIntegralTotal1.setAccount_id(account_id);
			tItovIntegralTotal = addIntegralService.selectTotalByAccount(tItovIntegralTotal1);
			tItovIntegralTotal1.setIntegral(integral);
			tItovIntegralTotal1.setRule_type(rule_type);
			if (tItovIntegralTotal == null) {
				addIntegralService.insert_integral_total(tItovIntegralTotal1);
			} else {
				addIntegralService.update_integral_total(tItovIntegralTotal1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.debug("积分计算定时器出错了");
		}
	}
}
