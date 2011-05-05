/*
 * To change this template, choose Tools | Templates
 * and load the template in the editor.
 */
package Wsy.MobileAccount.Setting;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.microedition.rms.RecordStore;

abstract class Setting
{
    private String settingName = null;
    private RecordStore rin = null;
    private RecordStore rout = null;
    private ByteArrayInputStream bin = null;
    private ByteArrayOutputStream bout = null;
    private DataInputStream din = null;
    private DataOutputStream dout = null;

    protected DataInputStream getDataInputStream()
    {
        return this.din;
    }

    protected DataOutputStream getDataOutputStream(){
        return this.dout;
    }

    public Setting(String name)
    {
        this.settingName=name;
    }

    public void load()
    {
        try
        {
            this.rin = RecordStore.openRecordStore(settingName, false);
            this.bin = new ByteArrayInputStream(rin.getRecord(rin.getNextRecordID()));
            this.din = new DataInputStream(bin);
            this.doLoad();
            this.rin.closeRecordStore();
        } catch (Exception ex)
        {
        }
    }

    public void save()
    {
        try
        {
            this.bout = new ByteArrayOutputStream();
            this.dout = new DataOutputStream(bout);
            this.rout = RecordStore.openRecordStore(settingName, true, RecordStore.AUTHMODE_PRIVATE, true);
            this.doSave();
            try
            {
                rout.setRecord(1, bout.toByteArray(), 0, bout.size());
            } catch (Exception e)
            {
                rout.addRecord(bout.toByteArray(), 0, bout.size());
            }
            rout.closeRecordStore();
        } catch (Exception e)
        {
        }
    }

    protected abstract void doLoad();
    protected abstract void doSave();
}
