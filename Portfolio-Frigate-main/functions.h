#ifndef FUNCTION_H
#define FUNCTION_H

int checkBoardSize(int size);
char** createBoard(int size);
void fillBoard(char** board, int size);
void printBoard(char** board, int size);
void deleteBoard(char** board, int size);
struct shipClass* createShipArray();
struct shipClass* createShips(struct shipClass* shipArray);
void printShipArray(struct shipClass* shipArray);

int numberOfShells(int size);
int randomNum(int upper, int lower);
char** placeShips(char** gBoard, struct shipClass* shipArray, int size);
bool checkIfFilled(struct shipClass* shipArray, char** gBoard, int row, int col, int iteration, bool isVert);
bool stringCheck(char str[], int size);
void changeString(char* str);
void fireShell(char** gBoard, char** mBoard, char* str);

struct shipClass{
	char iden;
	int col;
	int row;
	int length;
	bool isVert;
};
#endif
