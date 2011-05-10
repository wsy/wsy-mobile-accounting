/*
 * To change this template, choose Tools | Templates
 * and load the template in the editor.
 */

package Wsy.MobileAccount.Setting;

/**
 *
 * @author WSY
 */
import Wsy.MobileAccount.Debug;
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
        super("Setting_Login");Debug.show("LoginSetting_Constructor");
        initDefaultValue();
    }

    private void initDefaultValue()
    {Debug.show("LoginSetting_initDefaultValue");
        requirePassword = false;
        slaveUserEnabled = true;
        primaryUserName = "User";
        primaryUserPassword = "User";
        slaveUserName = "Guest";
        slaveUserPassword = "Guest";
        superPassword = "Super";Debug.show("LoginSetting_initDefaultValueFinished");
    }

    public boolean isRequirePassword()
    {Debug.show("Debug_LoginSettingIsRequirePassword");
        return requirePassword;
    }

    public void setRequirePassword(boolean requirePassword)
    {Debug.show("Debug_LoginSettingSetRequirePassword");
        this.requirePassword = requirePassword;
    }

    public boolean isSlaveUserEnabled()
    {Debug.show("Debug_IsSlaveUserEnabled");
        return slaveUserEnabled;
    }

    public void setSlaveUserEnabled(boolean slaveUserEnabled)
    {Debug.show("Debug_LoginSettingSetSlaveUserEnabled");
        this.slaveUserEnabled = slaveUserEnabled;
    }

    public String getPrimaryUserName()
    {Debug.show("Debug_LoginSettingGetPrimaryUserName");
        return primaryUserName;
    }

    public void setPrimaryUserName(String primaryUserName)
    {Debug.show("Debug_LoginSettingSetPrimaryUserName");
        this.primaryUserName = primaryUserName;
    }

    public String getPrimaryUserPassword()
    {Debug.show("Debug_GetPrimaryUserPassword");
        return primaryUserPassword;
    }

    public void setPrimaryUserPassword(String primaryUserPassword)
    {Debug.show("Debug_SetPrimaryUserPassword");
        this.primaryUserPassword = primaryUserPassword;
    }

    public String getSlaveUserName()
    {Debug.show("Debug_GetSlaveUserName");
        return slaveUserName;
    }

    public void setSlaveUserName(String slaveUserName)
    {Debug.show("Debug_SetSlaveUserName");
        this.slaveUserName = slaveUserName;
    }

    public String getSlaveUserPassword()
    {Debug.show("Debug_GetSlaveUserPassword");
        return slaveUserPassword;
    }

    public void setSlaveUserPassword(String slaveUserPassword)
    {Debug.show("Debug_SetSlaveUserPassword");
        this.slaveUserPassword = slaveUserPassword;
    }

    public String getSuperPassword()
    {Debug.show("Debug_GetSuperPassword");
        return superPassword;
    }

    public void setSuperPassword(String superPassword)
    {Debug.show("Debug_SetSuperPassword");
        this.superPassword = superPassword;
    }

    protected void doLoad()
    {Debug.show("Debug_DoLoad");
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
