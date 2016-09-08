package org.lk.util.http;

/**
 * �������
 * 
 * @author lk
 *
 */
public class LoanMath {

	/**
	 * Ͷ����Ϣ ��ÿ�¸�����ʽ 50000����1+3%��^30
	 * 
	 * @param monthCountͶ������
	 * @param InterestRate������
	 * @param moneyͶ�ʽ��
	 * 
	 * @return ����ʲ�
	 */
	public static double interestOnOnvestment(int monthCount, double InterestRate, double money) {
		return money * Math.pow(1 + InterestRate / 12, monthCount);
	}

	/**
	 * ����Ͷ�ʼ���
	 * 
	 * @param monthCountͶ������
	 * @param InterestRate������
	 * @param money������
	 * @param loanRate��������
	 * @return ʣ����
	 */
	public static double loanInvestment(int monthCount, double InterestRate, double money, double loanRate) {
		return InterestRate;
	}

	/**
	 * ����ÿ�� ֧�� ���ȶϢ ���㹫ʽ��
	 * �ƻ��»����=�������������ʡ�(1+������)^�����������¡�(1+������)^��������-1��------��������=�������ޡ�12
	 * ������=������/12
	 * 
	 * @param monthCount��������
	 * @param money������
	 * @param loanRate��������
	 * @return ÿ��֧��
	 */
	public static double loanEveMonth1(int monthCount, double money, double loanRate) {
		double monthRate = loanRate / 12;
		return money * monthRate * Math.pow(1 + monthRate, monthCount) / Math.pow((1 + monthRate), monthCount - 1);
	}

	/**
	 * �ȶ�𻹿�
	 * �ȶ�𻹿��ÿ�µȶ�����𣬴�����Ϣ�汾��������µݼ�ֱ��������Ҳ����˵��ÿ�¹黹�����������ȣ���Ϣ=����ʣ�౾��������ʡ�
	 * ��������������ÿ�µĻ������̶�����������ÿ�±���ļ��ٶ��ݼ������Ż�����������࣬��Ϣ�ɶ��𽥼��١� ���㹫ʽ��
	 * �ƻ��»����=(�����»�������)+(�����-�ۼ��ѻ�����)�������� �ۼ��ѻ�����=�Ѿ��黹����������������/��������
	 * 
	 * @param monthCount��������
	 * @param money������
	 * @param loanRate��������
	 * @return ÿ��֧��
	 */
	public static double loanEveMonth2(int monthCount, double money, double loanRate) {
		return 0;
	}

	public static void main(String[] args) {
		System.out.println(loanEveMonth1(12, 10000, 0.1));
	}
}
