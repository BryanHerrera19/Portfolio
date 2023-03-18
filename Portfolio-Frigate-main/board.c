//Bryan Herrera
//b_herrera6@u.pacfic.edu

#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <stdlib.h>
#include <errno.h>
#include <stdbool.h>
#include <math.h>
#include <time.h>
#include "functions.h"

int checkBoardSize(int size){
if(size<5){
	size = 5;
	printf("Minimum grid size is 5...Creating a grid of size 5.\n\n");
}
if(size>20){
	size = 20;
	printf("Maximum grid size is 20...Creating a grid of size 20.\n\n");
}
printf("\n");
return size;
}

char** createBoard(int size){
char** board = NULL;

board = (char **)malloc(sizeof(char *)*size);
	for(int i=0;i<size;i++){
		board[i] = (char *)malloc(sizeof(char)*size);
	}
return board;
}

void fillBoard(char** board, int size){
	for(int i=0;i<size;i++){
		for(int j=0;j<size;j++){
		board[i][j] = '-';
		}
	}
return;
}

void printBoard(char** board, int size){
int count = 1;
char alph = 65;

	printf("      ");
	for(int i=0;i<size;i++){
	printf("%c", alph);
	printf(" ");
	alph = alph+1;
	}
	printf("\n");
	for(int i=0;i<size;i++){
		if(count<10){
			printf(" %d", count);
			printf("  | ");
		}else{
		printf(" %d", count);
		printf(" | ");
		}
		for(int j=0;j<size;j++){
		printf("%c ", board[i][j]);
		}
		printf("\n");
		count = count + 1;
	}
	printf("\n");
}

struct shipClass* createShipArray(){
	struct shipClass* shipArray = NULL;
	shipArray = (struct shipClass*)malloc(sizeof(struct shipClass) * 4);
	return shipArray;
}

struct shipClass* createShips(struct shipClass* shipArray){
    srand(time(NULL));

    shipArray[0].iden = 'c';
    shipArray[0].length = 5;
    shipArray[0].isVert = (rand() % (2 - 0));
    shipArray[0].row = 0;
    shipArray[0].col = 0;

    shipArray[1].iden = 'b';
    shipArray[1].length = 4;
    shipArray[1].isVert = (rand() % (2 - 0));
    shipArray[1].row = 0;
    shipArray[1].col = 0;

    shipArray[2].iden = 'f';
    shipArray[2].length = 2;
    shipArray[2].isVert = (rand() % (2 - 0));
    shipArray[2].row = 0;
    shipArray[2].col = 0;

    shipArray[3].iden = 'f';
    shipArray[3].length = 2;
    shipArray[3].isVert = (rand() % (2 - 0));
    shipArray[3].row = 0;
    shipArray[3].col = 0;

    return shipArray;
}

void deleteBoard(char** board, int size){
	for(int i=0;i<size; i++){
		free(board[i]);
	}
	free(board);
return;
}

void printShipArray(struct shipClass* shipArray){
    for(int i = 0; i<4; i++){
        printf("Ship type: %c", shipArray[i].iden);
        printf("\nShip length: %d", shipArray[i].length);
        printf("\nIs vertical: %d", shipArray[i].isVert);
        printf("\nRow: %d", shipArray[i].row);
        printf("\nCol: %d", shipArray[i].col);
        printf("\n\n");
    }
}
