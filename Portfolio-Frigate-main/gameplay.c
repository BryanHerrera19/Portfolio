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

int randomNum(int upper, int lower){
    int randomNumber;

    randomNumber = (rand() % (upper + 1 - lower)) + lower;

    return randomNumber;

}

bool checkIfFilled(struct shipClass* shipArray, char** gBoard, int row, int col, int iteration, bool isVert){
    int r = row;
    int c = col;

    if(isVert == true){
        for(int j = 0; j < shipArray[iteration].length; j++){
            if(gBoard[r][col] != '-'){
                return true;
            }
            r++;
        }
    }
    else if(isVert == false){
        for(int j = 0; j < shipArray[iteration].length; j++){
            if(gBoard[row][c] != '-'){
                return true;
            }
            c++;
        }
    }

    return false;
}

char** placeShips(char** gBoard, struct shipClass* shipArray, int size){
    int rowCoord;
    int colCoord;
    bool areAllFilled = true;

    for(int i = 0; i < 4; i++){
        if(shipArray[i].iden == 'c'){ //If the ship is a carrier
            if(shipArray[i].isVert == true){ //If carrier is veritcal
                while(areAllFilled != false){ //keep going until areAllFilled equal true
                rowCoord = randomNum(size - shipArray[i].length, 0);
                colCoord = randomNum(size - 1, 0);
                    for(int j = rowCoord; j < shipArray[i].length; j++){ //Checking if spaces are empty vertically
                        if(gBoard[j][colCoord] != '-'){
                            areAllFilled = true;
                            rowCoord = randomNum(size - shipArray[i].length, 0);
                            colCoord = randomNum(size - 1, 0);
                        }
                    }
                    areAllFilled = false;
                }
                for(int j = rowCoord; j < rowCoord + shipArray[i].length; j++){ //Placing carrier on board vertically
                    gBoard[j][colCoord] = shipArray[i].iden;

                }
                shipArray[i].row = rowCoord;
                shipArray[i].col = colCoord;
                areAllFilled = true;
            }else if(shipArray[i].isVert == false){ //If carrier is horizontal
                while(areAllFilled != false){
                rowCoord = randomNum(size - 1, 0);
                colCoord = randomNum(size - shipArray[i].length, 0);
                    for(int j = colCoord; j < shipArray[i].length; j++){ //Checking if spaces are empty horizontally
                        if(gBoard[rowCoord][j] != '-'){
                            areAllFilled = true;
                            rowCoord = randomNum(size - 1, 0);
                            colCoord = randomNum(size - shipArray[i].length, 0);
                        }
                    }
                    areAllFilled = false;
                }
                for(int j = colCoord; j < colCoord + shipArray[i].length; j++){ //Placing carrier on board horizontally
                    gBoard[rowCoord][j] = shipArray[i].iden;
                }
                shipArray[i].row = rowCoord;
                shipArray[i].col = colCoord;
                areAllFilled = true;
            }
        }
        else if(shipArray[i].iden == 'b'){//If the ship is a battleship
            if(size == 5 && shipArray[i-1].iden == 'c' && (shipArray[i-1].row > 0 || shipArray[i-1].row < 4 || shipArray[i-1].col > 0 || shipArray[i-1].col < 4)){
                shipArray[i].isVert = shipArray[i-1].isVert;
            }
            if(shipArray[i].isVert == true){ //If battleship is veritcal
                while(areAllFilled == true){
                    rowCoord = randomNum(size - shipArray[i].length, 0);
                    colCoord = randomNum(size - 1, 0);
                    areAllFilled = checkIfFilled(shipArray, gBoard, rowCoord, colCoord, i, shipArray[i].isVert);
                }

                for(int j = rowCoord; j < rowCoord + shipArray[i].length; j++){ //Placing battleship on board vertically
                    gBoard[j][colCoord] = shipArray[i].iden;
                }

                shipArray[i].row = rowCoord;
                shipArray[i].col = colCoord;
                areAllFilled = true;

            }else if(shipArray[i].isVert == false){ //If batlleship is horizontal
                while(areAllFilled == true){
                    rowCoord = randomNum(size - 1, 0);
                    colCoord = randomNum(size - shipArray[i].length, 0);
                    areAllFilled = checkIfFilled(shipArray, gBoard, rowCoord, colCoord, i, shipArray[i].isVert);
                }

                for(int j = colCoord; j < colCoord + shipArray[i].length; j++){ //Placing battleship on board horizontally
                    gBoard[rowCoord][j] = shipArray[i].iden;
                }

                shipArray[i].row = rowCoord;
                shipArray[i].col = colCoord;
                areAllFilled = true;
            }
        }
        else if(shipArray[i].iden == 'f'){ //If the ship is a frigate
            if(shipArray[i].isVert == true){ //If frigate is veritcal
                while(areAllFilled == true){
                    rowCoord = randomNum(size - shipArray[i].length, 0);
                    colCoord = randomNum(size - 1, 0);
                    areAllFilled = checkIfFilled(shipArray, gBoard, rowCoord, colCoord, i, shipArray[i].isVert);
                }

                for(int j = rowCoord; j < rowCoord + shipArray[i].length; j++){ //Placing frigate on board vertically
                    gBoard[j][colCoord] = shipArray[i].iden;
                }

                shipArray[i].row = rowCoord;
                shipArray[i].col = colCoord;
                areAllFilled = true;

            }else if(shipArray[i].isVert == false){ //If frigate is horizontal
                while(areAllFilled == true){
                    rowCoord = randomNum(size - 1, 0);
                    colCoord = randomNum(size - shipArray[i].length, 0);
                    areAllFilled = checkIfFilled(shipArray, gBoard, rowCoord, colCoord, i, shipArray[i].isVert);
                }

                for(int j = colCoord; j < colCoord + shipArray[i].length; j++){ //Placing frigate on board horizontally
                    gBoard[rowCoord][j] = shipArray[i].iden;
                }

                shipArray[i].row = rowCoord;
                shipArray[i].col = colCoord;
                areAllFilled = true;
            }
        }
    }
    return gBoard;
}

int numberOfShells(int size){
	int startingShells;

	startingShells = (int)floor((size*size)/2);
	return startingShells;
}

bool stringCheck(char str[], int size){
    if(strlen(str) > 2 || strlen(str) <= 1){
        printf("Coordinate Error! ");
        return false;
    }
    for(long unsigned int i = 0; i < strlen(str); i++){
        if(isalpha(str[i]) == 0 && isdigit(str[i]) == 0){
            return false;
        }else if(isalpha(str[i] != 0 && str[i] - 97 > size)){
            return false;
        }else if(isdigit(str[i]) !=0 && str[i] - 49 > size)
        return false;
        }
    return true;
}

void changeString(char* str){
    for(long unsigned int i = 0; i < strlen(str); i++){
        if(isalpha(str[i]) != 0){
            str[i] = toupper(str[i]);
        }
    }
}

void fireShell(char** gBoard, char** mBoard, char* str){
    int row;
    int col;

    for(long unsigned int i = 0; i < strlen(str); i++){ //Changes char digits and letters to array locations
        if(isdigit(str[i]) != 0){
            row = str[i] - 49;
        }if(isalpha(str[i]) != 0){
            col = str[i] - 97;
        }
    }

    if(gBoard[row][col] != '-'){
        mBoard[row][col] = 'h';

    }else{
        mBoard[row][col] = 'm';
        gBoard[row][col] = 'm';
    }
}
