package Wsy.MobileAccount;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//import Wsy.MobileAccount.Setting.SettingManager;
import Wsy.MobileAccount.Setting.SettingManager;
import java.util.Calendar;
import javax.microedition.lcdui.*;

/**
 *
 * @author WSY
 */
public class formRecord extends Form// implements ItemStateListener,ItemCommandListener
{
    //<editor-fold defaultstate="collapsed" desc=" Varibles ">
    /**
     * Static Values
     */
    public static final Command Command_Save=new Command("保存",Command.OK,0);
    public static final Command Command_QuickInput=new Command("快速记录",Command.OK,0);
    /**
     * Varibles
     */
    private Record Data_Record;
    private TextField Item_ID;
    private DateField Item_DateTime;
    private TextField Item_Item;
    private ChoiceGroup Item_TypeSrc;
    private ChoiceGroup Item_TypeDst;
    private TextField Item_Money;
    private TextField Item_PostScript;
    //</editor-fold>

    public formRecord()
    {
        /*
         * Add Items to Form
         */
        super("");
        //<editor-fold defaultstate="collapsed" desc=" InitializeCompoment ">
        Data_Record=null;
        Item_ID=new TextField("记录ID", String.valueOf(Record.Record_NewID), 50, TextField.UNEDITABLE);
        Item_DateTime=new DateField("日期",DateField.DATE_TIME);
        Item_Item=new TextField("项目","在此键入项目",50,TextField.ANY);
        Item_TypeSrc=new ChoiceGroup("From",ChoiceGroup.POPUP);
        Item_TypeDst=new ChoiceGroup("To",ChoiceGroup.POPUP);
        Item_Money=new TextField("金额","0",8,TextField.DECIMAL);
        Item_PostScript=new TextField("备注","在此键入备注",50,TextField.ANY);
        for(int i=0;i<SettingManager.getRecordSetting().getRecordType().length;i++)
        {
            Item_TypeSrc.append(SettingManager.getRecordSetting().getRecordType()[i], null);
            Item_TypeDst.append(SettingManager.getRecordSetting().getRecordType()[i], null);
        }
        Item_TypeSrc.append("收入", null);
        Item_TypeDst.append("支出", null);
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc=" BuildUI ">
        this.append(Item_ID);
        this.append(Item_DateTime);
        this.append(Item_Item);
        this.append(Item_TypeSrc);
        this.append(Item_TypeDst);
        this.append(Item_Money);
        this.append(Item_PostScript);
        this.addCommand(Command_QuickInput);
        this.addCommand(Command_Save);
        this.addCommand(WsyMobileAccount.Command_Cancel);
        //</editor-fold>
    }

    public void SetRecord(Record record)
    {
        this.Data_Record=record;
        //<editor-fold defaultstate="collapsed" desc=" NewRecord ">
        if (record == null)
        {
            this.setTitle("新增纪录");
            Item_ID.setString(String.valueOf(Record.Record_NewID));
            Item_DateTime.setDate(Calendar.getInstance().getTime());
            Item_Item.setString("");
            //Type.setSelectedIndex(0, true);
            Item_Money.setString("");
            Item_PostScript.setString("");
        }
        //</editor-fold>
        else
        {
            //<editor-fold defaultstate="collapsed" desc=" Command_QuickInput ">
            Item_Item.setString(this.Data_Record.Item);
            Item_Money.setString(String.valueOf(this.Data_Record.Money));
            Item_PostScript.setString(this.Data_Record.PostScript);
            if (record.RecordID.equals("00000000000"))
            {
                this.setTitle("新建记录");
                Item_DateTime.setDate(Calendar.getInstance().getTime());
                this.Data_Record = null;
            }
            //</editor-fold>
            else
            //<editor-fold defaultstate="collapsed" desc=" formRecord ">
            {
                Item_ID.setString(this.Data_Record.RecordID);
                this.setTitle("编辑记录");
                Item_DateTime.setDate(this.Data_Record.Time.getTime());
                //Type.setSelectedIndex(this.record.Type, true);//Debug Values
            }
            //</editor-fold>
        }
    }

    public void SaveRecord()
    {
        if (Data_Record==null)
        {
            Data_Record=new Record(Item_ID.getString(),Item_Item.getString(),Double.parseDouble(Item_Money.getString()),Item_TypeSrc.getSelectedIndex(),Item_TypeDst.getSelectedIndex(),Item_PostScript.getString());
            Data_Record.Time.setTime(Item_DateTime.getDate());//record.Time=day;
            Data_Record.SaveToRMS();
        }
        else
        {
            Data_Record.Time.setTime(Item_DateTime.getDate());
            Data_Record.Item=this.Item_Item.getString();
            Data_Record.Money=Double.parseDouble(this.Item_Money.getString());
            Data_Record.TypeSrc=this.Item_TypeSrc.getSelectedIndex();
            Data_Record.TypeDst=this.Item_TypeDst.getSelectedIndex();
            Data_Record.PostScript=this.Item_PostScript.getString();
        }
    }
}
