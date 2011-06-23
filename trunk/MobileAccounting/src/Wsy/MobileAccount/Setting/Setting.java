/*
 * To change this template, choose Tools | Templates
 * and load the template in the editor.
 */
package Wsy.MobileAccount.Setting;

import Wsy.MobileAccount.Debug;
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
    {Debug.show(settingName+"_load");
        try
        {Debug.show(settingName+"_load_openRecordStore");
            this.rin = RecordStore.openRecordStore(settingName, false);Debug.show(settingName+"_load_InitStream");
            this.bin = new ByteArrayInputStream(rin.getRecord(rin.getNextRecordID()));
            this.din = new DataInputStream(bin);Debug.show(settingName+"_load_doLoad");
            this.doLoad();
            this.din.close();
            this.bin.close();
            this.rin.closeRecordStore();
        } catch (Exception ex)
        {
        }
    }

    public void save()
    {Debug.show(settingName+"_Save");
        try
        {Debug.show(settingName+"_Save_InitializeStream");
            this.bout = new ByteArrayOutputStream();
            this.dout = new DataOutputStream(bout);
            this.rout = RecordStore.openRecordStore(settingName, true, RecordStore.AUTHMODE_PRIVATE, true);Debug.show(settingName+"_Save_CallSubFunction");
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
