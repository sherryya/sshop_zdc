package com.tmount.business.manage.controller;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.manage.service.TItovMenuRoleService;
import com.tmount.db.manage.dto.TItovMenuRole;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
/**
 *添加角色
 * 
 * @author 
 * 
 */
@Controller
public class AddItovMenuRoleController extends ControllerBase {
	@Autowired
	private TItovMenuRoleService tItovMenuRoleService;
	@RequestMapping(value = "menuRole.add")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {	
		String Role_Name = ParamData.getString(requestParam.getBodyNode(),	"Role_Name");//角色名称
		String Role_Description = new String(ParamData.getString(	requestParam.getBodyNode(), "Role_Description"));//描述
		Integer Role_Taxis=ParamData.getInt(requestParam.getBodyNode(),	"Role_Taxis");//排序
		if (StringUtils.isEmpty(Role_Name)) {
			throw new ShopBusiException(ShopBusiErrorBundle.NO_NULL_VALUES,
					new Object[] { "角色Role_Name" });
		}
		if (StringUtils.isEmpty(String.valueOf(Role_Taxis))) {
			throw new ShopBusiException(ShopBusiErrorBundle.NO_NULL_VALUES,
					new Object[] { "排序Role_Taxis" });
		}
		TItovMenuRole tItovMenuRole = tItovMenuRoleService.selectByRoleName(Role_Name);// 判断角色名是否存在
		if (tItovMenuRole != null) {
			throw new ShopBusiException(ShopBusiErrorBundle.ISEXIST,	new Object[] {"角色", Role_Name });
		}
		TItovMenuRole role = new TItovMenuRole();
		role.setRoleDescription(Role_Description);
		role.setRoleName(Role_Name);
		role.setRoleTaxis(Role_Taxis);
		tItovMenuRoleService.insert(role);
		responseBodyJson.writeStringField("result", "0");
	}
}
