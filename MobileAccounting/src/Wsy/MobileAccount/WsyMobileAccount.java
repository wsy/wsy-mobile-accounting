/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Wsy.MobileAccount;

import Wsy.MobileAccount.Setting.*;
import java.util.Calendar;
import java.util.Vector;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;
import org.netbeans.microedition.lcdui.SplashScreen;
import org.netbeans.microedition.lcdui.LoginScreen;

/**
 * @author WSY
 */

public class WsyMobileAccount extends MIDlet implements CommandListener
{
    // <editor-fold defaultstate="collapsed" desc=" public_Screens ">
    private SplashScreen Screen_Splash;
    private LoginScreen Screen_Login;
    private FormListView Screen_Main;
    private List Screen_Menu;
    private formRecord Screen_Edit;
    private List Screen_MainSetting;
    private Form[] Screen_Setting;
    private List Screen_QuickInput;
    private JumpToDate Screen_JumpToDate;
    private Alert Screen_Alert;
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" Static_Commands ">
    public static final Command Command_ForgetPasswd = new Command("忘记密码",Command.OK, 1);
    public static final Command Command_ShowMenu = new Command("选项",Command.ITEM, 0);
    public static final Command Command_Save = new Command("保存", Command.OK, 0);
    public static final Command Command_Cancel = new Command("取消",Command.CANCEL, 2);
    public static final Command Command_Exit = new Command("Exit",Command.EXIT, 1);
    // </editor-fold>
    Item[][] Item_Setting;

    public WsyMobileAccount()
    {
        Initialize_Screen_Splash();
    }
    // <editor-fold defaultstate="collapsed" desc=" GeneratedMethod ">

    public void startApp()
    {
        switchDisplayable(Screen_Splash);
        Initialize();
    }
    public void pauseApp(){}
    public void destroyApp(boolean unconditional){notifyDestroyed();}
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" PublicMethod ">
    public static String CTS(Calendar Day)
    {
        return Day.get(Calendar.YEAR) + "" + (Day.get(Calendar.MONTH) + 1)+ Day.get(Calendar.DAY_OF_MONTH);
    }
//    public static long CTL(Calendar Day)
//    {
//        return Day.get(Calendar.YEAR) * 10000 + (Day.get(Calendar.MONTH) + 1)* 100 + Day.get(Calendar.DAY_OF_MONTH);
//    }
//    public void switchDisplayable(Alert alert, Displayable NextDisplayable)
//    {
//        Display.getDisplay(this).setCurrent(alert, NextDisplayable);
//    }
    public void switchDisplayable(Displayable NextDisplayable)
    {
        Display.getDisplay(this).setCurrent(NextDisplayable);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" Initialize ">
    public void Initialize()
    {
        Initialize_SettingManager();
        Initialize_LoadConfig();
        Initialize_Screen_Login();
        Initialize_Screen_Main();
        Initialize_Screen_Menu();
        Initialize_Screen_Edit();
        Initialize_StringVaribles();
        Initialize_Screen_Setting();
        Initialize_Screen_Input();
        Initialize_Screen_JumpToDate();
        Initialize_Screen_Alert();
    }
    private void Initialize_StringVaribles()
    {
        String[] String_HoursOfDay = new String[]
        {
            "0点", "1点", "2点", "3点", "4点", "5点", "6点", "7点", "8点", "9点", "10点","11点","12点",
            "13点", "14点", "15点", "16点", "17点", "18点", "19点","20点", "21点", "22点", "23点"
        };
        String[] String_DaysOfWeek = new String[]
        {
            "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"
        };
        String[] String_LogType = new String[]
        {
            "成功的登陆", "失败的登录", "注销", "添加记录", "删改记录", "计划中的清理过期数据", "同步数据",
            "手动删除日志", "更改普通设置", "更改安全设置"
        };
        String[] String_Warning = new String[]{"未知错误！", "用户名或密码错误！", "账户已被锁定！请稍后再试！"};
        Item_Setting = new Item[][]
        {
            new Item[]
            {
                new ChoiceGroup("程序启动时验证密码", ChoiceGroup.POPUP,new String[]{"是", "否"}, null),
                new ChoiceGroup("启用用户2", ChoiceGroup.POPUP, new String[]{"是", "否"}, null),
                new TextField("用户1名称", SettingManager.getLoginSetting().getPrimaryUserName(), 20,TextField.ANY),
                new TextField("用户1密码", SettingManager.getLoginSetting().getPrimaryUserPassword(), 16,TextField.PASSWORD),
                new TextField("用户2密码", SettingManager.getLoginSetting().getSlaveUserPassword(), 16,TextField.PASSWORD),
                new TextField("超级用户密码", SettingManager.getLoginSetting().getSuperPassword(), 25,TextField.PASSWORD)
            },
            new Item[]
            {
                new TextField("账户锁定计数",String.valueOf(SettingManager.getSecuritySetting().getPasswordLockCount()),2, TextField.NUMERIC),
                new TextField("账户锁定时间（分钟）",String.valueOf(SettingManager.getSecuritySetting().getPasswdLockMin()),4, TextField.NUMERIC),
                new ChoiceGroup("用户2允许登陆的日期", ChoiceGroup.MULTIPLE,String_DaysOfWeek, null),
                new ChoiceGroup("用户2允许登陆的时间", ChoiceGroup.MULTIPLE,String_HoursOfDay, null),
                new ChoiceGroup("重置用户1的账户锁", ChoiceGroup.POPUP,new String[]{"点击以确定", "确定"}, null),
                new ChoiceGroup("重置用户2的账户锁", ChoiceGroup.POPUP,new String[]{"点击以确定", "确定"}, null)
            },
            new Item[]
            {
                new TextField("最长记录保存时间",String.valueOf(SettingManager.getStorageSetting().getMaxRecordDay()), 3,TextField.NUMERIC),
                new TextField("最长日志保存时间",String.valueOf(SettingManager.getStorageSetting().getMaxLogDay()), 3,TextField.NUMERIC),
                new ChoiceGroup("强制保留未同步记录/日志", ChoiceGroup.POPUP, new String[]{"是", "否"}, null)
            },
            new Item[]
            {
                new ChoiceGroup("默认主界面视图", ChoiceGroup.POPUP,new String[]{"列表视图", "天视图", "周视图", "月视图"}, null),
                new ChoiceGroup("默认日志筛选器", ChoiceGroup.MULTIPLE,String_LogType, null)
            },
            new Item[]
            {
                new TextField("服务器地址", SettingManager.getSyncSetting().getServerAddress(), 256,TextField.ANY),
                new TextField("同步频率（天）",String.valueOf(SettingManager.getSyncSetting().getSyncFreq()), 2,TextField.NUMERIC),
                new ChoiceGroup("同步类型",ChoiceGroup.POPUP,new String[]{"仅手机到电脑", "手机电脑双向同步"}, null)
            },
            new Item[]
            {
                new ChoiceGroup("日志记录选项", ChoiceGroup.MULTIPLE,String_LogType, null)
            }
        };
    }
    private void Initialize_Screen_Splash()
    {
        try
        {
            Screen_Splash = new SplashScreen(Display.getDisplay(this));
            Screen_Splash.setTitle("splashScreen");
            Screen_Splash.setCommandListener(this);
            Screen_Splash.setFullScreenMode(true);
            Screen_Splash.setTimeout(3072);
            Screen_Splash.addCommand(SplashScreen.DISMISS_COMMAND);
            Screen_Splash.setImage(Image.createImage("/Splash.jpg"));
        }
        catch (Exception e)
        {
            System.out.println(e.toString()); // debug Options;
        }
    }
    private void Initialize_SettingManager()
    {
        SettingManager.StaticInitializer();
    }
    private void Initialize_LoadConfig()
    {
        try
        {
            SettingManager.LoadRMSConfig();
        }
        catch (Exception e)
        {
            Initialize_LoadConfig_FirstRun();
        }
        Record.ReadRMS();
    }
    private void Initialize_LoadConfig_FirstRun()
    {
        System.out.println("Debug,Save");
        SettingManager.SaveRMSConfig(); //Debug Options
        try
        {
        System.out.println("Debug,LoadAgain");
        SettingManager.LoadRMSConfig();
        }
        catch (Exception e2)
        {
        }
    }
    private void Initialize_Screen_Login()
    {
        if (SettingManager.getLoginSetting().isRequirePassword())
        {
            try
            {
                Screen_Login = new LoginScreen(Display.getDisplay(this));
                Screen_Login.setLabelTexts("\u7528\u6237\u540D", "\u53E3\u4EE4");
                Screen_Login.setTitle("\u767B\u5F55");
                Screen_Login.setCommandListener(this);
                Screen_Login.setBGColor(-3355444);
                Screen_Login.setFGColor(0);
                Screen_Login.setUseLoginButton(true);
                Screen_Login.setLoginButtonText("\u767B\u5F55");
                Screen_Login.addCommand(LoginScreen.LOGIN_COMMAND);
                Screen_Login.addCommand(Command_ForgetPasswd);
                Screen_Login.addCommand(Command_Exit);
            }
            catch (Exception e)
            {
            }
        }
    }
    private void Initialize_Screen_Main()
    {
        Screen_Main = new FormListView();
        Screen_Main.addCommand(Command_ShowMenu);
        Screen_Main.addCommand(Command_Exit);
        Screen_Main.setCommandListener(this);
    }
    private void Initialize_Screen_Menu()
    {
        String[] SettingTitles=new String[]{"新纪录","当前纪录", "清理纪录", "跳至指定日期", "日志", "设置"};
         Screen_Menu = new List("菜单", List.IMPLICIT, SettingTitles, null);
         Screen_Menu.addCommand(Command_Cancel);
         Screen_Menu.setCommandListener(this);
    }
    private void Initialize_Screen_Edit()
    {
        Screen_Edit = new formRecord();
        Screen_Edit.setCommandListener(this);
    }
    private void Initialize_Screen_Setting()
    {
         String[] String_Setting = new String[]{"快速记录", "登陆选项", "安全选项",
         "存储选项", "视图选项", "同步选项", "日志记录选项"};
         Screen_MainSetting = new List("更改设置", List.IMPLICIT, String_Setting,
         null);
         Screen_MainSetting.addCommand(Command_Cancel);
         Screen_MainSetting.setCommandListener(this);
         Screen_Setting = new Form[String_Setting.length];
         Initialize_Screen_Setting_SetDefaultValue();
         for (int i = 0; i < Item_Setting.length; i++) //AskCharlie
         {
         Screen_Setting[i] = new Form(String_Setting[i]);
         for (int j = 0; j < Item_Setting[i].length; j++)
         {
         Screen_Setting[i].append(Item_Setting[i][j]);
         }
         Screen_Setting[i].addCommand(Command_Save);
         Screen_Setting[i].addCommand(Command_Cancel);
         Screen_Setting[i].setCommandListener(this);
         }
    }
    private void Initialize_Screen_Setting_SetDefaultValue()
    {
        ((ChoiceGroup) Item_Setting[0][0]).setSelectedFlags(new boolean[]
        {
            SettingManager.getLoginSetting().isRequirePassword() == true,
            SettingManager.getLoginSetting().isRequirePassword() == false
        });
        ((ChoiceGroup) Item_Setting[0][1]).setSelectedFlags(new boolean[]
        {
            SettingManager.getLoginSetting().isSlaveUserEnabled() == true,
            SettingManager.getLoginSetting().isSlaveUserEnabled() == false
        });
        ((ChoiceGroup) Item_Setting[1][2]).setSelectedFlags(SettingManager.getSecuritySetting().getSlaveUserAllowedDay());
        ((ChoiceGroup) Item_Setting[1][3]).setSelectedFlags(SettingManager.getSecuritySetting().getSlaveUserAllowedHour());
        ((ChoiceGroup) Item_Setting[2][2]).setSelectedFlags(new boolean[]
        {
            SettingManager.getStorageSetting().isForceUnsyncedData() == true,
            SettingManager.getStorageSetting().isForceUnsyncedData() == false
        });
        ((ChoiceGroup) Item_Setting[3][0]).setSelectedFlags(new boolean[]
        {
            SettingManager.getViewSetting().getDefaultView() == Enum_FormView.List,
            SettingManager.getViewSetting().getDefaultView() == Enum_FormView.Daily,
            SettingManager.getViewSetting().getDefaultView() == Enum_FormView.Weekly,
            SettingManager.getViewSetting().getDefaultView() == Enum_FormView.Monthly
        });
        ((ChoiceGroup) Item_Setting[3][1]).setSelectedFlags(new boolean[]
        {
            (SettingManager.getViewSetting().getDefaultFilter() & Enum_LogFilter.SuccessLogin) != 0,
            (SettingManager.getViewSetting().getDefaultFilter() & Enum_LogFilter.FailedLogin) != 0,
            (SettingManager.getViewSetting().getDefaultFilter() & Enum_LogFilter.LogOff) != 0,
            (SettingManager.getViewSetting().getDefaultFilter() & Enum_LogFilter.AddRecord) != 0,
            (SettingManager.getViewSetting().getDefaultFilter() & Enum_LogFilter.EditDelRecord) != 0,
            (SettingManager.getViewSetting().getDefaultFilter() & Enum_LogFilter.ClearExpiredData) != 0,
            (SettingManager.getViewSetting().getDefaultFilter() & Enum_LogFilter.SyncData) != 0,
            (SettingManager.getViewSetting().getDefaultFilter() & Enum_LogFilter.ManuallyDelLog) != 0,
            (SettingManager.getViewSetting().getDefaultFilter() & Enum_LogFilter.ChangeSetting) != 0,
            (SettingManager.getViewSetting().getDefaultFilter() & Enum_LogFilter.ChangeSecuritySetting) != 0
        });
        ((ChoiceGroup) Item_Setting[4][2]).setSelectedFlags(new boolean[]
        {
            SettingManager.getSyncSetting().getSyncType() == Enum_SyncType.ToComputerOnly,
            SettingManager.getSyncSetting().getSyncType() == Enum_SyncType.DoubleWaySync
        });
        ((ChoiceGroup) Item_Setting[5][0]).setSelectedFlags(new boolean[]
        {
            SettingManager.getLogSetting().isSuccessLogin(), SettingManager.getLogSetting().isFailedLogin(),
            SettingManager.getLogSetting().isLogOff(), SettingManager.getLogSetting().isAddRecord(),
            SettingManager.getLogSetting().isEditDelRecord(), SettingManager.getLogSetting().isClearExpiredData(),
            SettingManager.getLogSetting().isSyncData(), SettingManager.getLogSetting().isManuallyDelLog(),
            SettingManager.getLogSetting().isChangeSetting(), SettingManager.getLogSetting().isChangeSecuritySetting()
        });
    }
    private void Initialize_Screen_Input()
    {
         Screen_QuickInput = new List("快速录入", List.IMPLICIT);
         for (int i = 0; i < SettingManager.getRecordSetting().getFastRecordNum(); i++)
         {
             Screen_QuickInput.append(SettingManager.getRecordSetting().getFastRecord()[i].Item, null);
         }
         Screen_QuickInput.removeCommand(List.SELECT_COMMAND);
         Screen_QuickInput.addCommand(Command_Save);
         Screen_QuickInput.setCommandListener(this);
         Screen_QuickInput.addCommand(Command_Cancel);
    }
    private void Initialize_Screen_JumpToDate()
    {
         Screen_JumpToDate = new JumpToDate();
         Screen_JumpToDate.addCommand(Command_Cancel);
         Screen_JumpToDate.setCommandListener(this);
    }
    private void Initialize_Screen_Alert()
    {
        // Screen_Alert = new Alert("Mobile Account", "", null,
        // AlertType.ERROR);
        // Screen_Alert.addCommand(new Command("确认", Command.OK, 0));
        // Screen_Alert.setCommandListener(this);
        // Screen_Alert.setTimeout(Alert.FOREVER);
    }
    // </editor-fold>
    public void commandAction(Command command,Displayable displayable)
    {
        // <editor-fold defaultstate="collapsed" desc=" ExitCommand ">
        if (command == WsyMobileAccount.Command_Exit)
        {
            switchDisplayable(null);
            try
            {
                this.destroyApp(false);
            }
            catch (Exception e)
            {
            }
            // notifyDestroyed();
        }
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc=" Screen_Splash ">
        if (displayable == this.Screen_Splash)
        {
            if (command == SplashScreen.DISMISS_COMMAND)
            {
                if (SettingManager.getLoginSetting().isRequirePassword())
                {
                    switchDisplayable(this.Screen_Login);
                } else
                {
                    switchDisplayable(this.Screen_Main);
                }
            }
        }
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc=" Screen_Login ">
//        else if (displayable == this.Screen_Login)
//        {
//            if (command == LoginScreen.LOGIN_COMMAND)
//            {
//                int LoginResult = Authorization.VerifyLogin(
//                        this.Screen_Login.getUsername(),
//                        this.Screen_Login.getPassword());
//                if (LoginResult <= Authorization.Author_Failed)
//                {
//                    String AlertText = "登录失败！"
//                            + this.String_Warning[-LoginResult];
//                    this.Screen_Alert.setString(AlertText);
//                    switchDisplayable(this.Screen_Alert, this.Screen_Login);
//                } else
//                {
//                    switchDisplayable(this.Screen_Main);
//                }
//            }
//        }
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc=" Screen_Menu ">
        else if (displayable == this.Screen_Menu)
        {
            if (command == List.SELECT_COMMAND)
            {
                switch (this.Screen_Menu.getSelectedIndex())
                {
                case 0: // 新记录
                    this.Screen_Edit.SetRecord(null);
                    switchDisplayable(this.Screen_Edit);
                    break;
                case 1: // 当前记录
                    break;
                case 2: // 清理记录
                    break;
                case 3: // 跳至指定日期
                    switchDisplayable(this.Screen_JumpToDate);
                    break;
                case 4: // 日志
                    break;
                case 5: // 设置
                    switchDisplayable(this.Screen_MainSetting);
                    break;
                case 6: // 帮助
                    break;
                default:
                    switchDisplayable(this.Screen_Main);
                    break;
                }
                // Handle Menu
            }
            else if (command == WsyMobileAccount.Command_Cancel)
            {
                switchDisplayable(this.Screen_Main);
            }
        }
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc=" Screen_Main ">
        else if (displayable == this.Screen_Main)
        {
            if (command == WsyMobileAccount.Command_ShowMenu)
            {
                switchDisplayable(this.Screen_Menu);
            }
        }
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc=" Screen_Setting ">
        else if (displayable == this.Screen_MainSetting)
        {
            if (command == WsyMobileAccount.Command_Cancel)
            {
                switchDisplayable(this.Screen_Menu);
            } else if (command == List.SELECT_COMMAND)
            {
                int i = this.Screen_MainSetting.getSelectedIndex();
                switch (i)
                {
                    case 0:
                        // Handle QuickRecord SettingManager
                        break;
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                        switchDisplayable(this.Screen_Setting[i - 1]);
                        break;
                }
            }
        }
        else if (displayable == this.Screen_Setting[0]
                || displayable == this.Screen_Setting[1]
                || displayable == this.Screen_Setting[2]
                || displayable == this.Screen_Setting[3]
                || displayable == this.Screen_Setting[4]
                || displayable == this.Screen_Setting[5])
        {
            if (command == WsyMobileAccount.Command_Save)
            {
                switch (this.Screen_MainSetting.getSelectedIndex() - 1)
                {
                    case 0:
                        SettingManager.getLoginSetting().setRequirePassword(((ChoiceGroup) this.Item_Setting[0][0]).getSelectedIndex() == 0);
                        SettingManager.getLoginSetting().setSlaveUserEnabled(((ChoiceGroup) this.Item_Setting[0][1]).getSelectedIndex() == 0);
                        SettingManager.getLoginSetting().setPrimaryUserName(((TextField) this.Item_Setting[0][2]).getString());
                        SettingManager.getLoginSetting().setPrimaryUserPassword(((TextField) this.Item_Setting[0][3]).getString());
                        SettingManager.getLoginSetting().setSlaveUserPassword(((TextField) this.Item_Setting[0][4]).getString());
                        SettingManager.getLoginSetting().setSuperPassword(((TextField) this.Item_Setting[0][5]).getString());
                        break;
                    case 1:
                        SettingManager.getSecuritySetting().setPasswordLockCount(Integer.parseInt(((TextField) this.Item_Setting[1][0]).getString()));
                        SettingManager.getSecuritySetting().setPasswdLockMin(Integer.parseInt(((TextField) this.Item_Setting[1][1]).getString()));
                        ((ChoiceGroup) this.Item_Setting[1][2]).getSelectedFlags(SettingManager.getSecuritySetting().getSlaveUserAllowedDay());
                        ((ChoiceGroup) this.Item_Setting[1][3]).getSelectedFlags(SettingManager.getSecuritySetting().getSlaveUserAllowedHour());
                    // "重置用户1的账户锁"
                    // "重置用户2的账户锁"
                        break;
                    case 2:
                        SettingManager.getStorageSetting().setMaxRecordDay(Integer.parseInt(((TextField) this.Item_Setting[2][0]).getString()));
                        SettingManager.getStorageSetting().setMaxLogDay(Integer.parseInt(((TextField) this.Item_Setting[2][1]).getString()));
                        SettingManager.getStorageSetting().setForceUnsyncedData((((ChoiceGroup) this.Item_Setting[2][2]).getSelectedIndex() == 0));
                        break;
                    case 3:
                        boolean Temp_bool[] = new boolean[10];
                        int Temp_int = 1;
                        SettingManager.getViewSetting().setDefaultView(((ChoiceGroup) this.Item_Setting[3][0]).getSelectedIndex());
                        ((ChoiceGroup) this.Item_Setting[3][1]).getSelectedFlags(Temp_bool);
                        SettingManager.getViewSetting().setDefaultFilter(0);
                        for (int i = 0; i < Temp_bool.length; i++)
                        {
                            if (Temp_bool[i] == true)
                            {
                                SettingManager.getViewSetting().setDefaultFilter
                                (
                                    SettingManager.getViewSetting().getDefaultFilter() | Temp_int
                                );
                            }
                            Temp_int *= 2;
                        }
                        break;
                    case 4:
                        SettingManager.getSyncSetting().setServerAddress(((TextField) this.Item_Setting[4][0]).getString());
                        SettingManager.getSyncSetting().setSyncFreq(Integer.parseInt(((TextField) this.Item_Setting[4][1]).getString()));
                        SettingManager.getSyncSetting().setSyncType(((ChoiceGroup) this.Item_Setting[4][2]).getSelectedIndex());
                        break;
                    case 5:
                        boolean[] res = new boolean[10];
                        ((ChoiceGroup) this.Item_Setting[5][0]).getSelectedFlags(res);
                        SettingManager.getLogSetting().setSuccessLogin(res[0]);
                        SettingManager.getLogSetting().setFailedLogin(res[1]);
                        SettingManager.getLogSetting().setLogOff(res[2]);
                        SettingManager.getLogSetting().setAddRecord(res[3]);
                        SettingManager.getLogSetting().setEditDelRecord(res[4]);
                        SettingManager.getLogSetting().setClearExpiredData(res[5]);
                        SettingManager.getLogSetting().setSyncData(res[6]);
                        SettingManager.getLogSetting().setManuallyDelLog(res[7]);
                        SettingManager.getLogSetting().setChangeSetting(res[8]);
                        SettingManager.getLogSetting().setChangeSecuritySetting(res[9]);
                        break;
                }
                SettingManager.SaveRMSConfig();
                switchDisplayable(this.Screen_MainSetting);
            }
            else if (command == WsyMobileAccount.Command_Cancel)
            {
                switchDisplayable(this.Screen_MainSetting);
            }
        }
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc=" Screen_Edit ">
        else if (displayable == this.Screen_Edit)
        {
            if (command == formRecord.Command_QuickInput)
            {
                switchDisplayable(this.Screen_QuickInput);
            } else if (command == WsyMobileAccount.Command_Save)
            {
                this.Screen_Edit.SaveRecord();
                switchDisplayable(this.Screen_Main);
            } else if (command == WsyMobileAccount.Command_Cancel)
            {
                switchDisplayable(this.Screen_Main);
            }
        }
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc=" Screen_JumpToDate ">
        else if (displayable == this.Screen_JumpToDate)
        {
            if (command == JumpToDate.Command_Jump)
            {
                Calendar day = Calendar.getInstance();
                day.setTime(this.Screen_JumpToDate.Field_Date.getDate());
                // Screen_Main.ChangeDate(day);
            }
            switchDisplayable(this.Screen_Main);
        }
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc=" Command_QuickInput ">
        else if (displayable == this.Screen_QuickInput)
        {
            if (command == WsyMobileAccount.Command_Save)
            {
                this.Screen_Edit.SetRecord(SettingManager.getRecordSetting().getFastRecord()[this.Screen_QuickInput.getSelectedIndex()]);
                switchDisplayable(this.Screen_Edit);
            } else if (command == WsyMobileAccount.Command_Cancel)
            {
                switchDisplayable(this.Screen_Edit);
            }
        }
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc=" Alert ">
        else if (displayable == this.Screen_Alert)
        {
            if (this.Screen_Alert.getString().startsWith("登录失败！"))
            {
                switchDisplayable(this.Screen_Login);
            }
        }
        // </editor-fold>
    }
}
