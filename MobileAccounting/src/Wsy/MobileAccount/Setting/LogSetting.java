package Wsy.MobileAccount.Setting;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class LogSetting extends Setting
{
	public boolean isSuccessLogin()
	{
		return successLogin;
	}

	public void setSuccessLogin(boolean successLogin)
	{
		this.successLogin = successLogin;
	}

	public boolean isFailedLogin()
	{
		return failedLogin;
	}

	public void setFailedLogin(boolean failedLogin)
	{
		this.failedLogin = failedLogin;
	}

	public boolean isLogOff()
	{
		return logOff;
	}

	public void setLogOff(boolean logOff)
	{
		this.logOff = logOff;
	}

	public boolean isAddRecord()
	{
		return addRecord;
	}

	public void setAddRecord(boolean addRecord)
	{
		this.addRecord = addRecord;
	}

	public boolean isEditDelRecord()
	{
		return editDelRecord;
	}

	public void setEditDelRecord(boolean editDelRecord)
	{
		this.editDelRecord = editDelRecord;
	}

	public boolean isClearExpiredData()
	{
		return clearExpiredData;
	}

	public void setClearExpiredData(boolean clearExpiredData)
	{
		this.clearExpiredData = clearExpiredData;
	}

	public boolean isSyncData()
	{
		return syncData;
	}

	public void setSyncData(boolean syncData)
	{
		this.syncData = syncData;
	}

	public boolean isManuallyDelLog()
	{
		return manuallyDelLog;
	}

	public void setManuallyDelLog(boolean manuallyDelLog)
	{
		this.manuallyDelLog = manuallyDelLog;
	}

	public boolean isChangeSetting()
	{
		return changeSetting;
	}

	public void setChangeSetting(boolean changeSetting)
	{
		this.changeSetting = changeSetting;
	}

	public boolean isChangeSecuritySetting()
	{
		return changeSecuritySetting;
	}

	public void setChangeSecuritySetting(boolean changeSecuritySetting)
	{
		this.changeSecuritySetting = changeSecuritySetting;
	}

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
		super("Setting_Log");
		initDefaultValue();
	}

	private void initDefaultValue()
	{
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
	
	protected void doLoad()
	{
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
		}
	}

	protected void doSave()
	{
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
		}
	}
}
