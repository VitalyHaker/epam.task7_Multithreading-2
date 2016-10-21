package by.tc.philosophers.main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MoveSticks {
    public static void main(String[] args) throws Exception{
        int ponder=5;//�����������, �������� �� ����� ����������� ��� ���
        int size=5;//���������� ��������� � �������
        ExecutorService exec=Executors.newCachedThreadPool();//������� ��� �������
        Chopstick[] sticks=new Chopstick[size];
        for(int i=0;i<size;i++){
            sticks[i]=new Chopstick();
        }
        for(int i=0;i<size;i++){
            if(i<(size-1)){
                //���� ��� �� ��������� �������, �������� ������� � ������� �������
                exec.execute(new Philosofer(sticks[i],sticks[i+1],i,ponder));
            }
            else{
                //���� ��� ��������� �������, �������� ������� � �������� �������
                //�������������, ������� �� ����� ����� ����� �������, � ����� ������
                exec.execute(new Philosofer(sticks[0],sticks[i],i,ponder));
            }
        }
        System.out.println("Press 'Enter' to quit");
        System.in.read();
        exec.shutdownNow();//�������� �������� ������
    }
}

