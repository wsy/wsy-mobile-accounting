package Wsy.MobileAccount;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Calendar;
import javax.microedition.rms.*;
import Wsy.MobileAccount.Setting.SettingManager;

/**
 * 
 * @author WSY
 */
public class Record
{
    // // <editor-fold defaultstate="collapsed" desc=" Enum Values ">
    // public static final int RecordTyper_Income = 1;
    // public static final int RecordType_Outcome = 2;
    // public static final int RecordType_Inter = 1;
    // // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" Static Varibles ">
    public static long Record_NewID;// =Program.CTL(Calendar.getInstance())*1000+1;
    static int DayNum = 0;
    // static boolean EmptyRecord;
    static String DayIndex[];
    static Record CurrentData[][];
    static int Catalog[][];
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" Varibles ">
    public String RecordID;
    public Calendar Time;
    public String Item;
    public double Money;
    public int TypeSrc;
    public int TypeDst;
    public String PostScript;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" Abandoned Constructor ">
    // Record()
    // {
    // RecordID=String.valueOf(Record_NewID);
    // Record_NewID++;
    // Time=Calendar.getInstance();
    // }
    // Record(Record Source)
    // {
    // this.Item=Source.Item;
    // this.Money=Source.Money;
    // this.PostScript=Source.PostScript;
    // this.Type=Source.Type;
    // }
    // </editor-fold>

    public Record(String ID, String Item, double Money, int TypeSrc,int TypeDst, String PostScript)
    {System.out.println("Debug_Record65");
        if (ID.equals("00000000000"))
        {
            this.RecordID = ID;
        }
        else
        {
            RecordID = String.valueOf(Record_NewID);
            Record_NewID++;
        }System.out.println("Debug_Record74");
        this.Item = Item;
        this.Money = Money;
        this.TypeSrc = TypeSrc;
        this.TypeDst = TypeDst;
        this.PostScript = PostScript;System.out.println("Debug_Record79");
    }

    public String getShortInfo()
    {
        int end = 12;
        String Result;
        if (this.Item.length() < 15)
        {
            end = this.Item.length();
        }
        Result = this.Item.substring(0, end);
        if (this.Item.length() > 15)
        {
            Result = Result + "...";
        }
        Result = Result + " " + this.Money;
        return Result;
    }

    public static int GetNum(Calendar Day)
    {
        String Label;// =Program.CTS(Day);
        int Num = 0;//
        return Num;
    }

    public static void SetID(Calendar Day)
    {
        int Num = GetNum(Day);
        long Label = 0;// =Program.CTL(Day);
        Record_NewID = Label * 1000 + Num + 1;
    }

    public static void ReadRMS()
    {Debug.show("Record_ReadRMS");
        // <editor-fold defaultstate="collapsed" desc=" ReadAllRecord ">
        try
        {Debug.show("Record_ReadRMS_InitializeStream");
            RecordStore rin = RecordStore.openRecordStore("MobileAccountSettngs", false);
            ByteArrayInputStream bin = new ByteArrayInputStream(rin.getRecord(SettingManager.getStorageSetting().getCatalogID()));
            DataInputStream din = new DataInputStream(bin);Debug.show("Record_ReadRMS_BeginRead");
            DayNum = din.readInt();
            if (DayNum == 0)
            {
                DayIndex = null;
                CurrentData = null;
                Catalog = null;
            } else
            {
                DayIndex = new String[DayNum];
                CurrentData = new Record[DayNum][];
                Catalog = new int[DayNum][];
                for (int i = 0; i < DayNum; i++)
                {
                    DayIndex[i] = din.readUTF();
                }
            }
            rin.closeRecordStore();
            // <editor-fold defaultstate="collapsed" desc=" ReadOneDay ">
            if (DayNum != 0)
            {
                for (int i = 0; i < DayNum; i++)
                {
                    try
                    {
                        rin = RecordStore.openRecordStore(DayIndex[i], false);
                        bin = new ByteArrayInputStream(rin.getRecord(1));
                    } catch (RecordStoreException e)
                    {
                        continue;
                    }
                    din = new DataInputStream(bin);
                    int RecordNumber = din.readInt();
                    CurrentData[i] = new Record[RecordNumber];
                    Catalog[i] = new int[RecordNumber];
                    for (int j = 0; j < CurrentData[i].length; j++)
                    {
                        try
                        {
                            Catalog[i][j] = rin.getNextRecordID();
                            bin = new ByteArrayInputStream(
                                    rin.getRecord(Catalog[i][j]));
                        } catch (RecordStoreException e)
                        {
                            continue;
                        }
                        din = new DataInputStream(bin);
                        CurrentData[i][j] = new Record(din.readUTF(),
                                din.readUTF(), din.readDouble(), din.readInt(),
                                din.readInt(), din.readUTF());
                    }
                    rin.closeRecordStore();
                }
            }
            // </editor-fold>
        }
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc=" FirstRun ">
        catch (RecordStoreNotFoundException e)
        {
            SettingManager.SaveRMSConfig();
            CreateCatalog();
        } catch (InvalidRecordIDException e)
        {
            CreateCatalog();
        } catch (Exception e)
        {

        }
        // </editor-fold>
    }

    public void SaveToRMS()
    {
        try
        {
            RecordStore rin = RecordStore.openRecordStore(
                    RecordID.substring(0, 7), true);
            ByteArrayInputStream bin = new ByteArrayInputStream(
                    rin.getRecord(1));
            DataInputStream din = new DataInputStream(bin);
            int RNO = din.readInt();
        } catch (Exception e)
        {

        }
        // RecordStore rin=RecordStore.openRecordStore(RecordID.substring(0, 7),
        // false);
    }

    public static void CreateCatalog()
    {
        try
        {
            RecordStore rout = RecordStore.openRecordStore(
                    "MobileAccountSetting", false);
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            DataOutputStream dout = new DataOutputStream(bout);
            dout.writeInt(0);
        } catch (Exception e)
        {
        }
    }

    // <editor-fold defaultstate="collapsed" desc=" AbandonedMethod ">
    // static void CreateRecordCatalog()
    // {
    // try
    // {
    // ByteArrayOutputStream bout=new ByteArrayOutputStream();
    // DataOutputStream dout=new DataOutputStream(bout);
    // RecordStore rout=RecordStore.openRecordStore("MobileAccountSettings",
    // false);
    // dout.writeInt(0);
    // SettingItem.Storage_CatalogID=rout.addRecord(bout.toByteArray(), 0,
    // bout.size());
    // rout.closeRecordStore();
    // }
    // catch (Exception e)
    // {
    //
    // }
    // }// </editor-fold>
}
