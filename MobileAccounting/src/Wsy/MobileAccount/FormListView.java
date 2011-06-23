package Wsy.MobileAccount;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Calendar;
import javax.microedition.lcdui.List;

/**
 *
 * @author WSY
 */
public class FormListView extends List
{
    int DayIndex;
    String Today;
    FormListView()
    {
        super(WsyMobileAccount.CTS(Calendar.getInstance()),List.IMPLICIT);
        Today=this.getTitle();
    }
    public void SetDate(Calendar Day)
    {
        Today=WsyMobileAccount.CTS(Day);
        DayIndex=Record.DayIndex.length;
        this.deleteAll();
        this.setTitle(Today);
        for (int i=0;i<Record.DayIndex.length;i++)
        {
            if (Today.equals(Record.DayIndex[i]))
            {
                DayIndex=i;
                break;
            }
        }
        if (DayIndex==Record.DayIndex.length)
        {
            return;
        }
        for (int i=0;i<WsyMobileAccount.CurrentData[DayIndex].length;i++)
        {
            this.append(WsyMobileAccount.CurrentData[DayIndex][i].getShortInfo(), null);
        }
    }
}
