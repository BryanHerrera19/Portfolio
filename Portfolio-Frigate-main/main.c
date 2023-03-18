//Bryan Herrera
//b_herrera6@u.pacfic.edu

#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <stdlib.h>
#include <errno.h>
#include <stdbool.h>
#include <time.h>
#include "functions.h"


int main(){
srand(time(NULL));

int size, shells;
char input[100];
bool stringFit = false;

char** mainBoard = NULL;
char** ghostBoard = NULL; //Seperate board to store locations of ships w/o interfering with main board
struct shipClass* arrayOfShips = NULL;

//bool stringLengthCheck = true;

printf("Hello! Welcome to Frigate!\nHow large should I make the grid? ");
scanf("%d", &size);
size = checkBoardSize(size);

ghostBoard = createBoard(size);
fillBoard(ghostBoard, size);

mainBoard = createBoard(size);
fillBoard(mainBoard, size);
shells = numberOfShells(size);

arrayOfShips = createShipArray();
createShips(arrayOfShips);
ghostBoard = placeShips(ghostBoard, arrayOfShips, size);

while(shells !=0){
    printf("Your Board: \n\n");
	printBoard(mainBoard, size);

	do{
	    printf("Enter the coordinates for your shot (%d",shells);
        printf(" shells remaining): ");
        scanf("%s", input);
        printf("\n");
        stringFit = stringCheck(input, size);
	}while(stringFit == false);

    fireShell(ghostBoard, mainBoard, input);
    changeString(input);
	printf("\n");

	shells--;
}

printf("You have run out of shells! Here is the placement of the ships and your board.\nThanks for Playing!\n\n");

printf("Your Board: \n\n");
printBoard(mainBoard, size);
printf("Real Board: \n\n");
printBoard(ghostBoard, size);

deleteBoard(mainBoard, size);
deleteBoard(ghostBoard, size);
free(arrayOfShips);

return 0;
}
