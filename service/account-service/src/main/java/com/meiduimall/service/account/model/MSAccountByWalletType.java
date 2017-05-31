package com.meiduimall.service.account.model;

import java.io.Serializable;
import java.util.Date;


/**
 * 会员按业务类型账户表Model
 * @author chencong
 *
 */
public class MSAccountByWalletType extends BaseModel implements Serializable {

	private static final long serialVersionUID = 8080490869514026587L;
	
	/** 账户标识 */
	private String id;
	
	/** 对应ms_wallet_type表的主键*/
	private String wallet_type;

	/** 当前类型总金额明文*/
	private String balance;

	/**当前类型冻结余额明文 */
	private String balance_encrypt;
	
	/**冻结总金额明文*/
	private String freeze_balance;

	/**当前类型冻结余额密文*/
	private String freeze_balance_encrypt;
	
	/**账户状态,0 正常 1禁用*/
	private String account_status;
	
	/**账户状态密文,0 正常 1禁用*/
	private String account_status_encrypt;

	/** 创建时间 */
	private Date create_date;

	/** 修改时间 */
	private Date update_date;
	
	/**更新人*/
	private String update_man;
	
	/**备注*/
	private String remark;

	
	
}
