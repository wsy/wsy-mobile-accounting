package Wsy.MobileAccount.Setting;

/**
 * 
 * @author WSY
 */
import Wsy.MobileAccount.Debug;
public final class SettingManager
{
    private static LoginSetting loginSetting;
    private static SecuritySetting securitySetting;
    private static StorageSetting storageSetting;
    private static LogSetting logSetting;
    private static ViewSetting viewSetting;
    private static SyncSetting syncSetting;
    private static RecordSetting recordSetting;

    private SettingManager()
    {

    }

    public static void StaticInitializer()
    {Debug.show("SettingManager_StaticInitializer");
        loginSetting = new LoginSetting();
        securitySetting = new SecuritySetting();
        storageSetting = new StorageSetting();
        logSetting = new LogSetting();
        viewSetting = new ViewSetting();
        syncSetting = new SyncSetting();
        recordSetting = new RecordSetting();Debug.show("SettingManager_StaticInitializerFinished");
    }

    public static LoginSetting getLoginSetting()
    {
        return SettingManager.loginSetting;
    }

    public static SecuritySetting getSecuritySetting()
    {
        return SettingManager.securitySetting;
    }

    public static StorageSetting getStorageSetting()
    {
        return SettingManager.storageSetting;
    }

    public static LogSetting getLogSetting()
    {
        return SettingManager.logSetting;
    }

    public static ViewSetting getViewSetting()
    {
        return SettingManager.viewSetting;
    }

    public static SyncSetting getSyncSetting()
    {
        return SettingManager.syncSetting;
    }

    public static RecordSetting getRecordSetting()
    {
        return SettingManager.recordSetting;
    }

    public static void LoadRMSConfig() throws Exception
    {Debug.show("SettingManager_LoadRMSConfig");
        loginSetting.load();
        securitySetting.load();
        storageSetting.load();
        viewSetting.load();
        syncSetting.load();
        recordSetting.load();
        logSetting.load();Debug.show("SettingManager_LoadRMSConfigFinished");
    }

    public static void SaveRMSConfig()
    {
        loginSetting.save();
        securitySetting.save();
        storageSetting.save();
        viewSetting.save();
        syncSetting.save();
        recordSetting.save();
        logSetting.save();
    }
}
