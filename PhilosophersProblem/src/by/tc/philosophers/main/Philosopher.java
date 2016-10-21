package by.tc.philosophers.main;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/*�������. ������� ������������ ���������� ���������.
 *������ ������� �������� ����� ������� �� ���� � ����� ������� ���������.
 *��� ����, ����� ������, �������� ��������� ������� ����� � ������.
 *���� ������� � ������� ��������, �� ������� �������.
 */

public class Philosofer implements Runnable{
    private Chopstick left;//����� �������
    private Chopstick right;//������ �������
    private final int id;//������������� ��������
    //�����������, ������� ������ �� �����, ���������� ���������� �� �������������.
    //���� �� ���������, �� deadlock ��������� ������
    private final int pounderFactor;
    private Random rand=new Random(47);
    
    private void pause() throws InterruptedException{
        //�������� sleep �� ��������� ������
        //����� ������� ������� ���������� � ��� ��������� ��������� �����
        if(pounderFactor==0) return;
        TimeUnit.MILLISECONDS.sleep(rand.nextInt(pounderFactor*250));
    }

    public Philosofer(Chopstick left, Chopstick right, int ident, int pounder) {
        //� ������������ ���������� ������ �� ����� � ������ ������� ��� ��������
        // � ����� id �������� � �����������, �������� �� �����
        this.left = left;
        this.right = right;
        id = ident;
        pounderFactor = pounder;
    }
    
    public void run() {
        try{
            while(!Thread.interrupted()){
                //����� �����������,  ���� Thread.interrupted() �� ������ true
                System.out.println(this+" "+"thinking");
                pause();//������� ���������� ��������� �����
                System.out.println(this+" "+"grabbing right");
                right.take();//����� ������ �������, ���� ��������. ���� ���-����.
                System.out.println(this+" "+"grabbing left");
                left.take();//����� ����� �������, ���� ��������. ���� ���-����.
                System.out.println(this+" "+"eating");
                pause();//������� ��� ��������� �����
                right.drop();//������� ������� ������ �������
                left.drop();//������� ������� ����� �������
            }
        }
        catch(InterruptedException e){
            System.out.println(this+" "+"exiting via interrupt");
        }
    }
    
    public String toString(){ 
        return "Philosofer "+id;
    }
    
}
