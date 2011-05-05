package Wsy.MobileAccount.Setting;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Calendar;
import java.util.Date;

public class SecuritySetting extends Setting
{
    private int passwordLockCount;
    private int passwdLockMin;
    private boolean[] slaveUserAllowedDay;
    private boolean[] slaveUserAllowedHour;
    private int primaryUserLoginStatus;
    private int slaveUserLoginStatus;
    private Calendar primaryUserLastLoginDate;
    private Calendar slaveUserLastLoginDate;

    public SecuritySetting()
    {
        super("Setting_Security");
        initDefaultValue();
    }

    private void initDefaultValue()
    {
        passwordLockCount = 5;
        passwdLockMin = 30;
        slaveUserAllowedDay = new boolean[]{ true, true, true, true, true, true, true };
        slaveUserAllowedHour = new boolean[]
        {
            true, true, true, true, true, true, true, true, true, true, true, true,
            true, true, true, true, true, true, true, true, true, true, true, true
        };
        primaryUserLoginStatus = 0;
        slaveUserLoginStatus = 0;
        primaryUserLastLoginDate = Calendar.getInstance();
        slaveUserLastLoginDate = Calendar.getInstance();
    }

    public int getPasswordLockCount()
    {
        return passwordLockCount;
    }

    public void setPasswordLockCount(int passwordLockCount)
    {
        this.passwordLockCount = passwordLockCount;
    }

    public int getPasswdLockMin()
    {
        return passwdLockMin;
    }

    public void setPasswdLockMin(int passwdLockMin)
    {
        this.passwdLockMin = passwdLockMin;
    }

    public boolean[] getSlaveUserAllowedDay()
    {
        return slaveUserAllowedDay;
    }

    public void setSlaveUserAllowedDay(boolean[] slaveUserAllowedDay)
    {
        this.slaveUserAllowedDay = slaveUserAllowedDay;
    }

    public boolean[] getSlaveUserAllowedHour()
    {
        return slaveUserAllowedHour;
    }

    public void setSlaveUserAllowedHour(boolean[] slaveUserAllowedHour)
    {
        this.slaveUserAllowedHour = slaveUserAllowedHour;
    }

    public int getPrimaryUserLoginStatus()
    {
        return primaryUserLoginStatus;
    }

    public void setPrimaryUserLoginStatus(int primaryUserLoginStatus)
    {
        this.primaryUserLoginStatus = primaryUserLoginStatus;
    }

    public int getSlaveUserLoginStatus()
    {
        return slaveUserLoginStatus;
    }

    public void setSlaveUserLoginStatus(int slaveUserLoginStatus)
    {
        this.slaveUserLoginStatus = slaveUserLoginStatus;
    }

    public Calendar getPrimaryUserLastLoginDate()
    {
        return primaryUserLastLoginDate;
    }

    public void setPrimaryUserLastLoginDate(Calendar primaryUserLastLoginDate)
    {
        this.primaryUserLastLoginDate = primaryUserLastLoginDate;
    }

    public Calendar getSlaveUserLastLoginDate()
    {
        return slaveUserLastLoginDate;
    }

    public void setSlaveUserLastLoginDate(Calendar slaveUserLastLoginDate)
    {
        this.slaveUserLastLoginDate = slaveUserLastLoginDate;
    }

    protected void doLoad()
    {
        try
        {
            DataInputStream din = this.getDataInputStream();
            passwordLockCount = din.readInt();
            passwdLockMin = din.readInt();
            slaveUserAllowedDay = new boolean[7];
            for (int i = 0; i < 7; i++)
            {
                slaveUserAllowedDay[i] = din.readBoolean();
            }
            slaveUserAllowedHour = new boolean[24];
            for (int i = 0; i < 24; i++)
            {
                slaveUserAllowedHour[i] = din.readBoolean();
            }
            primaryUserLoginStatus = din.readInt();
            slaveUserLoginStatus = din.readInt();
            primaryUserLastLoginDate.setTime(new Date(din.readLong()));
            slaveUserLastLoginDate.setTime(new Date(din.readLong()));
        } catch (Exception e)
        {
        }
    }

    protected void doSave()
    {
        try
        {
            DataOutputStream dout = this.getDataOutputStream();
            dout.writeInt(passwordLockCount);
            dout.writeInt(passwdLockMin);
            for (int i = 0; i < 7; i++)
            {
                dout.writeBoolean(slaveUserAllowedDay[i]);
            }
            for (int i = 0; i < 24; i++)
            {
                dout.writeBoolean(slaveUserAllowedHour[i]);
            }
            dout.writeInt(primaryUserLoginStatus);
            dout.writeInt(slaveUserLoginStatus);
            dout.writeLong(primaryUserLastLoginDate.getTime().getTime());
            dout.writeLong(slaveUserLastLoginDate.getTime().getTime());
        } catch (Exception e)
        {
        }
    }
}
