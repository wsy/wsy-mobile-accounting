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
        super("Setting_Record");
        initDefaultValue();
    }

    private void initDefaultValue()
    {
        fastRecordNum = 2;
        recordType = new String[]{ "现金支出", "现金收入", "银行卡支出", "银行卡收入", "内部转账" };
        fastRecord = new RecordVector();
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
    {
        try
        {
            DataOutputStream dout = this.getDataOutputStream();
            dout.writeInt(fastRecordNum);
            for (int i = 0; i < fastRecordNum; i++)
            {
                dout.writeUTF(fastRecord.getElement(i).RecordID);
                dout.writeUTF(fastRecord.getElement(i).Item);
                dout.writeDouble(fastRecord.getElement(i).Money);
                dout.writeInt(fastRecord.getElement(i).TypeSrc);
                dout.writeInt(fastRecord.getElement(i).TypeDst);
                dout.writeUTF(fastRecord.getElement(i).PostScript);
                dout.writeLong(fastRecord.getElement(i).Time.getTime().getTime());
            }
            dout.writeInt(recordType.length);
            for (int i = 0; i < recordType.length; i++)
            {
                dout.writeUTF(recordType[i]);
            }
        } catch (Exception e)
        {
        }
    }
}
