package com.tmount.business.user.service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.db.car.dto.UserRelationCarInfo;
import com.tmount.db.product.dao.GdItemsExpRiceMapper;
import com.tmount.db.product.dao.GdItemsInfoMapper;
import com.tmount.db.product.dao.GdShopInfoMapper;
import com.tmount.db.product.dto.GdItemsExpRice;
import com.tmount.db.product.dto.GdItemsInfo;
import com.tmount.db.product.dto.GdShopInfo;
import com.tmount.db.pub.dao.DbSequenceMapper;
import com.tmount.db.pub.dao.DbTimeMapper;
import com.tmount.db.user.dao.UsAccountAuxiliaryMapper;
import com.tmount.db.user.dao.UsBuyItemsMapper;
import com.tmount.db.user.dao.UsFavouriteItemsMapper;
import com.tmount.db.user.dao.UsFavouriteShopMapper;
import com.tmount.db.user.dao.UsInvoiceCfgMapper;
import com.tmount.db.user.dao.UsPersonalMapper;
import com.tmount.db.user.dao.UsShopCartMapper;
import com.tmount.db.user.dao.UsUserAccountMapper;
import com.tmount.db.user.dao.UsUserAddLogMapper;
import com.tmount.db.user.dao.UsUserAddressMapper;
import com.tmount.db.user.dao.UsUserCommentMapper;
import com.tmount.db.user.dao.UsUserInfoMapper;
import com.tmount.db.user.dao.UsUserMapper;
import com.tmount.db.user.dto.UsAccount;
import com.tmount.db.user.dto.UsAccountAuxiliary;
import com.tmount.db.user.dto.UsBuyItemsKey;
import com.tmount.db.user.dto.UsFavouriteItems;
import com.tmount.db.user.dto.UsFavouriteItemsKey;
import com.tmount.db.user.dto.UsFavouriteShop;
import com.tmount.db.user.dto.UsFavouriteShopKey;
import com.tmount.db.user.dto.UsInvoiceCfg;
import com.tmount.db.user.dto.UsInvoiceCfgKey;
import com.tmount.db.user.dto.UsPersonal;
import com.tmount.db.user.dto.UsShopCart;
import com.tmount.db.user.dto.UsShopCartKey;
import com.tmount.db.user.dto.UsUser;
import com.tmount.db.user.dto.UsUserAccount;
import com.tmount.db.user.dto.UsUserAddress;
import com.tmount.db.user.dto.UsUserAddressKey;
import com.tmount.db.user.dto.UsUserComment;
import com.tmount.db.user.vo.UsBuyItemsInput;
import com.tmount.db.user.vo.UsFavouriteItemsDetail;
import com.tmount.db.user.vo.UsFavouriteShopInput;
import com.tmount.db.user.vo.UsShopCartItems;
import com.tmount.db.user.vo.UsUserAddressExpl;
import com.tmount.db.user.vo.UsUserBuyItemsView;
import com.tmount.db.util.dto.CommonBean;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopParamException;
import com.tmount.util.ParamData;

@Service
public class UserService {
	@Autowired
	private UsUserAccountMapper usUserAccountMapper;

	@Autowired
	private UsUserInfoMapper usUserInfoMapper;
	
	@Autowired
	private UsUserCommentMapper usUserCommentMapper;

	@Autowired
	private UsFavouriteItemsMapper usFavouriteItemsMapper;
	
	@Autowired
	private UsFavouriteShopMapper usFavouriteShopMapper;

	@Autowired
	private DbTimeMapper dbTimeMapper;

	@Autowired
	private DbSequenceMapper dbSequenceMapper;

	@Autowired
	private UsShopCartMapper usShopCartMapper;

	@Autowired
	private UsUserAddressMapper usUserAddressMapper;
	
	@Autowired
	private GdItemsExpRiceMapper gdItemsExpRiceMapper;
	
	@Autowired
	private UsInvoiceCfgMapper usInvoiceCfgMapper;
	
	@Autowired
	private UsBuyItemsMapper usBuyItemsMapper;
	
	@Autowired
	private GdItemsInfoMapper gdItemsInfoMapper;
	
	@Autowired
	private GdShopInfoMapper gdShopInfoMapper;
	
	@Autowired
	private UsUserAddLogMapper usUserAddLogMapper;
	
	@Autowired
	private UsAccountAuxiliaryMapper  usAccountAuxiliaryMapper;
	
	@Autowired
	private UsUserMapper usUserMapper; 
	@Autowired
	private UsPersonalMapper usPersonalMapper; 
	/**
	 * 获取用户余额信息
	 * @param userid
	 * @return
	 */
	public UsUserAccount getUsUserAccountByUserId(Long userid){
		return usUserAccountMapper.selectByPrimaryKey(userid);
	}

	/**
	 * 获取用户信息
	 * @param userid
	 * @return
	 */
	public UsAccount getUsUserInfoByUserAccount(String userAcount){
		return usUserInfoMapper.selectByUserAccount(userAcount);
	}

	public UsAccount getUsUserInfo(UsAccount usUserAccount){
		return usUserInfoMapper.getUsUserInfo(usUserAccount);
	}
	
	public void updateLoginStatus(UsAccount usUserAccount)
	{
		usUserInfoMapper.updateLoginStatus(usUserAccount);
		
	}

	/**
	 * 创建用户账户。
	 * @param usUserAccount 用户账户
	 * @return
	 */
	public void insertUsUserAccount(UsAccount usUserAccount){
		System.out.println(usUserAccount);
		usUserAccountMapper.insert(usUserAccount);
		
	}
	/**
	 * 根據用戶名即車的imei獲得車的accountid
	 * @param getAccountIdByName
	 * @return
	 */
	public Long getAccountIdByName(String getAccountIdByName)
	{
		return usUserAccountMapper.getAccountIdByName(getAccountIdByName);
	}
	
	public void insertPersonalInfo(UsPersonal usPersonal){
		System.out.println(usPersonal);
		usPersonalMapper.insert_person(usPersonal);
		
	}
	/**
	 * 增加用户收藏商品
	 * @param reUserOrder
	 * @return
	 */
	public void insertUsFavouriteItems(UsFavouriteItems usFavouriteItems){
		usFavouriteItemsMapper.insert(usFavouriteItems);
	}	

	/**
	 * 查询用户收藏的商品。
	 * @param key
	 */
	public GdItemsInfo selectUsFavouriteItems(UsFavouriteItemsKey key){
		UsFavouriteItems usFavouriteItems = usFavouriteItemsMapper.selectByPrimaryKey(key);
		if (usFavouriteItems == null ) {
			return null;
		}
		GdItemsInfo gdItemsInfo = gdItemsInfoMapper.selectByPrimaryKey(usFavouriteItems.getItemsId());
		if (gdItemsInfo == null) {
			gdItemsInfo = new GdItemsInfo();
			gdItemsInfo.setItemsId(usFavouriteItems.getItemsId());
			gdItemsInfo.setItemsName("未知");
			return gdItemsInfo;
		} else {
			return gdItemsInfo;
		}
	}	

	/**
	 * 根据指定的条件查询用户收藏商品。
	 * @param paramMap
	 * @return
	 */
	public List<UsFavouriteItemsDetail> getUsFavouriteItemsDetail(Map<String, Object> paramMap) {
		return usFavouriteItemsMapper.selectBySelective(paramMap);
	}
	
	/**
	 * 根据指定的条件查询用户收藏商品数量。
	 * @param paramMap
	 * @return
	 */
	public int getUsFavouriteItemsDetailCount(Map<String, Object> paramMap) {
		return usFavouriteItemsMapper.selectBySelectiveCount(paramMap);
	}
	/**
	 * 用户收藏商品删除，单个删除
	 * @param key
	 */
	public void deleteUsFavouriteItems(UsFavouriteItemsKey key) {
		usFavouriteItemsMapper.deleteByPrimaryKey(key);
	}

	/**
	 * 增加用户收藏商店
	 * @param reUserOrder
	 * @return
	 */
	public void insertUsFavouriteShop(UsFavouriteShop usFavouriteShop){
		usFavouriteShopMapper.insert(usFavouriteShop);
	}	

	/**
	 * 查询用户收藏的商店。
	 * @param key
	 */
	public GdShopInfo selectUsFavouriteShop(UsFavouriteShopKey usFavouriteShopKey){
		UsFavouriteShop usFavouriteShop = usFavouriteShopMapper.selectByPrimaryKey(usFavouriteShopKey);
		if (usFavouriteShop == null ) {
			return null;
		}
		GdShopInfo gdShopInfo = gdShopInfoMapper.selectByPrimaryKey(usFavouriteShop.getShopId());
		if (gdShopInfo == null) {
			gdShopInfo = new GdShopInfo();
			gdShopInfo.setShopId(usFavouriteShop.getShopId());
			gdShopInfo.setShopName("未知");
			return gdShopInfo;
		} else {
			return gdShopInfo;
		}
	}
	
	/**
	 * 查询用户收藏的商店列表。
	 * @param usFavouriteShopKey
	 */
	public List<UsFavouriteShop> selectUsFavouriteShopList(UsFavouriteShopInput usFavouriteShopInput){
		return usFavouriteShopMapper.selectBySelective(usFavouriteShopInput);
	}
	public int selectUsFavouriteCountBySelective(UsFavouriteShopInput usFavouriteShopInput){
		return usFavouriteShopMapper.selectCountBySelective(usFavouriteShopInput);
	}
	/**
	 * 得到用户评论
	 * @param usUserComment
	 * @return
	 */
	public List<UsUserComment> getUsUserCommentList(UsUserComment usUserComment){
		return usUserCommentMapper.selectListById(usUserComment);
	}


	/**
	 * 根据指定的条件查询购物车中的内容。
	 * @param key
	 * @return
	 */
	public List<UsShopCartItems> getUsShopCart(UsShopCartKey key) {
		return usShopCartMapper.selectBySelective(key);
	}
	
	/**
	 * 根据指定的条件查询购物车中商品的数量。
	 * @param key
	 * @return
	 */
	public Integer getItemsCount(UsShopCartKey key) {
		return usShopCartMapper.selectItemsCount(key);
	}
	
	/**
	 * 得到大米属性。
	 * @param itemsId
	 * @return
	 */
	public GdItemsExpRice getGdItemsExpRiceByItemsId(Long itemsId) {
		return gdItemsExpRiceMapper.selectByPrimaryKey(itemsId);
	}

	/**
	 * 批量增加购物车商品
	 * @param bodyNode
	 * @throws ShopParamException 
	 */
	public void insertUsShopCart(JsonNode bodyNode) throws ShopParamException{
		Date createTime = dbTimeMapper.selectDbTime().getDbTime();
		JsonNode itemListNode = bodyNode.get("item_list");
		Iterator<JsonNode> detailIt = itemListNode.elements();
		JsonNode detailJson;
		UsShopCart usShopCart = new UsShopCart();
		UsShopCart oldUsShopCart;
		UsShopCartKey oldKey = new UsShopCartKey();
		while(detailIt.hasNext()) {
			detailJson = detailIt.next();
			usShopCart.setUserId(ParamData.getLong(detailJson, "user_id"));
			usShopCart.setShopId(ParamData.getLong(detailJson, "shop_id"));
			usShopCart.setItemsId(ParamData.getLong(detailJson, "items_id"));
			usShopCart.setDiscount(ParamData.getDouble(detailJson, "discount"));
			usShopCart.setAcount(ParamData.getInt(detailJson, "acount"));
			usShopCart.setCreateTime(createTime);
			oldKey.setUserId(usShopCart.getUserId());
			oldKey.setShopId(usShopCart.getShopId());
			oldKey.setItemsId(usShopCart.getItemsId());
			oldUsShopCart = usShopCartMapper.selectByPrimaryKey(oldKey);
			if (oldUsShopCart != null) {
				usShopCart.setAcount(usShopCart.getAcount() + oldUsShopCart.getAcount());
				usShopCartMapper.updateByPrimaryKeySelective(usShopCart);
			} else {
				usShopCartMapper.insert(usShopCart);
			}
		}
	}	


	/**
	 * 单个取消购物车中的商品
	 * @param bodyNode
	 * @throws ShopParamException 
	 */
	public void deleteUsShopCart(JsonNode bodyNode) throws ShopParamException{
		Date createTime = dbTimeMapper.selectDbTime().getDbTime();
		UsShopCart usShopCart = new UsShopCart();

		UsShopCartKey oldKey = new UsShopCartKey();
		UsShopCart oldUsShopCart;

		usShopCart.setUserId(ParamData.getLong(bodyNode, "user_id"));
		usShopCart.setShopId(ParamData.getLong(bodyNode, "shop_id"));
		usShopCart.setItemsId(ParamData.getLong(bodyNode, "items_id"));
		usShopCart.setAcount(ParamData.getInt(bodyNode, "acount", 0));
		if (usShopCart.getAcount() == 0) {	//如果商品数量为0，表示删除此商品的所有订购。
			usShopCartMapper.deleteByPrimaryKey(usShopCart);
		} else {
			usShopCart.setCreateTime(createTime);
			oldKey.setUserId(usShopCart.getUserId());
			oldKey.setShopId(usShopCart.getShopId());
			oldKey.setItemsId(usShopCart.getItemsId());
			oldUsShopCart = usShopCartMapper.selectByPrimaryKey(oldKey);
			if (oldUsShopCart != null) {
				if (oldUsShopCart.getAcount() > usShopCart.getAcount()) {	//删除一部分数量的商品。
					usShopCart.setAcount(oldUsShopCart.getAcount() - usShopCart.getAcount());
					usShopCartMapper.updateByPrimaryKeySelective(usShopCart);
				} else {	//取消商品下的所有数量。
					usShopCartMapper.deleteByPrimaryKey(oldKey);
				}
			} else {
				usShopCartMapper.deleteByPrimaryKey(oldKey);
			}
		}
	}	

	/**
	 * 用户地址增加
	 * @param usUserAddress
	 */
	public void insertUsUserAddress(UsUserAddress usUserAddress){
		if (usUserAddress.getOrders() == null) {
			Integer orders = usUserAddressMapper.selectMaxOrders(usUserAddress.getUserId());
			if (orders == null) {
				usUserAddress.setOrders(0);
			} else {
				orders ++;
				usUserAddress.setOrders(orders);
			}
		}
		if (usUserAddress.getPostAddr().equals("1")) {
			usUserAddressMapper.updateUndefaultPostAddr(usUserAddress.getUserId());
		}
		usUserAddressMapper.insert(usUserAddress);
	}	

	/**
	 * 用户地址修改
	 * @param usUserAddress
	 */
	public void updateUsUserAddress(UsUserAddress usUserAddress){
		if (usUserAddress.getPostAddr().equals("1")) {
			usUserAddressMapper.updateUndefaultPostAddr(usUserAddress.getUserId());
		}
		usUserAddressMapper.updateByPrimaryKeySelective(usUserAddress);
	}	

	/**
	 * 得到用户下得所有地址信息。
	 * @param userId
	 * @return
	 */
	public List<UsUserAddressExpl> getUsUserAddressListByUserId(UsUserAddress usUserAddress){
		return usUserAddressMapper.selectBySelective(usUserAddress);
	}

	
	
	/**
	 * 查询用户发票配置信息
	 * @param userid
	 * @return
	 */
	public List<UsInvoiceCfg> getUsInvoiceCfgListByUserId(Long userId){
		
		return usInvoiceCfgMapper.selectByUserId(userId);
	}
	/**
	 * 增加用户发票配置信息
	 * @param userid
	 * @return 
	 * @return
	 */
	public int insertUsInvoiceCfg(UsInvoiceCfg record){

		Integer orders;
		orders = usInvoiceCfgMapper.selectUserInvoiceOrders(record.getUserId());
		record.setOrders(orders);
		
		return usInvoiceCfgMapper.insertSelective(record);
	}

	
	public int deleteUserAddress(UsUserAddressKey key)
	{
		return usUserAddressMapper.deleteByPrimaryKey(key);
	}
	
	
	/**
	 * 用户已经购买商品数量
	 * @param UsBuyItemsKey
	 * @return
	 */
	public int getUsBuyItemsCount(UsBuyItemsInput sBuyItemsInput) {
		// TODO Auto-generated method stub
		return usBuyItemsMapper.selectCountBySelective(sBuyItemsInput);
	}
	/**
	 * 用户已经购买商品
	 * @param UsBuyItemsKey
	 * @return
	 */
	public List<UsUserBuyItemsView> getUsBuyItemsList(UsBuyItemsInput sBuyItemsInput)
	{
		return usBuyItemsMapper.selectBySelective(sBuyItemsInput);
	}
	
	/**
	 * 用户购买过的店铺
	 * @param UsBuyItemsKey
	 * @return
	 */
	public List<UsUserBuyItemsView> getUsBuyShopList(UsBuyItemsKey usBuyItemsKey)
	{
		return usBuyItemsMapper.selectBuyShopBySelective(usBuyItemsKey);
	}
	/**
	 * 用户收藏店铺删除，单个删除
	 * @param key
	 */
	public void deleteUsFavouriteShop(UsFavouriteShopKey usFavouriteShopKey) {
		// TODO Auto-generated method stub
		usFavouriteShopMapper.deleteByPrimaryKey(usFavouriteShopKey);
		
	}

	/**
	 * 用户发票配置信息，单个删除
	 * @param usInvoiceCfgKey
	 */
	public void deleteUserInvoice(UsInvoiceCfgKey usInvoiceCfgKey) {
		// TODO Auto-generated method stub
		usInvoiceCfgMapper.deleteByPrimaryKey(usInvoiceCfgKey);
	}

	public String userAdd(String account_name,String account_password,String account_type,String account_role_id,String company_id) throws ShopBusiException {

		UsAccount usUserInfoOld = getUsUserInfoByUserAccount(account_name);
		if (usUserInfoOld != null) {
			throw new ShopBusiException(ShopBusiErrorBundle.EXISTS_USER, new Object[]{account_name});
		}
		//Long userId = dbSequenceMapper.selectSeqByName(SyDataTypeDicStatic.UserIdSeq).getSeqValue();
		Long userId = (long) 1;
		UsAccount usUserInfo = new UsAccount();
		//insertUsUserInfo(usUserInfo);
		UsAccount usUserAccount = new UsAccount();
		usUserAccount.setAccount_id(userId);
		usUserAccount.setAccount_name(account_name);
		usUserAccount.setAccount_password(account_password);
		usUserAccount.setAccount_type(Integer.valueOf( account_type));
		usUserAccount.setCompany_id(Long.parseLong(company_id));
		usUserAccount.setAccount_role_id(0);
		System.out.println(account_name+","+account_password+","+userId);
		insertUsUserAccount(usUserAccount);
		
		return  String .valueOf( userId);
	}	
	
	/**
	 * 获取用户信息
	 * @param userid
	 * @return
	 */
	public UsAccountAuxiliary getUsAccountAuxiliary(String userAccount){
		return usAccountAuxiliaryMapper.getUsAccountAuxiliary(userAccount);
	}
	
	
	public UsUser getUsUser(String userAccount){
		return usUserMapper.getUsUser(userAccount);
	}
	
	public UsAccount getUsUserInfoByAccountId(int userAcount){
		return usUserInfoMapper.getUsUserInfoByAccountId(userAcount);
	}
	public UsAccount getTradeUserInfoByUserAccount(int userAcount){
		return usUserInfoMapper.getUsUserInfoByAccountId(userAcount);
	}
	public void insertUserInfo(UsUser usUser){
		usUserMapper.insertUserInfo(usUser);
	}
	
	public void insertRelationUserAndCar(UserRelationCarInfo  userRelationCarInfo){
		usUserMapper.insertRelationUserAndCar(userRelationCarInfo);
	}
	public Long getUserMessage(Long account_id){
		return usUserMapper.getUserMessage(account_id);
	}
	
	public List<CommonBean> getRetMessageList(Long account_id){
		return usUserMapper.getRetMessageList(account_id);
	}
	public String getRelationCarInfoCompare(int user_id){
		return usUserMapper.getRelationCarInfoCompare(user_id);
	}
	/**
	 * 车热了吗--根据userid查询terminal_car中查询
	 * @param user_id
	 * @return
	 */
	public List<UserRelationCarInfo> getRelationCarInfo(long user_id)
	{
		return usUserMapper.getRelationCarInfo(user_id);
	}
	public CommonBean getPictureMessage(String car_brands){
		return usUserMapper.getPictureMessage(car_brands);
	}
	public String getRelationUserInfoByAccountId(int account_id){
		return usUserMapper.getRelationUserInfoByAccountId(account_id);
	}
	
	public CommonBean getVersion(int platform){
		return usUserMapper.getVersion(platform);
	}
	
	public 	CommonBean getVersion_ter(CommonBean commonBean){
		return usUserMapper.getVersion_ter(commonBean);
	}
	public Integer getAgentId(String agent_id){
		return usUserMapper.getAgentId(agent_id);
	}
	public CommonBean getCommonDeviceUid(Long account_id){
		return usUserMapper.getCommonDeviceUid(account_id);
	}
	
	public CommonBean getCommonDeviceUidForGPS(String personal_tel){
		return usUserMapper.getCommonDeviceUidForGPS(personal_tel);
	}
	public 	void updateNickNameByAccount(UsAccount usAccount)
	{
		usUserMapper.updateNickNameByAccount(usAccount);
		
	}
	
	public    int updatePicName(UsAccount usAccount)
	{
		return usUserMapper.updatePicName(usAccount);
	}
	
	public 	int updatePwd(UsAccount usAccount)
	{
		return usUserMapper.updatePwd(usAccount);
	}
	/**
	 * 车热了吗，解除手机绑定信息，更新手机号
	 * @param usAccount
	 * @return
	 */
	public int updateAccountName(UsAccount usAccount)
	{
		return usUserMapper.updateAccountName(usAccount);
	}
}
