/*
 * To change this template, choose Tools | Templates
 * and load the template in the editor.
 */

package Wsy.MobileAccount.Setting;

/**
 *
 * @author WSY
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class LoginSetting extends Setting
{
    private boolean requirePassword;
    private boolean slaveUserEnabled;
    private String primaryUserName;
    private String primaryUserPassword;
    private String slaveUserName;
    private String slaveUserPassword;
    private String superPassword;

    public LoginSetting()
    {
        super("Setting_Login");System.out.println("Debug_LoginSetting28");
        initDefaultValue();System.out.println("Debug_LoginSetting29");
    }

    private void initDefaultValue()
    {System.out.println("Debug_LoginSetting33");
        requirePassword = false;
        slaveUserEnabled = true;
        primaryUserName = "User";
        primaryUserPassword = "User";
        slaveUserName = "Guest";
        slaveUserPassword = "Guest";
        superPassword = "Super";System.out.println("Debug_LoginSetting40");
    }

    public boolean isRequirePassword()
    {System.out.println("Debug_LoginSetting44");
        return requirePassword;
    }

    public void setRequirePassword(boolean requirePassword)
    {
        this.requirePassword = requirePassword;
    }

    public boolean isSlaveUserEnabled()
    {
        return slaveUserEnabled;
    }

    public void setSlaveUserEnabled(boolean slaveUserEnabled)
    {
        this.slaveUserEnabled = slaveUserEnabled;
    }

    public String getPrimaryUserName()
    {
        return primaryUserName;
    }

    public void setPrimaryUserName(String primaryUserName)
    {
        this.primaryUserName = primaryUserName;
    }

    public String getPrimaryUserPassword()
    {
        return primaryUserPassword;
    }

    public void setPrimaryUserPassword(String primaryUserPassword)
    {
        this.primaryUserPassword = primaryUserPassword;
    }

    public String getSlaveUserName()
    {
        return slaveUserName;
    }

    public void setSlaveUserName(String slaveUserName)
    {
        this.slaveUserName = slaveUserName;
    }

    public String getSlaveUserPassword()
    {
        return slaveUserPassword;
    }

    public void setSlaveUserPassword(String slaveUserPassword)
    {
        this.slaveUserPassword = slaveUserPassword;
    }

    public String getSuperPassword()
    {
        return superPassword;
    }

    public void setSuperPassword(String superPassword)
    {
        this.superPassword = superPassword;
    }

    protected void doLoad()
    {
        try
        {
            DataInputStream din = this.getDataInputStream();
            requirePassword = din.readBoolean();
            slaveUserEnabled = din.readBoolean();
            primaryUserName = din.readUTF();
            primaryUserPassword = din.readUTF();
            slaveUserName = din.readUTF();
            slaveUserPassword = din.readUTF();
            superPassword = din.readUTF();
        } catch (Exception e)
        {
        }
    }

    protected void doSave()
    {
        try
        {
            DataOutputStream dout = this.getDataOutputStream();
            dout.writeBoolean(requirePassword);
            dout.writeBoolean(slaveUserEnabled);
            dout.writeUTF(primaryUserName);
            dout.writeUTF(primaryUserPassword);
            dout.writeUTF(slaveUserName);
            dout.writeUTF(slaveUserPassword);
            dout.writeUTF(superPassword);
        } catch (Exception e)
        {
        }
    }

}
