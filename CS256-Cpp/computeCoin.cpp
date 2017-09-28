#include <iostream>

using namespace std;

void computeCoin(int coinValue, int &number, int &amountLeft);

int main()
{
    int amountLeft = 0,q=0,d=0;

    do
    {
       cout << "Enter number between 1 & 99: " << endl;
       cin >> amountLeft;
      
       while(amountLeft > 99 || amountLeft < 1){
	cout << "Try again... 1-99" << endl;
	cin >> amountLeft;}
	cout << amnountLeft << "can be given by" << endl;
	computerCoin(25,q,amountLeft);
	computeCoin(10,d,amountLeft);
	cout << q << "quarter(s)"<<d<<"dimes(s) and " << amountLeft << 
"penny (pennies)\n";
	cout << "Do you want to calculate another number (y/n)/n)";
	cin >> userIn;
	}while(userIn = 'y');
	return 0;
}

int computeCoin(int coinValue, int &number, int &amountLeft){
	number = amountLeft/coinValue;
	amountLeft = amountLeft % coinValue;
}
