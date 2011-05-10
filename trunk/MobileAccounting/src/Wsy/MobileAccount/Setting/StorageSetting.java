package Wsy.MobileAccount.Setting;

import Wsy.MobileAccount.Debug;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class StorageSetting extends Setting
{
    private int maxRecordDay;
    private int maxLogDay;
    private boolean forceUnsyncedData;
    private int catalogID;
    private int daysInStorage;

    public StorageSetting()
    {
        super("Setting_Storage");Debug.show("StorageSetting_Constructor");
        initDefaultValue();
    }

    private void initDefaultValue()
    {Debug.show("StorageSetting_initDefaultValue");
        maxRecordDay = 30;
        maxLogDay = 30;
        forceUnsyncedData = true;
        catalogID = 2;
        daysInStorage = 0;
    }

    public int getMaxRecordDay()
    {
        return maxRecordDay;
    }

    public void setMaxRecordDay(int maxRecordDay)
    {
        this.maxRecordDay = maxRecordDay;
    }

    public int getMaxLogDay()
    {
        return maxLogDay;
    }

    public void setMaxLogDay(int maxLogDay)
    {
        this.maxLogDay = maxLogDay;
    }

    public boolean isForceUnsyncedData()
    {
        return forceUnsyncedData;
    }

    public void setForceUnsyncedData(boolean forceUnsyncedData)
    {
        this.forceUnsyncedData = forceUnsyncedData;
    }

    public int getCatalogID()
    {
        return catalogID;
    }

    public void setCatalogID(int catalogID)
    {
        this.catalogID = catalogID;
    }

    public int getDaysInStorage()
    {
        return daysInStorage;
    }

    public void setDaysInStorage(int daysInStorage)
    {
        this.daysInStorage = daysInStorage;
    }

    protected void doLoad()
    {
        try
        {
            DataInputStream din = this.getDataInputStream();
            maxRecordDay = din.readInt();
            maxLogDay = din.readInt();
            forceUnsyncedData = din.readBoolean();

        } catch (Exception e)
        {
        }
    }

    protected void doSave()
    {
        try
        {
            DataOutputStream dout = this.getDataOutputStream();
            dout.writeInt(maxRecordDay);
            dout.writeInt(maxLogDay);
            dout.writeBoolean(forceUnsyncedData);
        } catch (Exception e)
        {
        }
    }
}
