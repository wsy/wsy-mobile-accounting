package Wsy.MobileAccount;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.microedition.lcdui.*;
import java.util.Calendar;

/**
 *
 * @author WSY
 */
public class JumpToDate extends Form
{
    public static final Command Command_Jump=new Command("确定",Command.OK,0);
    DateField Field_Date;
    JumpToDate()
    {
        super("跳至指定日期");
        Field_Date=new DateField("请输入要跳转的日期",DateField.DATE);
        Field_Date.setDate(Calendar.getInstance().getTime());
        this.append(Field_Date);
        this.addCommand(Command_Jump);
    }
}
