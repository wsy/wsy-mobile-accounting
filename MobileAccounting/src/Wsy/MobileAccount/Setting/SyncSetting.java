package Wsy.MobileAccount.Setting;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class SyncSetting extends Setting
{
    private String serverAddress;
    private int syncFreq;
    private int syncType;

    public SyncSetting()
    {
        super("Setting_Sync");
        initDefaultValue();
    }

    private void initDefaultValue()
    {
        serverAddress = "192.168.137.1:11022";
        syncFreq = 1;
        syncType = Enum_SyncType.DoubleWaySync;
    }

    public String getServerAddress()
    {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress)
    {
        this.serverAddress = serverAddress;
    }

    public int getSyncFreq()
    {
        return syncFreq;
    }

    public void setSyncFreq(int syncFreq)
    {
        this.syncFreq = syncFreq;
    }

    public int getSyncType()
    {
        return syncType;
    }

    public void setSyncType(int syncType)
    {
        this.syncType = syncType;
    }

    protected void doLoad()
    {
        try
        {
            DataInputStream din = this.getDataInputStream();
            serverAddress = din.readUTF();
            syncFreq = din.readInt();
            syncType = din.readInt();
        } catch (Exception e)
        {
        }
    }

    protected void doSave()
    {
        try
        {
            DataOutputStream dout = this.getDataOutputStream();
            dout.writeUTF(serverAddress);
            dout.writeInt(syncFreq);
            dout.writeInt(syncType);
        } catch (Exception e)
        {
        }
    }
}
