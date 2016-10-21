package by.tc.philosophers.main;

// ������� ��� ���


public class Chopstick {
    private boolean taken=false;//���������� ���������� ����� �� ������� ���-�� �� ���������
    public synchronized void take() throws InterruptedException{
        //���� ������� ��������, �� ������� ����� ��, ���� ���, �� ����, ���� ��� �����������
        while(taken){
            wait();
        }
        taken=true;//������� ����� �������
    }
    public synchronized void drop(){
        //������� ������� ������� � ��� ���������� ���������
        taken=false;
        notifyAll();
    }
}