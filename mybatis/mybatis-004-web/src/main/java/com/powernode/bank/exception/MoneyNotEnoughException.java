package com.powernode.bank.exception;

/**
 * @author 饺子！
 * @since 2024/6/27 10:59
 **/
public class MoneyNotEnoughException extends Exception{
    public MoneyNotEnoughException(){}
    public MoneyNotEnoughException(String msg){super(msg);}
}
