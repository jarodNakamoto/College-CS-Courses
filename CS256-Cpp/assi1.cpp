#include <iostream>

using namespace std;

//constant int of number of rows/columns
const int n = 4;

void printTop();
void print(int (&card)[n][n], bool (&fd)[n][n]);
void generateGame(int (&card)[n][n], bool (&fd)[n][n]);
void shuffle(int (&card)[n][n]);

int main()
{
    cout << "Memory Matching Game" << endl << endl;

    int pairsFound = 0;

    //keeps track of card values
    int[n][n] cards;
    //keeps track of whether the card has been found or not
    bool[n][n] found;

    generateGame(cards, found);

    //while(pairsFound < (n*n/2))
    //{
       //print out the game board
       print(cards, found);
    //}
}


void generateGame(int[n][n] card, bool[n][n] fd)
{
    // initializes game board

    //num keeps track of which number to add to the matrix
    int num = 1;
    //numTimes keeps track of how many times a number has been used
    int numTimes = 0;

    //nested for loop to initialize every value in card and fd
    for(int row = 0; row < n; row++)
    {
       for(int col = 0; col < n; col++)
       {
           //no cards found at start of game
           fd[row][col] = false;
           
           //card value is num
           card[row][col] = num;
           
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
        shuffle(card);
    }
}


//picks 2 cards at random and swaps them
void shuffle(int[n][n] card))
{

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
}


void print(int[n][n] card, bool[n][n] fd)
{
    //print the top part
    printTop();

    //nested for loop to look at every value
    for(int row = 0; row < n; row++)
    {
       //print out the row header and 3 spaces
       cout << (row + 1) << "   |";
       
       for(int col = 0; col < n; col++;
       {
            //if card is found print value else print *
            if(fd[n][n])
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
