/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Wsy.MobileAccount.Setting;

/**
 *
 * @author WSY
 */
public final class Enum_LogFilter
{

    private Enum_LogFilter()
    {
    }
    public static final int SuccessLogin = 0x1;
    public static final int FailedLogin = 0x2;        //Default
    public static final int LogOff = 0x4;
    public static final int AddRecord = 0x8;
    public static final int EditDelRecord = 0x10;               //Default
    public static final int ClearExpiredData = 0x20;            //Default
    public static final int SyncData = 0x40;
    public static final int ManuallyDelLog = 0x80;    //Default
    public static final int ChangeSetting = 0x100;
    public static final int ChangeSecuritySetting = 0x200;      //Default
    public static final int DefaultFilter = 0x2B2;
}
