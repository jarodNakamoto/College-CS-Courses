// Jarod Nakamoto
// Genevieve Leach
// CS 256
// Assignment 1



#include <iostream>
#include <cstdlib>
#include <ctime>

using namespace std;

//constant int of number of rows/columns
const int n = 4;

void printTop();
void print(int card[n][n], bool fd[n][n]);
void shuffle(int (&card)[n][n]);
void getInput(int card[n][n], int &row, int &column, int &value);

int main()
{
    cout << "Memory Matching Game" << endl << endl;


    //keeps track of card values
    int cards[n][n];
    //keeps track of whether the card has been found or not
    bool found[n][n];

    // initializes game board

    //num keeps track of which number to add to the matrix
    int num = 1;
    //numTimes keeps track of how many times a number has been used
    int numTimes = 1;

    //nested for loop to initialize every value in card and found
    for(int row = 0; row < n; row++)
    {
       for(int col = 0; col < n; col++)
       {
           //no cards found at start of game
           found[row][col] = false;
           
           //card value is num
           cards[row][col] = num;
           
           //if number has been used move on to next number
           if(numTimes == 2)
           {
              numTimes = 0;
              num = num+1;
           }
           numTimes++;
       }
    } 


    // swaps cards the number of pairs times
    for(int i = 1; i <= (n*n/2); i++)
    {
        shuffle(cards);
    } 
 
    //holder values
    int row1, col1, row2, col2;
    int value1 = 696, value2 = 69;
    //keeps track of number of pairs found
    int pairsFound = 0;

    while(pairsFound < (n*n/2))
    {
       //print out the game board
       print(cards, found);
       
       //get input from keyboard and store it in  respective values
       getInput(cards, row1, col1, value1); 
        
       getInput(cards, row2, col2, value2);


       
       //If both cards match, they stay face up.
       if (value1 == value2) 
       {
         found[row1-1][col1-1] = true;
         found[row2-1][col2-1] = true;
         pairsFound++;
       }

    }
}

//gets user input
void getInput(int card[n][n], int &row, int &column, int &value)
{
       //get user input
       cout << "Input coordinate as prompted" << endl;
       cout << "Column: ";
       cin >> column;

       cout << "Row: ";
       cin >> row;


       value = card[row-1][column-1];
       cout << "Value at cooridnate (" << column << "," << row << ") is " << value << endl;
}


//picks 2 cards at random and swaps them
void shuffle(int (&card)[n][n])
{

    srand((unsigned)time(0));

    int row1, row2, col1, col2;
  
    row1 =(rand()%n)+1;
    col1 =(rand()%n)+1;

    row2 =(rand()%n)+1;
    col2 =(rand()%n)+1;

    if(row1 == row2 && col2 == col1)
    {
        shuffle(card);
    }
    else
    {
        int temp = card[row1][col1];
        card[row1][col1] = card[row2][col2];
        card[row2][col2] = temp;
    }
}


void printTop()
{
    //prints out 5 spaces and then the column header;
    cout << "     ";
    
    for(int i = 1; i <= n; i++)
    {
        //print out the column number and 4 spaces
        cout << i << "    ";
    }
   
    //print out new line
    cout << endl;
    //print underscores
    cout << "     ---------------------" << endl;
}


void print(int card[n][n], bool fd[n][n])
{
    //print the top part
    printTop();

    //nested for loop to look at every value
    for(int row = 0; row < n; row++)
    {
       //print out the row header and 3 spaces
       cout << (row + 1) << "   |";
       
       for(int col = 0; col < n; col++)
       {
            //if card is found print value else print *
            if(fd[row][col]==true)
            {
                cout << card[row][col];
            }
            else
            {
                cout << "*";
            }
            //print 4 spaces
            cout << "    ";
       }
       //print next line
       cout << endl;
    }

    //print 2 new lines
    cout << endl << endl;
}
