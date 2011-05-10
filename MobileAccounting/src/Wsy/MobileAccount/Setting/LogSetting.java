package Wsy.MobileAccount.Setting;

import Wsy.MobileAccount.Debug;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class LogSetting extends Setting
{
	private boolean successLogin;
	private boolean failedLogin;
	private boolean logOff;
	private boolean addRecord;
	private boolean editDelRecord;
	private boolean clearExpiredData;
	private boolean syncData;
	private boolean manuallyDelLog;
	private boolean changeSetting;
	private boolean changeSecuritySetting;
	
	public LogSetting()
	{
		super("Setting_Log");Debug.show("LogSetting_Constructor");
		initDefaultValue();
	}

	private void initDefaultValue()
	{Debug.show("LogSetting_initDefaultValue");
		successLogin = false;
		failedLogin = true;
		logOff = false;
		addRecord = false;
		editDelRecord = true;
		clearExpiredData = true;
		syncData = true;
		manuallyDelLog = true;
		changeSetting = false;
		changeSecuritySetting = true;
	}
	
	public boolean isSuccessLogin()
	{Debug.show("LogSetting_isSuccessLogin");
		return successLogin;
	}

	public void setSuccessLogin(boolean successLogin)
	{Debug.show("LogSetting_setSuccessLogin");
		this.successLogin = successLogin;
	}

	public boolean isFailedLogin()
	{Debug.show("LogSetting_isFailedLogin");
		return failedLogin;
	}

	public void setFailedLogin(boolean failedLogin)
	{Debug.show("LogSetting_setFailedLogin");
		this.failedLogin = failedLogin;
	}

	public boolean isLogOff()
	{Debug.show("LogSetting_isLogOff");
		return logOff;
	}

	public void setLogOff(boolean logOff)
	{Debug.show("LogSetting_setLogOff");
		this.logOff = logOff;
	}

	public boolean isAddRecord()
	{Debug.show("LogSetting_isAddRecord");
		return addRecord;
	}

	public void setAddRecord(boolean addRecord)
	{Debug.show("LogSetting_setAddRecord");
		this.addRecord = addRecord;
	}

	public boolean isEditDelRecord()
	{Debug.show("LogSetting_isEditDelRecord");
		return editDelRecord;
	}

	public void setEditDelRecord(boolean editDelRecord)
	{Debug.show("LogSetting_setEditDelRecord");
		this.editDelRecord = editDelRecord;
	}

	public boolean isClearExpiredData()
	{Debug.show("LogSetting_isClearExpiredData");
		return clearExpiredData;
	}

	public void setClearExpiredData(boolean clearExpiredData)
	{Debug.show("LogSetting_setClearExpiredData");
		this.clearExpiredData = clearExpiredData;
	}

	public boolean isSyncData()
	{Debug.show("LogSetting_isSyncData");
		return syncData;
	}

	public void setSyncData(boolean syncData)
	{Debug.show("LogSetting_setSyncData");
		this.syncData = syncData;
	}

	public boolean isManuallyDelLog()
	{Debug.show("LogSetting_isManuallyDelLog");
		return manuallyDelLog;
	}

	public void setManuallyDelLog(boolean manuallyDelLog)
	{Debug.show("LogSetting_setManuallyDelLog");
		this.manuallyDelLog = manuallyDelLog;
	}

	public boolean isChangeSetting()
	{Debug.show("LogSetting_isChangeSetting");
		return changeSetting;
	}

	public void setChangeSetting(boolean changeSetting)
	{Debug.show("LogSetting_setChangeSetting");
		this.changeSetting = changeSetting;
	}

	public boolean isChangeSecuritySetting()
	{Debug.show("LogSetting_isChangeSecuritySetting");
		return changeSecuritySetting;
	}

	public void setChangeSecuritySetting(boolean changeSecuritySetting)
	{Debug.show("LogSetting_setChangeSecuritySetting");
		this.changeSecuritySetting = changeSecuritySetting;
	}

	protected void doLoad()
	{Debug.show("LogSetting_doLoad");
		try
		{
			DataInputStream din = this.getDataInputStream();
			successLogin = din.readBoolean();
			failedLogin = din.readBoolean();
			logOff = din.readBoolean();
			addRecord = din.readBoolean();
			editDelRecord = din.readBoolean();
			clearExpiredData = din.readBoolean();
			syncData = din.readBoolean();
			manuallyDelLog = din.readBoolean();
			changeSetting = din.readBoolean();
			changeSecuritySetting = din.readBoolean();
		} catch (Exception e)
		{
		}Debug.show("LogSetting_doLoadFinish");
	}

	protected void doSave()
	{Debug.show("LogSetting_doSave");
		try
		{
			DataOutputStream dout = this.getDataOutputStream();
			dout.writeBoolean(successLogin);
			dout.writeBoolean(failedLogin);
			dout.writeBoolean(logOff);
			dout.writeBoolean(addRecord);
			dout.writeBoolean(editDelRecord);
			dout.writeBoolean(clearExpiredData);
			dout.writeBoolean(syncData);
			dout.writeBoolean(manuallyDelLog);
			dout.writeBoolean(changeSetting);
			dout.writeBoolean(changeSecuritySetting);
		} catch (Exception e)
		{
		}Debug.show("LogSetting_doSaveFinish");
	}
}
