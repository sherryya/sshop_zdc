package com.tmount.business.order.vo;

/**
 * 订单分支判断类，位从左到右排列
 * @author lugz
 *
 */
public class OrderCondition {
	
	/**
	 * 第一位	|支付方式：0 电子支付（默认）；1货到付款 1000000000000000
	 */
	public static final int payWayIdBit = 0x8000;
	
	/**
	 * 第二位	|是否主动取消订单：0不取消（默认）；1取消订单	用户是否主动。 0100000000000000
	 */
	public static final int cancelOrderBit = 0x4000;

	/**
	 * 第三位	|退货审核商家是否同意：0同意（默认）；1不同意 0010000000000000
	 */
	public static final int agreeBackOrderBit = 0x2000;
	
	/**
	 * 第四位	|是否付款：0已经付款；1未付款			0001000000000000
	 */
	public static final int payMoneyBit = 0x1000;

	/**
	 * 支付方式的校验位值  0000000010000000
	 */
	public static final int payWayIdVerifyBit = 0x0080;

	/**
	 * 是否主动取消订单的校验位值
	 */
	public static final int cancelOrderVerifyBit = 0x0040;

	/**
	 * 退货审核商家是否同意的校验位值
	 */
	public static final int agreeBackOrderVerifyBit = 0x0020;

	/**
	 * 是否付款的校验位值
	 */
	public static final int payMoneyVerifyBit = 0x0010;

	/**
	 * 验证给定值是否可以办理下一步订单。
	 * @param orderCondition 订单分支判断信息。
	 * @param currentOrderCondition 当前订单的条件信息。
	 * @return
	 */
	public static boolean isValidOrderConditon(int orderCondition, int currentOrderCondition) {
		//判断支付方式
		if ((orderCondition & payWayIdVerifyBit) != 0) {
			if ((orderCondition & payWayIdBit) != (currentOrderCondition & payWayIdBit)) {
				return false;
			}
		}

		//判断是否主动取消订单
		if ((orderCondition & cancelOrderVerifyBit) != 0) {
			if ((orderCondition & cancelOrderBit) != (currentOrderCondition & cancelOrderBit)) {
				return false;
			}
		}

		//判断退货审核商家是否同意
		if ((orderCondition & agreeBackOrderVerifyBit) != 0) {
			if ((orderCondition & agreeBackOrderBit) != (currentOrderCondition & agreeBackOrderBit)) {
				return false;
			}
		}

		//判断是否付款
		if ((orderCondition & payMoneyVerifyBit) != 0) {
			if ((orderCondition & payMoneyBit) != (currentOrderCondition & payMoneyBit)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 生成订单条件值
	 * @return
	 */
	public static int generatorOrderCondition(int payWayId, boolean payWayIdVerify,
			boolean isCancelOrder, boolean cancelOrderVerify,
			boolean isAgreeBackOrder, boolean agreeBackOrderVerify,
			boolean isPayMoney, boolean payMoneyVerify) {
		int currentOrderCondition = 0;
		if (payWayId == 2) {	//2  货到付款: 如果货到付款，则设置标志位。
			currentOrderCondition |= payWayIdBit;
		}
		if (payWayIdVerify) {
			currentOrderCondition |= payWayIdVerifyBit;
		}

		if (isCancelOrder) {	//是否主动取消订单：如果取消订单，则设置标志位。
			currentOrderCondition |= cancelOrderBit;
		}
		if (cancelOrderVerify) {
			currentOrderCondition |= cancelOrderVerifyBit;
		}

		if (!isAgreeBackOrder) {	//退货审核商家是否同意：如果不同意，则设置标志位。
			currentOrderCondition |= agreeBackOrderBit;
		}
		if (agreeBackOrderVerify) {
			currentOrderCondition |= agreeBackOrderVerifyBit;
		}

		if (!isPayMoney) {	//是否付款：如果未付款，则设置标志位。
			currentOrderCondition |= payMoneyBit;
		}
		if (payMoneyVerify) {
			currentOrderCondition |= payMoneyVerifyBit;
		}
		return currentOrderCondition;
	}
}
