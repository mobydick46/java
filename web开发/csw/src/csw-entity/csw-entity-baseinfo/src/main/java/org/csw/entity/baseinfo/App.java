package org.csw.entity.baseinfo;

/**
 * Hello world!
 *
 */
public class App 
{
	static{
		x=0;
	}
	static int x,y;
    public static void main( String[] args )
    {
    	x--;
    	method();
        System.out.println(x + y + ++x);
    }
    
    static void method(){
    	y= x++ + ++x;
    }
}
