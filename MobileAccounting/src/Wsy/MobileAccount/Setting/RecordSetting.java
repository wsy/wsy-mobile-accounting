package Wsy.MobileAccount.Setting;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Date;

import Wsy.MobileAccount.*;


public class RecordSetting extends Setting
{
    private int fastRecordNum;
    private RecordVector fastRecord;
    private String recordType[];

    public RecordSetting()
    {
        super("Setting_Record");Debug.show("RecordSetting_Constructor");
        initDefaultValue();
    }

    private void initDefaultValue()
    {Debug.show("RecordSetting_initDefaultValue");
        fastRecordNum = 2;Debug.show("RecordSetting_initDefaultValue_InitString");
        recordType = new String[]{ "现金支出", "现金收入", "银行卡支出", "银行卡收入", "内部转账" };
        fastRecord = new RecordVector();Debug.show("RecordSetting_initDefaultValue_InitRecordVector");
        fastRecord.addElement(new Record("00000000000", "午饭", 3.6, 0, recordType.length, "备注"));
        fastRecord.addElement(new Record("00000000000", "晚饭", 3.6, 0, recordType.length, "备注"));
    }

    public int getFastRecordNum()
    {
        return fastRecordNum;
    }

    public void setFastRecordNum(int fastRecordNum)
    {
        this.fastRecordNum = fastRecordNum;
    }

    public Record[] getFastRecord()
    {
        Record[] Result=new Record[fastRecord.getElementCount()];
        fastRecord.copyInto(Result);
        return Result;
    }

    public Record getFastRecord(int index)
    {
        return fastRecord.getElement(index);
    }

    public void setFastRecord(RecordVector fastRecord)
    {
        this.fastRecord = fastRecord;
    }

    public void addFastRecord(Record fastRecord)
    {
        this.fastRecord.addElement(fastRecord);
    }

    public String[] getRecordType()
    {
        return recordType;
    }

    public void setRecordType(String[] recordType)
    {
        this.recordType = recordType;
    }

    protected void doLoad()
    {
        try
        {
            DataInputStream din = this.getDataInputStream();
            fastRecordNum = din.readInt();
            fastRecord = new RecordVector();
            for (int i = 0; i < fastRecordNum; i++)
            {
                Record read=new Record(din.readUTF(), din.readUTF(), din.readDouble(),
                    din.readInt(), din.readInt(), din.readUTF());
                read.Time.setTime(new Date(din.readLong()));
                fastRecord.addElement(read);
            }
            recordType = new String[din.readInt()];
            for (int i = 0; i < recordType.length; i++)
            {
                recordType[i] = din.readUTF();
            }
        } catch (Exception e)
        {
        }
    }

    protected void doSave()
    {Debug.show("RecordSetting_DoSave");
        try
        {Debug.show("RecordSetting_DoSave_InitializeStream");
            DataOutputStream dout = this.getDataOutputStream();Debug.show("RecordSetting_DoSave_WriteNumberOfFastRecord");
            dout.writeInt(fastRecordNum);Debug.show("CurrentFastRecordNum="+fastRecordNum);
            for (int i = 0; i < fastRecordNum; i++)
            {Debug.show("RecordSetting_DoSave_WriteRecord"+i);Debug.show("RecordSetting_DoSave_WriteRecord"+i+"RecordID"+(fastRecord.getElement(i)));
                dout.writeUTF(fastRecord.getElement(i).RecordID);Debug.show("RecordSetting_DoSave_WriteRecord"+i+"Item");
                dout.writeUTF(fastRecord.getElement(i).Item);Debug.show("RecordSetting_DoSave_WriteRecord"+i+"Money");
                dout.writeDouble(fastRecord.getElement(i).Money);Debug.show("RecordSetting_DoSave_WriteRecord"+i+"TypeSrc");
                dout.writeInt(fastRecord.getElement(i).TypeSrc);Debug.show("RecordSetting_DoSave_WriteRecord"+i+"TypeDst");
                dout.writeInt(fastRecord.getElement(i).TypeDst);Debug.show("RecordSetting_DoSave_WriteRecord"+i+"PostScript");
                dout.writeUTF(fastRecord.getElement(i).PostScript);Debug.show("RecordSetting_DoSave_WriteRecord"+i+"Time.getTime.getTime");
                dout.writeLong(fastRecord.getElement(i).Time.getTime().getTime());
            }Debug.show("RecordSetting_DoSave_WriteRecordTypeLength");
            dout.writeInt(recordType.length);
            for (int i = 0; i < recordType.length; i++)
            {Debug.show("RecordSetting_DoSave_WriteRecordType"+i);
                dout.writeUTF(recordType[i]);
            }
        } catch (Exception e)
        {e.toString();e.printStackTrace();
        }
    }
}
