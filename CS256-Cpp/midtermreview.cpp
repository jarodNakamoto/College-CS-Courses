#include <iostream>

using namespace std;

void zeroBoth(int&a,int&b)
{
	a = 0;
	b = 0;
}

void oneMore(int array[],int size)
{
	for(int i = 0;i<size;i++)
	{
		array[i]+=1;
	}
}

int main()
{
	//passing array practice
	int arr[6] ={1,23,324,543534,534,5};
	int size = 6;
 	for(int i = 0;i<size;i++)
	{
		cout << arr[i] << endl;
	}
	cout << endl << endl;

	oneMore(arr,size);
	 for(int i = 0;i<size;i++)
	{
		cout << arr[i] << endl;
	}
	cout << endl << endl;


/*
	//pass by refrence pracice
	int a = 89023;
	int b = 23789;
	cout << a << endl;	
	cout << b << endl;
	zeroBoth(a,b);
	cout << a << endl;	
	cout << b << endl;*/
	return 0;
}
