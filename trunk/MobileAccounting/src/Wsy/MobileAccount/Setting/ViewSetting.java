package Wsy.MobileAccount.Setting;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ViewSetting extends Setting
{
    private int defaultView;
    private int defaultFilter;

    public ViewSetting()
    {
        super("Setting_View");
        initDefaultValue();
    }

    private void initDefaultValue()
    {
        defaultView = Enum_FormView.List;
        defaultFilter = Enum_LogFilter.DefaultFilter;
    }

    public int getDefaultView()
    {
        return defaultView;
    }

    public void setDefaultView(int defaultView)
    {
        this.defaultView = defaultView;
    }

    public int getDefaultFilter()
    {
        return defaultFilter;
    }

    public void setDefaultFilter(int defaultFilter)
    {
        this.defaultFilter = defaultFilter;
    }

    protected void doLoad()
    {
        try
        {
            DataInputStream din = this.getDataInputStream();
            defaultView = din.readInt();
            defaultFilter = din.readInt();
        } catch (Exception e)
        {
        }
    }

    protected void doSave()
    {
        try
        {
            DataOutputStream dout = this.getDataOutputStream();
            dout.writeInt(defaultView);
            dout.writeInt(defaultFilter);
        } catch (Exception e)
        {
        }
    }
}
