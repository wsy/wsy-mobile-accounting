/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Wsy.MobileAccount;

import java.util.Vector;

/**
 *
 * @author WSY
 */
public class RecordVector extends Vector
{
    public int getElementCount()
    {
        return this.elementCount;
    }
    public Record getElement(int index)
    {
        return (Record)getElement(index);
    }
    public Record fingElement(String ID)
    {
        for (int i=0;i<elementCount;i++)
        {
            if (this.getElement(i).RecordID.equals(ID))
            {
                return this.getElement(i);
            }
        }
        return null;
    }
}
