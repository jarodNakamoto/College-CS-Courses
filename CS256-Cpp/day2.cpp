#include <iostream>
#include <string>

using namespace std;

int main()
{
   int x = 40;
   int max = 30;
   int y = 35;


  // equivalent statement
   if(x > y)
     max = x;
   else
     max = y;


   // conditional operator
   max = (x>y)?x:y;


}
