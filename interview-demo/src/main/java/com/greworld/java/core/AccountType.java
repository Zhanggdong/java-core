package com.greworld.java.core;

/**
 * @author 风骚的GRE
 * @Description TODO
 * @date 2018/3/1.
 */
public enum AccountType
{
    SAVING, FIXED, CURRENT;
    private AccountType()
    {
        System.out.println("It is a account type");
    }
}
class EnumOne
{
    public static void main(String[]args)
    {
        System.out.println(AccountType.FIXED);
    }
}
